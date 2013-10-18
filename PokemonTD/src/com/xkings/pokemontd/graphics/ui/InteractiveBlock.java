package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
abstract class InteractiveBlock extends DisplayBlock implements Clickable {

    InteractiveBlock(Rectangle rectangle) {
        super(rectangle);
    }

    @Override
    public boolean hit(float x, float y) {
        if (rectangle.contains(x, y)) {
            process(x - rectangle.width, y - rectangle.height);
            return true;
        }
        return false;
    }

    public abstract void process(float x, float y);
}