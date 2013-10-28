package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.xkings.pokemontd.App;

/**
 * Created by Tomas on 10/8/13.
 */
abstract class Button extends InteractiveBlock {
    private final SpriteBatch spriteBatch;
    private final BitmapFont.HAlignment alignment;
    private String text;
    private final BitmapFont font;
    private Vector2 position;
    private final ShapeRenderer shapeRenderer;
    private final Color color;

    protected Button(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, BitmapFont font) {
        this(rectangle, shapeRenderer, spriteBatch, font, BitmapFont.HAlignment.LEFT, Color.CLEAR);
    }

    protected Button(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, BitmapFont font,
                     BitmapFont.HAlignment alignment) {
        this(rectangle, shapeRenderer, spriteBatch, font, alignment, Color.CLEAR);
    }

    public Button(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, BitmapFont font,
                  BitmapFont.HAlignment alignment, Color color) {
        super(rectangle);
        this.shapeRenderer = shapeRenderer;
        this.spriteBatch = spriteBatch;
        this.text = new String();
        this.color = color;
        this.font = font;
        this.alignment = alignment;
        this.position = recalculatePosition(text);
    }

    public void setScale(float scale) {
        this.font.setScale(scale);
        this.position = recalculatePosition(text);
    }

    @Override
    public void render() {
        if (color != Color.CLEAR || App.DEBUG != null) {
            Color color = isEnabled() ? this.color : Color.DARK_GRAY;
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(App.DEBUG != null ? Color.GREEN : color);
            shapeRenderer.rect(x, y, width, height);
            shapeRenderer.end();
        }
        spriteBatch.begin();
        System.out.println(spriteBatch);
        font.setColor(isEnabled() ? Color.WHITE : Color.GRAY);
        font.drawMultiLine(spriteBatch, text, x, y + position.y, width, alignment);
        spriteBatch.end();
    }

    public void render(String text) {
        if (!text.equals(this.text)) {
            this.text = text;
            recalculatePosition(text);
        }
        render();
    }

    private Vector2 recalculatePosition(String text) {
        BitmapFont.TextBounds fontBounds = font.getBounds(text);
        return new Vector2((width - fontBounds.width) / 2f, (height + fontBounds.height) / 2f);
    }


}