package com.xkings.pokemontd.component.attack.projectile;

import com.xkings.pokemontd.App;
import com.xkings.pokemontd.component.attack.AbilityComponent;

/**
 * Created by Tomas on 10/25/13.
 */
public class SunbeamAbility extends AbilityComponent {

    private final float width;
    private final float height;

    public SunbeamAbility(float width, float height) {
        this.width = width * App.WORLD_SCALE;
        this.height = height * App.WORLD_SCALE;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
}
