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
public class DisplayText extends Button {

    public DisplayText(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, String text) {
        super(rectangle, shapeRenderer, spriteBatch, text);
    }

    public DisplayText(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, String text,
                       BitmapFont.HAlignment alignment) {
        super(rectangle, shapeRenderer, spriteBatch, text, alignment);
    }

    public DisplayText(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, String text,
                       BitmapFont.HAlignment alignment, Color color) {
        super(rectangle, shapeRenderer, spriteBatch, text, alignment, color);
    }

    @Override
    public void process(float x, float y) {

    }
}