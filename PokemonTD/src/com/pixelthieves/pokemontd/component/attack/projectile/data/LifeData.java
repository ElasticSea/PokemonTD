package com.pixelthieves.pokemontd.component.attack.projectile.data;

import com.pixelthieves.pokemontd.component.attack.EffectName;

/**
 * Created by Tomas on 10/13/13.
 */
public class LifeData extends EffectData {
    private final float earnRatio;

    public LifeData(String effect, float earnRatio, float chance) {
        super(effect, chance);
        this.earnRatio = earnRatio;
    }

    public float getEarnRatio() {
        return earnRatio;
    }

    public String getEffectDescription(EffectName effectName, float speed, float damage) {
        return super.getEffectDescription(effectName, speed, damage)+  "drop " + earnRatio + "X life instead of gold";
    }

}
