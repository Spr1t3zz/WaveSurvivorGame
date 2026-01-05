package io.github.wave_survivor_game.Objects;

import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.wave_survivor_game.Managers.SpriteManager;
import io.github.wave_survivor_game.Utilities.ObjectType;

public abstract class Object {      //jakoby staticky object, na consumables mam ConsObject class

    protected float mapX;
    protected float mapY;
    protected float width;
    protected float height;
    protected float scale;
    protected String texture;
    protected boolean isDestroyed;

    protected Sprite sprite;
    protected ObjectType type;
    protected String name;

    protected final SpriteManager sm;

    public Object(float mapX, float mapY, float width, float height, float scale, String texture, ObjectType type, String name) {
        this.mapX = mapX;
        this.mapY = mapY;
        this.width = width;
        this.height = height;
        this.scale = scale;
        this.texture = texture;
        this.type = type;
        this.name = name;
        this.isDestroyed = false;

        this.sprite = new Sprite();
        sprite.setPosition(mapX, mapY);
        sprite.setSize(width * scale, height * scale);

        sm = SpriteManager.getInstance();
    }

    public float getMapX() {
        return mapX; }

    public float getMapY() {
        return mapY; }

    public float getWidth() {
        return width; }

    public float getHeight() {
        return height; }

    public float getScale() {
        return scale; }

    public String getTexture() {
        return texture; }

    public Sprite getSprite() {
        return sprite; }

    public ObjectType getType() {
        return type; }

    public String getName() {
        return name; }


    public void update() {
        updateSpritePosition();
        sm.draw(getMapX(), getMapY(), getWidth(), getHeight(), getTexture());
    }

    public void destroy() {
        this.isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public void updateSpritePosition() {
        sprite.setPosition(mapX, mapY);
    }

//    public void onInteract(Player player, ArrayList<Object> inventory) {
//
//        if (Gdx.input.isKeyJustPressed(Input.Keys.E) && this.type == ObjectType.CONSUMABLE) {
//            if (player.getSprite().getBoundingRectangle().overlaps(this.sprite.getBoundingRectangle())) {
//                this.destroy();
//                inventory.add(this);
//                System.out.println(this.name + " added to inventory");
//            }
//        }
//    }


}
