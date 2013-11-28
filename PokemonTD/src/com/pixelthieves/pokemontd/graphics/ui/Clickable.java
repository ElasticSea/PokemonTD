package com.pixelthieves.pokemontd.graphics.ui;

/**
 * Created by Seda on 10/18/13.
 */
public interface Clickable {

    boolean hit(float x, float y);

    public boolean isEnabled();

    public void setEnabled(boolean enabled);
}
