package com.xkings.pokemontd.component.attack;

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
