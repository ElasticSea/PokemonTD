package com.xkings.pokemontd.component.attack.projectile.data;

import com.xkings.pokemontd.component.attack.EffectName;

/**
 * Created by Tomas on 10/13/13.
 */
public class BonusAttack extends EffectData {
    private final int iterations;

    public BonusAttack(float chance, int iterations) {
        super("", chance);
        this.iterations = iterations;
    }

    public int getIterations() {
        return iterations;
    }

    public String getEffectDescription(EffectName effectName, float speed, float damage) {
        return super.getEffectDescription(effectName, speed, damage) + iterations + "X additional damage";
    }
}
