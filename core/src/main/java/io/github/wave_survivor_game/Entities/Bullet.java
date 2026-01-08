package io.github.wave_survivor_game.Entities;

import com.badlogic.gdx.Gdx;
import io.github.wave_survivor_game.Managers.SFXManager;
import io.github.wave_survivor_game.Utilities.EntityType;

public class Bullet extends Entity {

    private Player player;
    private long flyingFireball;
    private float existence = 0f;

    public Bullet(float x, float y, float width, float height, int damage, String texture, Player player) {
        super(x, y, width, height, texture, EntityType.BULLET);
        this.player = player;
        dir = (float) (Math.atan2(Gdx.input.getX() - x, -((Gdx.graphics.getHeight() - Gdx.input.getY()) - y)) - (Math.PI / 2));
        speed = 4;
        this.damage = damage;

        SFXManager soundManager = SFXManager.getInstance();
        this.flyingFireball = soundManager.play("flying-fireball");
    }

    public void update() {

        this.x += (float) (Math.cos(dir) * speed);
        this.y += (float) (Math.sin(dir) * speed);

        existence += Gdx.graphics.getDeltaTime();

        if (existence >= 1f) {
            this.isDestroyed = true;
        }

        this.sprite.setPosition(this.x, this.y);
        sm.draw(x, y, getWidth(), getHeight(), texture);
    }

    @Override
    public void onCollide(Entity e) {
        if (e.type == EntityType.ENEMY && !isDestroyed) {
            e.health -= damage;
            System.out.println("Enemy's health: " + e.health);

            if (e.health <= 0) {
                e.health = 0;
                System.out.println("Enemy is dead");
                player.setScore(player.getScore() + 1);
            }
            this.isDestroyed = true;

        }
    }

}
