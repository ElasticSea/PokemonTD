package com.xkings.pokemontd.component.attack.projectile.data;

import com.xkings.pokemontd.component.attack.AbilityComponent;

/**
 * Created by Tomas on 10/13/13.
 */
public class SlowData extends AbilityComponent {
    private float slowRatio;
    private float duration;
    private float chance;

    public SlowData(float slowRatio, float duration, float chance) {
        this.slowRatio = slowRatio;
        this.duration = duration;
        this.chance = chance;
    }

    public float getSlowRatio() {
        return slowRatio;
    }

    public float getDuration() {
        return duration;
    }

    public float getChance() {
        return chance;
    }
}
