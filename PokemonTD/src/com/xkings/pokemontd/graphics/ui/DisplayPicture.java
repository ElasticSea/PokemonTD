package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
public class DisplayPicture extends Icon {

    DisplayPicture(Gui ui, float x, float y, float width, float height) {
        super(ui, x, y, width, height);
    }

    DisplayPicture(Gui ui, Rectangle rectangle) {
        super(ui, rectangle);
    }

    @Override
    public void process(float x, float y) {

    }

}