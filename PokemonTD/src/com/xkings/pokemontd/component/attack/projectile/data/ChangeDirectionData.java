package com.xkings.pokemontd.component.attack.projectile.data;

/**
 * Created by Tomas on 10/13/13.
 */
public class ChangeDirectionData extends EffectData {
    private final float earnRatio;

    public ChangeDirectionData(String effect, float earnRatio, float chance) {
        super(effect, chance);
        this.earnRatio = earnRatio;
    }

    public float getEarnRatio() {
        return earnRatio;
    }
}
