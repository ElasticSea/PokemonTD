package com.pixelthieves.pokemontd;

import com.badlogic.gdx.graphics.Color;

/**
 * Created by Tomas on 10/5/13.
 */
public enum Element {
    WATER(Color.BLUE), FIRE(Color.RED), NATURE(Color.GREEN), LIGHT(Color.YELLOW), DARKNESS(Color.MAGENTA),
    PURE(Color.GRAY),
    SOUL(Color.PINK);

    private final Color color;

    private Element(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
}
