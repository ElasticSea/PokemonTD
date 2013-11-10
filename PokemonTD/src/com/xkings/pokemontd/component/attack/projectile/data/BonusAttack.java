package com.xkings.pokemontd.component.attack.projectile.data;

/**
 * Created by Tomas on 10/13/13.
 */
public class BonusAttack extends EffectData {
    private final float chance;
    private final int iterations;

    public BonusAttack(float chance, int iterations) {
        super("", chance);
        this.chance = chance;
        this.iterations = iterations;
    }

    public int getIterations() {
        return iterations;
    }
    public String getEffectDescription(float speed,float damage){
        return "Increase attack of nearby towers";
    }
}
