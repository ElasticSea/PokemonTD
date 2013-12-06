package com.pixelthieves.pokemontd.component.attack.projectile.data;

import com.pixelthieves.pokemontd.component.attack.EffectName;

/**
 * Created by Tomas on 10/13/13.
 */
public class DotData extends EffectData {
    private final float interval;
    private final int iterations;
    private final float damage;

    public DotData(String effect, int iterations, float interval) {
        this(effect, iterations, interval, 1);
    }

    public DotData(String effect, int iterations, float interval, float damageMultiplier) {
        super(effect);
        this.iterations = iterations;
        this.interval = interval;
        this.damage = damageMultiplier / iterations;
    }

    public float getInterval() {
        return interval;
    }

    public int getIterations() {
        return iterations;
    }

    public float getDamage() {
        return damage;
    }

    @Override
    public String getEffectDescription(EffectName effectName, float speed, float damage) {
        return super.getEffectDescription(effectName, speed, damage) + "Deals " + iterations + "X " + (int) (damage) +
                " every " + interval + " ms.";
    }
}
