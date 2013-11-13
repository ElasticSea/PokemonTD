package com.xkings.pokemontd.component.attack.projectile.data;

import com.xkings.pokemontd.component.attack.EffectName;

/**
 * Created by Tomas on 10/13/13.
 */
public class MoneyData extends EffectData {
    private final float earnRatio;

    public MoneyData(String effect, float earnRatio) {
        super(effect, 1);
        this.earnRatio = earnRatio;
    }

    public float getEarnRatio() {
        return earnRatio;
    }
    public String getEffectDescription(EffectName effectName, float speed, float damage){
        return super.getEffectDescription(effectName, speed, damage)+  "";
    }
}
