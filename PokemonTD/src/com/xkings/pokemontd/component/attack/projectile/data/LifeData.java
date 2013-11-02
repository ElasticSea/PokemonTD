package com.xkings.pokemontd.component.attack.projectile.data;

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
    public String setText(){
        return "asdasd";
    }

}
