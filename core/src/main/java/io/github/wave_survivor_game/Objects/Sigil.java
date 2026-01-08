package io.github.wave_survivor_game.Objects;

import io.github.wave_survivor_game.Entities.Enemy;
import io.github.wave_survivor_game.Entities.Player;
import io.github.wave_survivor_game.Managers.SFXManager;
import io.github.wave_survivor_game.Utilities.ObjectType;
import io.github.wave_survivor_game.Managers.EntityManager;

public class Sigil extends ConsObject {

    private static final float radius = 20f; //radius 20 kolem hrace

    public Sigil(float x, float y) {
        super(x * 32, y * 32, 25, 25, 3, "sigil", ObjectType.CONSUMABLE, "Strange Sigil");
        String description = "When the energy from the sigil is used, creatures around you drop dead.";
    }

    @Override
    public void use(Player player) {
        instantKill(player);
    }

    private void instantKill(Player player) {

        float playerX = player.getX();
        float playerY = player.getY();
        EntityManager em = EntityManager.getInstance();

        for (Enemy enemy : em.getEnemies()) {
            float distance = (float) Math.sqrt(Math.pow(enemy.getX() - playerX, 2) + Math.pow(enemy.getY() - playerY, 2));

            if (distance <= radius) {
                enemy.setHealth(0);
                System.out.println("Killed enemies around thr player");
            }
        }
    }

    @Override
    public void specificUsageSound() {
        SFXManager.getInstance().play("sigil-use", 0.2f);
    }

    public void soundWhenGone() {
        SFXManager.getInstance().play("sigil-gone", 0.09f); //idk, nevim co jeste s tim
    }
}
