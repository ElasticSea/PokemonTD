package com.pixelthieves.pokemontd.graphics.ui;

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
        super(gui, rectangle, font, alignment, false, new String());
    }

    public DisplayText(Gui gui, Rectangle rectangle, BitmapFont font, BitmapFont.HAlignment alignment, boolean wrapped,
                       String text) {
        super(gui, rectangle, font, alignment, wrapped, text);
    }

    @Override
    public void process(float x, float y) {

    }
}