package com.xkings.pokemontd.component.attack.projectile.data;

import com.xkings.pokemontd.component.attack.AbilityComponent;

/**
 * Created by Tomas on 10/13/13.
 */
public class AoeComponent extends AbilityComponent {
    private final float range;

    public AoeComponent(float range) {
        this.range = range;
    }

    public float getRange() {
        return range;
    }
}
