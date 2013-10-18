package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.math.Rectangle;
import com.xkings.core.graphics.Renderable;

/**
 * Created by Tomas on 10/8/13.
 */
abstract class DisplayBlock implements Renderable {
    protected final Rectangle rectangle;

    DisplayBlock(Rectangle rectangle) {
        this.rectangle = rectangle;
    }
}