package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
public class DisplayText extends Button {

    public DisplayText(Gui gui, Rectangle rectangle, BitmapFont font) {
        super(gui, rectangle, font);
    }

    public DisplayText(Gui gui, Rectangle rectangle, BitmapFont font, BitmapFont.HAlignment alignment) {
        super(gui, rectangle, font, alignment);
    }

    @Override
    public void process(float x, float y) {

    }
}