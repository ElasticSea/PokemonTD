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
    private String text;
    private final BitmapFont font;
    private final Vector2 position;
    private final ShapeRenderer shapeRenderer;
    private final Color color;

    protected Button(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, String text) {
        this(rectangle, shapeRenderer, spriteBatch, text, Color.CLEAR);
    }

    public Button(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, String text, Color color) {
        super(rectangle);
        this.shapeRenderer = shapeRenderer;
        this.spriteBatch = spriteBatch;
        this.text = text;
        this.color = color;
        this.font = Assets.createFont("pixelFont");
        this.font.setScale(0.50f);
        this.position = recalculatePosition(text);
    }


    @Override
    public void render() {
        if (color != Color.CLEAR) {
            shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
            shapeRenderer.setColor(color);
            shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
            shapeRenderer.end();
        }

        spriteBatch.begin();
        font.draw(spriteBatch, text, rectangle.x + position.x, rectangle.y + position.y);
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
        return new Vector2((rectangle.width - fontBounds.width) / 2f, (rectangle.height + fontBounds.height) / 2f);
    }

}