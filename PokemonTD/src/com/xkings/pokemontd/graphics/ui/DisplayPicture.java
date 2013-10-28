package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

/**
 * Created by Tomas on 10/8/13.
 */
public class DisplayPicture extends DisplayBlock {

    private final SpriteBatch spriteBatch;
    private final ShapeRenderer shapeRenderer;
    private final Color color;
    private TextureAtlas.AtlasRegion texture;

    DisplayPicture(float x, float y, float width, float height, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        this(x, y, width, height, shapeRenderer, spriteBatch, Color.CLEAR);
    }

    DisplayPicture(float x, float y, float width, float height, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
                   Color color) {
        super(x, y, width, height);
        this.shapeRenderer = shapeRenderer;
        this.spriteBatch = spriteBatch;
        this.color = color;
    }

    @Override
    public void render() {
        if (color != Color.CLEAR) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(color);
            shapeRenderer.rect(x, y, width, height);
            shapeRenderer.end();
        }

        spriteBatch.begin();
        spriteBatch.draw(texture, x, y, width, height);
        spriteBatch.end();
    }

    public void render(TextureAtlas.AtlasRegion texture) {
        this.texture = texture;
        render();
    }
}