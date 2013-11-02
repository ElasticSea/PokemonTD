package com.xkings.pokemontd.component.attack.projectile.data;

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
    public String getEffectDescription(float speed,float damage){
        return "asdasd";
    }
}
