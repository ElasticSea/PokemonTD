package com.pixelthieves.elementtd.component.attack.projectile.data;

import com.pixelthieves.elementtd.component.attack.EffectName;

/**
 * Created by Tomas on 10/13/13.
 */
public class TempLifeData extends EffectData {
    private final float duration;

    public TempLifeData(float duration) {
        super();
        this.duration = duration;
    }

    public float getDuration() {
        return duration;
    }

    public String getEffectDescription(EffectName effectName, float speed, float damage) {
        return super.getEffectDescription(effectName, speed, damage) + "temporary steal " + (int) (damage * 100) +
                "% of creeps health for " + duration + "s.";
    }
}
