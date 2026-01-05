package io.github.wave_survivor_game.Managers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import io.github.wave_survivor_game.Entities.Player;
import io.github.wave_survivor_game.Objects.ConsObject;
import io.github.wave_survivor_game.Objects.HealingGoblet;
import io.github.wave_survivor_game.Objects.HealingPotion;
import io.github.wave_survivor_game.Objects.SpeedOrb;

import java.util.ArrayList;
import java.util.List;

public class ObjectManager {

    private static ObjectManager instance;
    private final List<ConsObject> consumables;
    private final List<ConsObject> buffer;
    private float spawnTimer = 15.0f;
    private Player player;

    public static ObjectManager getInstance(Player player) {
        if (instance == null) {
            instance = new ObjectManager(player);
        }
        return instance;
    }

    public ObjectManager(Player player) {
        consumables = new ArrayList<>();
        buffer = new ArrayList<>();
        this.player = player;
    }

    public void addObject(ConsObject co) {
        buffer.add(co);
    }

    public List<ConsObject> getConsumables() {
        return consumables;
    }

    public void update() {
        spawnTimer -= Gdx.graphics.getDeltaTime();

        if (spawnTimer <= 0) {
            spawnRandomObject();
            spawnTimer = 15.0f;
        }

        consumables.addAll(buffer);
        buffer.clear();

        for (ConsObject co : consumables) {
            co.update();

            if (player.getSprite().getBoundingRectangle().overlaps(co.getSprite().getBoundingRectangle())) {
                co.onInteract(player, player.getInventory());
            }
        }

        consumables.removeIf(ConsObject::isDestroyed);
    }

    public void spawnRandomObject() {
        int randChoice = MathUtils.random(1, 3);
        float randX = MathUtils.random(2, 18);
        float randY = MathUtils.random(2, 18);

        ConsObject spawnedObject = null;

        switch (randChoice) {
            case 1:
                spawnedObject = new HealingGoblet(randX, randY);
                break;
            case 2:
                spawnedObject = new HealingPotion(randX, randY);
                break;
            case 3:
                spawnedObject = new SpeedOrb(randX, randY);
                break;
        }

        if (player.getHealth() > 0 && spawnedObject != null) {
            addObject(spawnedObject);
        }
    }

    //    public void spawnObjects() {      //debug funkce
    //        HealingGoblet ho1 = new HealingGoblet(7, 7);
    //        HealingPotion ho2 = new HealingPotion(4, 4);
    //        SpeedOrb sp1 = new SpeedOrb(9, 9);
    //        addObject(sp1);
    //        addObject(ho1);
    //        addObject(ho2);
    //    }
}
