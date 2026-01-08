package io.github.wave_survivor_game.Objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import io.github.wave_survivor_game.Entities.Player;
import io.github.wave_survivor_game.Managers.SFXManager;
import io.github.wave_survivor_game.Utilities.ObjectType;

import java.util.ArrayList;

public abstract class ConsObject extends Object {

    public ConsObject(float mapX, float mapY, float width, float height, float scale, String texture, ObjectType type, String name) {
        super(mapX, mapY, width, height, scale, texture, type, name);
    }

    public abstract void use(Player player);

    public void pickingUpSound() {
        SFXManager.getInstance().play("picking-up-items");
    }

    public abstract void specificUsageSound();

    public void onInteract(Player player, ArrayList<ConsObject> inventory) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.E) && this.type == ObjectType.CONSUMABLE) {
            if (player.getSprite().getBoundingRectangle().overlaps(this.sprite.getBoundingRectangle())) {
                if (inventory.size() < 8) {
                    this.destroy();
                    inventory.add(this);
                    System.out.println(this + " added to inventory");
                    pickingUpSound();
                } else if (inventory.size() == 8) {
                    System.out.println("Inventory is full"); //nebere to veci do plneho invenntare a necha veci na zemi
                    //pouzivam arraylist protoze je superior to pole
                }
            }
            }


        }
    }

