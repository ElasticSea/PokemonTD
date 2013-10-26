package com.xkings.pokemontd.component.attack.projectile.data;

import com.xkings.pokemontd.component.attack.AbilityComponent;

/**
 * Created by Tomas on 10/13/13.
 */
public class BubbleData extends AbilityComponent {
    private final float interval;

    public BubbleData(float interval) {
        this.interval = interval;
    }

    public float getInterval() {
        return interval;
    }
}
