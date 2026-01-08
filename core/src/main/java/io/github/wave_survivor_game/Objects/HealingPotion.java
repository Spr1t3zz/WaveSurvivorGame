package io.github.wave_survivor_game.Objects;

import io.github.wave_survivor_game.Entities.Player;
import io.github.wave_survivor_game.Managers.SFXManager;
import io.github.wave_survivor_game.Utilities.ObjectType;

public class HealingPotion extends ConsObject {     //moderate healing of 50hp

    public HealingPotion(float x, float y) {
        super(x * 32, y * 32, 25, 25, 3, "redPotion", ObjectType.CONSUMABLE, "Blood Concoction");
        String description = "When drank from the bottle, the blood concoction heals by 50 HP.";
    }

    @Override
    public void use(Player player) {

        if (player.getHealth() > 0 && player.getHealth() < 100) {
            int maxHealth = player.getHealth() + 50;
            if (maxHealth > 100) {
                maxHealth = 100;
            }
            player.setHealth(maxHealth);
            System.out.println("Used " + this.name);
        }
    }

    @Override
    public void specificUsageSound() {
        SFXManager.getInstance().play("potion-use", 0.06f);
    }

}
