package io.github.wave_survivor_game.Managers;

import com.badlogic.gdx.Gdx;
import io.github.wave_survivor_game.Entities.Entity;
import io.github.wave_survivor_game.Entities.Player;
import io.github.wave_survivor_game.Entities.Enemy;
import io.github.wave_survivor_game.Utilities.EntityType;
import com.badlogic.gdx.math.MathUtils;

import java.util.ArrayList;
import java.util.List;

public class EntityManager {

    private static EntityManager instance;
    public static EntityManager getInstance() {
        if (instance == null) instance = new EntityManager();
        return instance;
    }

    private Player player;
    private final List<Entity> entities;
    private final List<Entity> buffer;

    private boolean ongoingWave = false;
    private float breakTimer = 5.0f;
    private int startingEnemyCount = 2;
    private static final int mapW = 640;
    private static final int mapH = 480;

    public EntityManager() {
        entities = new ArrayList<>();
        buffer = new ArrayList<>();
    }

    public void addEntity(Entity e) {
        buffer.add(e);
    }

    public void addPlayer(Player p) {
        this.player = p;
        addEntity(p);
    }

    public Player getPlayer() {
        return player;
    }

    public void update() {
        for (Entity e : entities) {
            e.update();

            for (Entity e2 : entities) {
                if (!e.equals(e2)) {
                    if (e.getSprite().getBoundingRectangle().overlaps(e2.getSprite().getBoundingRectangle())) {
                        e.onCollide(e2);
                        e2.onCollide(e);
                    }
                }
            }
        }

        waveLoop();

        entities.removeIf(Entity::isDestroyed);
        entities.addAll(buffer);
        buffer.clear();
    }

    public int getEnemyCount() {
        int count = 0;
        for (Entity e : entities) {
            if (e != null && !e.isDestroyed() && e.type == EntityType.ENEMY) {
                count++;
            }
        }
        return count;
    }

    private void waveLoop() {

    if (player.getHealth() > 0) {
        if (!ongoingWave && breakTimer > 0) {
            breakTimer -= Gdx.graphics.getDeltaTime();
        }

        if (ongoingWave == true && getEnemyCount() == 0) {
            ongoingWave = false;
            breakTimer = 5.0f;
        }

        if (breakTimer <= 0 && !ongoingWave) {
            spawnEnemies(startingEnemyCount);
            startingEnemyCount += 1;
            ongoingWave = true;
        }
    }}

    private void spawnEnemies(int count) {
        for (int i = 0; i < count; i++) {
            float randX = MathUtils.random(0, mapW - 32);
            float randY = MathUtils.random(0, mapH - 32);
            addEntity(new Enemy(randX, randY, 2, 70, 10, "enemyTest"));
            System.out.println("Enemy spawned");
        }
    }

    public Enemy[] getEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        for (Entity e : entities) {
            if (e instanceof Enemy && !e.isDestroyed()) {
                enemies.add((Enemy) e);
            }
        }
        return enemies.toArray(new Enemy[0]);
    }
}
