package com.xkings.pokemontd.component.attack.projectile.data;

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

    @Override
    public String getEffectName() {
        return "Bonus Damage";
    }

    public String getEffectDescription(float speed, float damage) {
        return super.getEffectDescription(speed, damage) + iterations + "X additional damage";
    }
}
