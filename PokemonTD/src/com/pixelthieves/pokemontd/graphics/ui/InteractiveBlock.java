package com.pixelthieves.pokemontd.graphics.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Tomas on 10/8/13.
 */
abstract class InteractiveBlock extends InteractiveElement {

    private boolean enabled = true;

    InteractiveBlock(Rectangle rectangle) {
        super(rectangle);
    }

    @Override
    public boolean hit(float x, float y) {
        y = Gdx.graphics.getHeight() - y;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rectangle)) return false;

        Rectangle that = (Rectangle) o;

        if (x != that.x || y != that.y || height != that.height || width != that.width) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (enabled ? 1 : 0);
    }

    public abstract void process(float x, float y);
}