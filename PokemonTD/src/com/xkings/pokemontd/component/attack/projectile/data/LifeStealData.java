package com.xkings.pokemontd.component.attack.projectile.data;

import com.xkings.pokemontd.component.attack.AbilityComponent;

/**
 * Created by Tomas on 10/13/13.
 */
public class LifeStealData extends EffectData {
    private final float lifeRationToSteal;
    private final float duration;

    public LifeStealData(float lifeRationToSteal, float duration) {
        super();
        this.lifeRationToSteal = lifeRationToSteal;
        this.duration = duration;
    }

    public float getLifeRationToSteal() {
        return lifeRationToSteal;
    }

    public float getDuration() {
        return duration;
    }
}
