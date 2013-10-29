package com.xkings.pokemontd.component.attack.projectile.data;

import com.xkings.pokemontd.component.attack.AbilityComponent;

/**
 * Created by Tomas on 10/13/13.
 */
public class EffectData extends AbilityComponent {
    private final float interval;
    private final int iterations;
    private final float damageMultiplier;
    private final String effect;

    public EffectData(String effect, int iterations, float interval) {
        this(effect, iterations, interval, 1);
    }

    public EffectData(String effect, int iterations, float interval, float damageMultiplier) {
        this.effect = effect;
        this.iterations = iterations;
        this.interval = interval;
        this.damageMultiplier = damageMultiplier;
    }

    public float getInterval() {
        return interval;
    }

    public int getIterations() {
        return iterations;
    }

    public float getDamageMultiplier() {
        return damageMultiplier;
    }
}
