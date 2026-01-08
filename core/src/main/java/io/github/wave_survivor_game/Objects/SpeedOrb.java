package io.github.wave_survivor_game.Objects;

import io.github.wave_survivor_game.Entities.Player;
import io.github.wave_survivor_game.Managers.SFXManager;
import io.github.wave_survivor_game.Utilities.ObjectType;

public class SpeedOrb extends ConsObject {

    public SpeedOrb(float x, float y) {
        super(x * 32, y * 32, 20, 20, 3, "greenOrb1", ObjectType.CONSUMABLE, "Orb of speed");
        String description = "When crushed, the orb provides a temporary boost to walking speed.";
    }

    @Override
    public void use(Player player) {

        player.setSpeed((int) (player.getSpeed() + 2));
        System.out.println("Used " + this.name);
    }

    @Override
    public void specificUsageSound() {
        SFXManager.getInstance().play("orb-use");
    }

}
