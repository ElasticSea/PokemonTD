package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
public class DisplayPicture extends DisplayBlock {

    private final SpriteBatch spriteBatch;
    private TextureAtlas.AtlasRegion texture;

    DisplayPicture(Rectangle rectangle, SpriteBatch spriteBatch) {
        this(rectangle.x, rectangle.y, rectangle.width, rectangle.height, spriteBatch);
    }

    DisplayPicture(float x, float y, float width, float height, SpriteBatch spriteBatch) {
        super(x, y, width, height);
        this.spriteBatch = spriteBatch;
    }

    @Override
    public void render() {
        spriteBatch.begin();
        spriteBatch.draw(texture, x, y, width, height);
        spriteBatch.end();
    }

    public void render(TextureAtlas.AtlasRegion texture) {
        this.texture = texture;
        render();
    }
}