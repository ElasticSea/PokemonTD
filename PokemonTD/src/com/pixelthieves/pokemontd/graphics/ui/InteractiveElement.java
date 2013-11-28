package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Seda on 11/6/13.
 */
public abstract class InteractiveElement extends DisplayBlock implements Clickable {
    public InteractiveElement(Gui gui, Rectangle rectangle) {
        super(gui, rectangle);
    }
}
