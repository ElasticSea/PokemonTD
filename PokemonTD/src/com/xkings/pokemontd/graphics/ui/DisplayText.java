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

    public DisplayText(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, BitmapFont font) {
        super(rectangle, shapeRenderer, spriteBatch, font);
    }

    public DisplayText(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch, BitmapFont font,
                       BitmapFont.HAlignment alignment) {
        super(rectangle, shapeRenderer, spriteBatch, font, alignment);
    }

    public DisplayText(Rectangle rectangle, ShapeRenderer shapeRenderer, SpriteBatch spriteBatch,BitmapFont font,
                       BitmapFont.HAlignment alignment, Color color) {
        super(rectangle, shapeRenderer, spriteBatch, font, alignment, color);
    }

    @Override
    public void process(float x, float y) {

    }
}