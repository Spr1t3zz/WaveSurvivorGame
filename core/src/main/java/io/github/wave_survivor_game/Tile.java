package io.github.wave_survivor_game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile {
    private int x, y;
    private int tileNumber;
    public int tileSize = 32;

    public Tile(int x, int y, int tileNumber) {
        this.x = x;
        this.y = y;
        this.tileNumber = tileNumber;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public int getTileNumber() { return tileNumber; }

    public void render(SpriteBatch batch, Texture texture) {
        batch.draw(texture, x * tileSize, y * tileSize, tileSize, tileSize);
    }
}
