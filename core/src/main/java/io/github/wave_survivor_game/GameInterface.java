package io.github.wave_survivor_game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.wave_survivor_game.Entities.Player;
import io.github.wave_survivor_game.Managers.SFXManager;
import io.github.wave_survivor_game.Managers.SpriteManager;
import io.github.wave_survivor_game.Objects.Object;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

public class GameInterface {

    private Texture GUI;
    private Texture barRed;
    private Texture barBlack;
    private Texture deathScreen;
    private SpriteBatch spriteBatch;
    private BitmapFont font;
    private Player player;
    private SpriteManager sm;
    private ShapeRenderer shapeRenderer;
    private float opacity = 0f;
    private long deathSound;

    public GameInterface(Player player) {
        sm = SpriteManager.getInstance();
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();
        GUI = new Texture("assets/GUI/gameUI.PNG");
        barRed = new Texture("assets/GUI/healthbarRED.PNG");
        barBlack = new Texture("assets/GUI/healthbarShadow.PNG");
        deathScreen = new Texture("assets/GUI/deathScreen.PNG");
        this.player = player;

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("assets/fonts/AbaddonNumbersOnly-Regular.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 48;

        parameter.shadowOffsetX = -5;
        parameter.shadowOffsetY = 0;
        parameter.shadowColor = new Color(0, 0, 0, 0.3f);

        font = generator.generateFont(parameter);
        generator.dispose();
    }

    public void render() {
        spriteBatch.begin();
        spriteBatch.draw(GUI, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        spriteBatch.draw(barBlack, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());


        float barX = 34;
        float barWidthDefault = 266;
        float healthPercentage = player.getHealth() / 100f;
        float barWidth = barWidthDefault * healthPercentage;
        spriteBatch.draw(barRed, barX, Gdx.graphics.getHeight() - 71, barWidth, 10);


        String scoreText = "" + player.getScore();
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font, scoreText);

        float textWidth = glyphLayout.width;
        float textHeight = glyphLayout.height;
        float textX = 483 + (100 - textWidth) / 2;
        float textY = (Gdx.graphics.getHeight() - 83) + (70 + textHeight) / 2;

        font.setColor(new Color(146 / 255f, 0 / 255f, 0 / 255f, 1));
        font.draw(spriteBatch, glyphLayout, textX, textY);


        int ItemStartX = 150;
        int ItemY = 20;

        for (int i = 0; i < player.getInventory().size(); i++) {
            Object obj = player.getInventory().get(i);
            if (obj != null) {
                Texture itemTexture = sm.getTexture(obj.getTexture());
                spriteBatch.draw(itemTexture, ItemStartX, ItemY, 40, 40);
                ItemStartX += 55;
            }
        }


        if (player.getHealth() <= 0 && deathSound == 0) {
            deathSound = SFXManager.getInstance().play("game-over");
        }

        if (player.getHealth() <= 0) {
            opacity += 0.02f;
            if (opacity > 1f) opacity = 1f;

            spriteBatch.setColor(1f, 1f, 1f, opacity);
            spriteBatch.draw(deathScreen, 0, 76, 600, 426);
            spriteBatch.setColor(1f, 1f, 1f, 1f);
        }

        spriteBatch.end();
    }

    public void dispose() {
        spriteBatch.dispose();
        GUI.dispose();
        font.dispose();
    }
}
