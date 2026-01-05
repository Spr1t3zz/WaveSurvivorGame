package io.github.wave_survivor_game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.wave_survivor_game.Entities.Player;
import io.github.wave_survivor_game.Managers.*;

public class Main extends ApplicationAdapter {

    private SpriteManager sm;
    private EntityManager em;
    private TextureManager tm;
    private ObjectManager om;
    private TileManager tim;
    private GameInterface gameInterface;

    @Override
    public void create() {
        tm = new TextureManager();
        sm = SpriteManager.getInstance();
        em = EntityManager.getInstance();

        Player player = new Player("playerTest");
        om = ObjectManager.getInstance(player);

        tim = new TileManager();
        tim.loadTileMap("assets/mapTest");
        om.spawnRandomObject();
        em.addPlayer(player);

        tm.loadTextures();

        gameInterface = new GameInterface(player);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);


        sm.begin();

        Player player = em.getPlayer();

        tim.render();

        em.update();

        om.update();

        gameInterface.render();

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }

        sm.end();
    }

    @Override
    public void dispose() {
        sm.dispose();
        gameInterface.dispose();
    }
}
