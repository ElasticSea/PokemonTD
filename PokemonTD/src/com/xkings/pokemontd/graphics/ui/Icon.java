package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Tomas on 10/8/13.
 */
abstract class Icon extends InteractiveBlock {

    protected final SpriteBatch spriteBatch;
    private TextureAtlas.AtlasRegion texture;
    private float size;
    private Vector2 offset;

    Icon(Ui ui, float x, float y, float width, float height) {
        this(ui, new Rectangle(x, y, width, height));
    }

    Icon(Ui ui, Rectangle rectangle) {
        super(rectangle);
        this.spriteBatch = ui.getSpriteBatch();
    }

    @Override
    public void render() {
       /* if (color != Color.CLEAR) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(color);
            shapeRenderer.rect(x, y, width, height);
            shapeRenderer.end();
        }   */

    }

    private void renderInternal(){
        if (texture != null) {
            spriteBatch.begin();
            spriteBatch.draw(texture, x + offset.x, y + offset.y, size, size);
            spriteBatch.end();
        }
    }

    public void render(TextureAtlas.AtlasRegion texture) {
        render(texture, texture.getRegionWidth(), false);
    }

    public void render(TextureAtlas.AtlasRegion texture, float textureSize, boolean pixelPerfect) {
        if (this.texture != texture) {
            this.texture = texture;
            this.size = pixelPerfect ? Math.round(textureSize / texture.getRegionWidth()) * texture.getRegionWidth() :
                    width;
            this.offset = new Vector2((width - size) / 2, (height - size) / 2);
        }

        renderInternal();
    }

    public void render(TextureAtlas.AtlasRegion texture, boolean pixelPerfect) {
        render(texture, width, pixelPerfect);
    }

    /*
        spriteBatch.begin();
        spriteBatch.draw(texture, x + offset.x, y + offset.y, size, size);
        spriteBatch.end();
    }    */
}