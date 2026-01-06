package io.github.wave_survivor_game.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.wave_survivor_game.Objects.ConsObject;
import io.github.wave_survivor_game.Utilities.EntityType;

import java.util.ArrayList;

public class Player extends Entity {

    private int score;
    ArrayList<ConsObject> inventory = new ArrayList<>();

    public Player(String texture) {
        super((float) Gdx.graphics.getWidth() /2, (float) Gdx.graphics.getHeight() /2, 32, 32, 2, 4, 20, 0, texture, EntityType.PLAYER);
        this.score = 0;
    }

    public void update() {
        sm.draw(getX(), getY(), getWidth(), getHeight(), getTexture());

        //KONTROLY

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) this.y += speed;
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) this.y -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) this.x -= speed;
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) this.x += speed;

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) || Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
            em.addEntity(new Bullet(x + getWidth() / 2, y + getHeight() / 2, 15, 15, 5, "bulletTest2", this));

        }

        for (int i = 0; i < 8; i++) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1 + i)) {
                useItem(i);
            }
        }


        this.sprite.setX(this.x);
        this.sprite.setY(this.y);
    }

    public void useItem(int index) {
        if (index >= 0 && index < inventory.size()) {
            ConsObject item = inventory.get(index);
            if (item != null) {
                item.use(this);
                inventory.remove(index);
            }
        }
    }

    @Override
    public void onCollide(Entity e) {
        if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT) || Gdx.input.isButtonJustPressed(Input.Keys.SPACE)) {
            e.knockBackInertia = 3.0f;
        }
    }

    public ArrayList<ConsObject> getInventory() {
        return inventory;
    }

//    public void onInteract(Object o) {
//        if (o.getTileNumber() == ObjectType.CONSUMABLE) {
//            addToInventory(o);
//            o.destroy();
//        }
//    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
