package com.xkings.pokemontd.component.attack;

/**
 * Created by Tomas on 10/13/13.
 */
public class LifeStealData extends AbilityComponent {
    private final float lifeRationToSteal;
    private final float duration;

    public LifeStealData(float lifeRationToSteal, float duration) {
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
