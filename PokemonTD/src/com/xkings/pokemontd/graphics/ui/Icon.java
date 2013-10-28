package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Tomas on 10/8/13.
 */
abstract class Icon extends InteractiveBlock {

    private final SpriteBatch spriteBatch;
    private final TextureAtlas.AtlasRegion texture;
    private final float size;
    private final Vector2 offset;

    Icon(Rectangle rectangle, SpriteBatch spriteBatch, TextureAtlas.AtlasRegion texture, boolean pixelScale) {
        super(rectangle);
        this.spriteBatch = spriteBatch;
        this.texture = texture;
        this.size = pixelScale ? ((int) width / texture.getRegionWidth()) * texture.getRegionWidth() : width;
        this.offset = new Vector2((width - size) / 2, (height - size) / 2);
    }

    @Override
    public void render() {
        spriteBatch.begin();
        spriteBatch.draw(texture, x + offset.x, y + offset.y, size, size);
        spriteBatch.end();
    }
}