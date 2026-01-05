package io.github.wave_survivor_game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.wave_survivor_game.Managers.EntityManager;
import io.github.wave_survivor_game.Managers.SpriteManager;
import io.github.wave_survivor_game.Utilities.EntityType;

public abstract class Entity {
    protected float x;
    protected float y;
    protected float dir;
    protected float width;
    protected float height;
    protected float scale;
    protected float knockBackInertia;
    protected float speed;
    protected int health;
    protected int damage;
    protected String texture;
    protected boolean isDestroyed;
    protected Sprite sprite;
    public EntityType type;

    protected final SpriteManager sm;
    protected final EntityManager em;

    public Entity(float x, float y, float width, float height, float scale, float speed , int health, int damage, String texture, EntityType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.speed = speed;
        this.health = health;
        this.damage = damage;
        this.texture = texture;
        isDestroyed = false;
        this.sprite = new Sprite();
        sprite.setPosition(x, y);
        sprite.setSize(width, height);
        this.type = type;

        sm = SpriteManager.getInstance();
        em = EntityManager.getInstance();
    }

    public Entity(float x, float y, float width, float height, String texture, EntityType type) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.texture = texture;
        isDestroyed = false;
        this.sprite = new Sprite();
        sprite.setPosition(x, y);
        sprite.setSize(width, height);
        this.type = type;
        // TODO: sprite.setOrigin();

        sm = SpriteManager.getInstance();
        em = EntityManager.getInstance();
    }

    public abstract void update();

    public abstract void onCollide(Entity e);

    public void knockback() {
        if (knockBackInertia > 0) {
            this.x -= (float) Math.cos(-dir) *  knockBackInertia * 10;
            this.y -= (float) Math.sin(-dir) * knockBackInertia * 10;
            knockBackInertia -= 0.6f;
        }
    }

    public void destroy() {
        this.isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }


    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getScale() {
        return scale;
    }

    public float getSpeed() {
        return speed;
    }

    public int getHealth() {
        return health;
    }

    public int getDamage() {
        return damage;
    }

    public String getTexture() {
        return texture;
    }

    public Sprite getSprite() {
        return sprite;
    }
}
