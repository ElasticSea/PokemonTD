package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.pixelthieves.pokemontd.App;

/**
 * Created by Tomas on 10/8/13.
 */
public abstract class Button extends InteractiveBlock {
    private final SpriteBatch spriteBatch;
    private final BitmapFont.HAlignment alignment;
    private String text;
    private final BitmapFont font;
    private Vector2 position;
    private final ShapeRenderer shapeRenderer;
    private Color color = Color.CLEAR;
    private Color backgroundColor = Color.CLEAR;
    private final boolean wrapped;
    private BitmapFont.TextBounds fontBounds;

    protected Button(Gui gui, float x, float y, float width, float height, BitmapFont font) {
        this(gui, new Rectangle(x, y, width, height), font, BitmapFont.HAlignment.CENTER, false);
    }

    protected Button(Gui gui, Rectangle rectangle, BitmapFont font) {
        this(gui, rectangle, font, BitmapFont.HAlignment.CENTER, false);
    }


    protected Button(Gui gui, Rectangle rectangle, BitmapFont font, BitmapFont.HAlignment alignment) {
        this(gui, rectangle, font, alignment, false);
    }

    protected Button(Gui gui, Rectangle rectangle, BitmapFont font, BitmapFont.HAlignment alignment, boolean wrapped) {
        super(rectangle);
        this.shapeRenderer = gui.getShapeRenderer();
        this.spriteBatch = gui.getSpriteBatch();
        this.text = new String();
        this.font = font;
        this.alignment = alignment;
        this.wrapped = wrapped;
        this.position = recalculatePosition(text);
    }

    public void setScale(float scale) {
        this.font.setScale(scale);
        this.position = recalculatePosition(text);
    }

    @Override
    public void render() {
        if (backgroundColor != Color.CLEAR || App.DEBUG != null) {
            Color color = isEnabled() ? this.backgroundColor : Color.DARK_GRAY;
            shapeRenderer.begin(App.DEBUG != null ? ShapeRenderer.ShapeType.Line : ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(App.DEBUG != null ? Color.GREEN : color);
            shapeRenderer.rect(x, y, width, height);
            shapeRenderer.end();
        }
        spriteBatch.begin();
        font.setColor(isEnabled() ? color : Color.GRAY);
        if (wrapped) {
            font.drawWrapped(spriteBatch, text, x, y + height, width, alignment);
        } else {
            font.drawMultiLine(spriteBatch, text, x, y + position.y, width, alignment);
        }
        spriteBatch.end();
    }

    public void render(String text) {
        render(text, Color.WHITE);
    }

    public void render(String text, Color color) {
        render(text, color, Color.CLEAR);
    }


    public void render(String text, Color color, Color backgroundColor) {
        if (!text.equals(this.text)) {
            this.text = text;
            recalculatePosition(text);
        }
        this.color = color;
        this.backgroundColor = backgroundColor;
        render();
    }

    private Vector2 recalculatePosition(String text) {
        fontBounds = font.getBounds(text);
        return new Vector2((width - fontBounds.width) / 2f, (height + fontBounds.height) / 2f);
    }

    @Override
    public void refresh() {
        this.position = recalculatePosition(text);
    }

    public float getTextHeight() {
        return fontBounds.height;
    }

    public float getTextWidth() {
        return fontBounds.width;
    }
}