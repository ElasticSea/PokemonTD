package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.xkings.core.main.Assets;

/**
 * Created by Tomas on 10/8/13.
 */
abstract class Button extends InteractiveBlock {
    private final SpriteBatch spriteBatch;
    private final BitmapFont.HAlignment alignment;
    private String text;
    private final BitmapFont font;
    private final Vector2 position;
    private final ShapeRenderer shapeRenderer;
    private final Color color;

    protected Button(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        this(rectangle, shapeRenderer, spriteBatch, BitmapFont.HAlignment.LEFT, Color.CLEAR);
    }

    protected Button(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
                     BitmapFont.HAlignment alignment) {
        this(rectangle, shapeRenderer, spriteBatch, alignment, Color.CLEAR);
    }

    public Button(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
                  BitmapFont.HAlignment alignment, Color color) {
        super(rectangle);
        this.shapeRenderer = shapeRenderer;
        this.spriteBatch = spriteBatch;
        this.text = new String();
        this.color = color;
        this.font = Assets.createFont("pixelFont");
        this.font.setScale(0.50f);
        this.position = recalculatePosition(text);
        this.alignment = alignment;
    }


    @Override
    public void render() {
        if (color != Color.CLEAR) {
            Color color = isEnabled() ? this.color : Color.DARK_GRAY;
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(color);
            shapeRenderer.rect(x, y, width, height);
            shapeRenderer.end();
        }
        spriteBatch.begin();
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