package com.xkings.pokemontd.component.attack.projectile.data;

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

    public float getLifeRatioToSteal() {
        return lifeRationToSteal;
    }

    public float getDuration() {
        return duration;
    }
    public String getEffectDescription(float speed,float damage){
        return "asdasd";
    }
}
