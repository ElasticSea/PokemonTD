package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
public class DisplayText extends Button {

    public DisplayText(Ui ui, Rectangle rectangle, BitmapFont font) {
        super(ui, rectangle, font);
    }

    public DisplayText(Ui ui, Rectangle rectangle, BitmapFont font, BitmapFont.HAlignment alignment) {
        super(ui, rectangle, font, alignment);
    }

    @Override
    public void process(float x, float y) {

    }
}