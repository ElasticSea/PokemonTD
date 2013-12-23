package com.pixelthieves.elementtd.graphics.ui;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 11/6/13.
 */
public abstract class InteractiveElement extends DisplayBlock implements Clickable {
    public InteractiveElement(Gui gui, Rectangle rectangle) {
        super(gui, rectangle);
    }
}
