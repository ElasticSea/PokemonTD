package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 11/6/13.
 */
public abstract class InteractiveElement extends DisplayBlock implements Clickable {
    public InteractiveElement(Rectangle rectangle) {
        super(rectangle);
    }
}
