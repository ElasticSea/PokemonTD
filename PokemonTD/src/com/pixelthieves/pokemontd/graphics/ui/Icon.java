package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Tomas on 10/8/13.
 */
public abstract class Icon extends InteractiveBlock {

    protected final SpriteBatch spriteBatch;
    private final BitmapFont font;
    private TextureAtlas.AtlasRegion texture;
    private float size;
    private Vector2 offset;
    private String text;
    private Color color = Color.WHITE;

    public Icon(Gui ui, float x, float y, float width, float height) {
        this(ui, new Rectangle(x, y, width, height));
    }

    public Icon(Gui ui, Rectangle rectangle) {
        super(ui, rectangle);
        this.spriteBatch = ui.getSpriteBatch();
        this.font = ui.getFont();
    }

    @Override
    public void render() {
    }

    private void renderInternal() {
        spriteBatch.begin();
        if (texture != null) {
            spriteBatch.setColor(color);
            spriteBatch.draw(texture, x + offset.x, y + offset.y, size, size);
        }

        if (text != null && !text.equals("")) {
            font.setColor(color);
            BitmapFont.TextBounds fontBounds = font.getBounds(text);
            float x = this.x + (width - fontBounds.width) / 2f;
            font.draw(spriteBatch, text, x, y + fontBounds.height);
        }
        spriteBatch.end();
    }

    public void render(TextureAtlas.AtlasRegion texture, String text) {
        render(texture, text, texture.getRegionWidth(), false);
    }

    public void render(TextureAtlas.AtlasRegion texture, String text, float textureSize, boolean pixelPerfect) {
        if (this.texture != texture) {
            this.texture = texture;
            this.size = pixelPerfect ? Math.round(textureSize / texture.getRegionWidth()) * texture.getRegionWidth() :
                    width;
            this.offset = new Vector2((width - size) / 2, (height - size) / 2);
        }
        this.text = text;

        renderInternal();
    }

    public void render(TextureAtlas.AtlasRegion texture, String text, boolean pixelPerfect) {
        render(texture, text, width, pixelPerfect);
    }

    @Override
    public void refresh() {
        text = "";
        texture = null;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}