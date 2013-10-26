package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
public class DisplayText extends Button {

    public DisplayText(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch) {
        super(rectangle, shapeRenderer, spriteBatch);
    }

    public DisplayText(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
                       BitmapFont.HAlignment alignment) {
        super(rectangle, shapeRenderer, spriteBatch, alignment);
    }

    public DisplayText(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,
                       BitmapFont.HAlignment alignment, Color color) {
        super(rectangle, shapeRenderer, spriteBatch, alignment, color);
    }

    @Override
    public void process(float x, float y) {

    }
}