package io.github.wave_survivor_game.Managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.wave_survivor_game.Tile;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TileManager {
    private ArrayList<Tile> tileMap;
    private SpriteBatch batch;

    public TileManager() {
        tileMap = new ArrayList<>();
        batch = new SpriteBatch();
    }

    public void loadTileMap(String filePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int y = 0;

            while ((line = br.readLine()) != null) {
                String[] numbers = line.split(" ");
                for (int x = 0; x < numbers.length; x++) {
                    int type = Integer.parseInt(numbers[x]);
                    tileMap.add(new Tile(x, y, type));
                }
                y++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render() {
        batch.begin();

        for (Tile tile : tileMap) {
            Texture tileTexture = getTextureForTileType(tile.getTileNumber());
            tile.render(batch, tileTexture);
        }

        batch.end();
    }

    private Texture getTextureForTileType(int type) {
        switch (type) {
            case 1:
                return SpriteManager.getInstance().getTexture("tile_1");
            case 2:
                return SpriteManager.getInstance().getTexture("tile_2");
            case 3:
                return SpriteManager.getInstance().getTexture("tile_3");
            case 4:
                return SpriteManager.getInstance().getTexture("tile_4");
            case 5:
                return SpriteManager.getInstance().getTexture("tile_5");
            default:
                return SpriteManager.getInstance().getTexture("missing");
        }
    }

    public void dispose() {
        batch.dispose();
    }
}
