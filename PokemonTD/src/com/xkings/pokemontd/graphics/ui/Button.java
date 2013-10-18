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
    private final String text;
    private final BitmapFont font;
    private final BitmapFont.TextBounds fontBounds;
    private final Vector2 position;
    private final ShapeRenderer shapeRenderer;

    public Button(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, String text) {
        super(rectangle);
        this.shapeRenderer = shapeRenderer;
        this.spriteBatch = spriteBatch;
        this.text = text;
        this.font = Assets.createFont("pixelFont");
        this.font.setScale(0.25f);
        this.fontBounds = font.getBounds(text);
        this.position =
                new Vector2((rectangle.width - fontBounds.width) / 2f, (rectangle.height + fontBounds.height) / 2f);
    }

    @Override
    public void render() {
        /*shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);
        shapeRenderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
        shapeRenderer.end(); */

        spriteBatch.begin();
        font.draw(spriteBatch, text, rectangle.x + position.x, rectangle.y + position.y);
        spriteBatch.end();
    }

}