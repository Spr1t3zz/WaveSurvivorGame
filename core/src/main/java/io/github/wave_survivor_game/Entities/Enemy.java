package io.github.wave_survivor_game.Entities;

import com.badlogic.gdx.Gdx;
import io.github.wave_survivor_game.Utilities.EntityType;

public class Enemy extends Entity {

    public Enemy(float x, float y, float speed, int health, int damage, String texture) {
        super(x, y, 32, 32, 1, speed, health, damage, texture, EntityType.ENEMY);
    }

    private float damageCooldown = 1.0f;
    private float damageTimer = 0.0f;


    public void update() {
        sm.draw(x, y, width, height, texture);

        Player p = em.getPlayer();
        float dx = p.getX() - x;
        float dy = p.getY() - y;

        dir = (float) (Math.atan2(p.x - x, -(p.y - y)) - (Math.PI / 2));
        if (Math.abs(dx) >= 16f) this.x += (float) (Math.cos(dir) * speed / 2);
        if (Math.abs(dy) >= 16f) this.y += (float) (Math.sin(dir) * speed / 2);

        this.sprite.setX(this.x);
        this.sprite.setY(this.y);

        if (health <= 0) destroy();

        if (damageTimer > 0) {
            damageTimer -= Gdx.graphics.getDeltaTime();
        }
    }

    @Override
    public void onCollide(Entity e) {
        if (e.type == EntityType.PLAYER && damageTimer <= 0) {
            e.health -= this.damage;

            if (e.health <= 0) {
                e.health = 0;
                System.out.println("Player is dead! Game Over!");
                e.isDestroyed = true;
            }

            System.out.println("Player health: " + e.health);
            damageTimer = damageCooldown;
        }
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
