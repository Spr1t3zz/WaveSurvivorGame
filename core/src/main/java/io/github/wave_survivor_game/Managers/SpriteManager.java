package io.github.wave_survivor_game.Managers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.HashMap;

public class SpriteManager {
    private static SpriteManager instance;
    private SpriteBatch batch;
    private TextureManager tm;

    HashMap<String, Texture> textures;

    public static SpriteManager getInstance(){
        if(instance == null){
            instance = new SpriteManager();
        }
        return instance;
    }

    private SpriteManager(){
        batch = new SpriteBatch();
        textures = new HashMap<>();
    }

    public Texture getTexture(String name) {
        return textures.get(name);
    }

    public void draw(float x, float y, float w, float h, String textureName) {
        Texture texture = textures.get(textureName);
        if (texture != null) {
            batch.draw(texture, x, y, w, h);
        } else {
            System.out.println("Error: Texture '" + textureName + "' not found!");
        }
    }


    public void begin() {
        if (textures.isEmpty()) {
            System.out.println("Warning: No textures loaded yet!");
        }
        batch.begin();
    }


    public void end() {
        batch.end();
    }

    public void dispose() {
        for (Texture t : textures.values()) {
            t.dispose();
        }
        batch.dispose();
    }

    public void loadSprite(String name, String path) {
        try {
            textures.put(name, new Texture(path));
            System.out.println("Loaded texture: " + name + " from " + path);
        } catch (Exception e) {
            System.out.println("Error loading texture: " + name + " from " + path);
            e.printStackTrace();
        }
    }

    public SpriteBatch getBatch() {
        return batch;
    }
}
