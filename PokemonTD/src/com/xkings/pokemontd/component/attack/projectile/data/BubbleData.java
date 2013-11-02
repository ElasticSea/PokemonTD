package com.xkings.pokemontd.component.attack.projectile.data;

import com.xkings.pokemontd.component.attack.AbilityComponent;

/**
 * Created by Tomas on 10/13/13.
 */
public class BubbleData extends AbilityComponent {
    private final float interval;
    private final float grow;

    public BubbleData(float interval, float grow) {
        this.interval = interval;
        this.grow = grow;
    }

    public float getInterval() {
        return interval;
    }

    public float getGrow() {
        return grow;
    }
}
