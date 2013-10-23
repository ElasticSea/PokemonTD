package com.xkings.pokemontd.graphics.ui;

import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
abstract class InteractiveBlock extends DisplayBlock implements Clickable {

    private boolean enabled = true;

    InteractiveBlock(Rectangle rectangle) {
        super(rectangle);
    }

    @Override
    public boolean hit(float x, float y) {
        if (isEnabled() && contains(x, y)) {
            process(x - width, y - height);
            return true;
        }
        return false;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public abstract void process(float x, float y);
}