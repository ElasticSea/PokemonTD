package com.pixelthieves.pokemontd.component.attack.projectile.data;

import com.pixelthieves.pokemontd.component.attack.EffectName;

/**
 * Created by Tomas on 10/13/13.
 */
public class SlowData extends EffectData {
    private float slowRatio;
    private float duration;

    public SlowData(String effect, float slowRatio, float duration, float chance) {
        super(effect, chance);
        this.slowRatio = slowRatio;
        this.duration = duration;
    }

    public float getSlowRatio() {
        return slowRatio;
    }

    public float getDuration() {
        return duration;
    }

    public String getEffectDescription(EffectName effectName, float speed, float damage) {
        return super.getEffectDescription(effectName, speed, damage);
    }
}
