package io.github.wave_survivor_game.Entities;

import com.badlogic.gdx.Gdx;
import io.github.wave_survivor_game.Utilities.EntityType;

public class Bullet extends Entity {

    private Player player;

    public Bullet(float x, float y, float width, float height, int damage, String texture, Player player) {
        super(x, y, width, height, texture, EntityType.BULLET);
        this.player = player;  // Initialize the player field
        dir = (float) (Math.atan2(Gdx.input.getX() - x, -((Gdx.graphics.getHeight() - Gdx.input.getY()) - y)) - (Math.PI / 2));  // Calculate direction towards the mouse
        speed = 4;
        this.damage = damage;
    }

    // Update the bullet's position and ensure it's within screen coordinates
    public void update() {
        // Update the bullet's world position
        this.x += (float) (Math.cos(dir) * speed);
        this.y += (float) (Math.sin(dir) * speed);

        this.sprite.setPosition(this.x, this.y);

        sm.draw(x, y, getWidth(), getHeight(), texture);
    }

    @Override
    public void onCollide(Entity e) {
        if (e.type != EntityType.PLAYER && e.health > 0) {
            e.health -= this.damage;
            System.out.println("Enemy's health: " + e.health);

            if (e.health == 0) {
                this.isDestroyed = true;
                System.out.println("Enemy is dead!");
                player.setScore(player.getScore() + 1);

            }
            this.isDestroyed = true;
        }
    }
}
