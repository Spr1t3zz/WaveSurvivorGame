package io.github.wave_survivor_game.Objects;

import io.github.wave_survivor_game.Entities.Player;
import io.github.wave_survivor_game.Managers.SFXManager;
import io.github.wave_survivor_game.Utilities.ObjectType;

public class HealingGoblet extends ConsObject {     //minor healing of 20hp

    public HealingGoblet(float x, float y) {
        super(x * 32, y * 32, 23, 23, 3, "bloodGoblet", ObjectType.CONSUMABLE, "Goblet of Blood");
        String description = "When drank from the goblet, the blood heals by 20 HP.";
    }

    @Override
    public void use(Player player) {

        if (player.getHealth() > 0 && player.getHealth() < 100) {
            int maxHealth = player.getHealth() + 20;
            if (maxHealth > 100) {
                maxHealth = 100;
            }
            player.setHealth(maxHealth);
            System.out.println("Used " + this.name);
        }
    }

    @Override
    public void specificUsageSound() {
        SFXManager.getInstance().play("goblet-use");
    }

}
