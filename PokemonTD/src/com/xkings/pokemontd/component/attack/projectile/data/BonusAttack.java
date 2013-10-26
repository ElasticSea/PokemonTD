package com.xkings.pokemontd.component.attack.projectile.data;

import com.xkings.pokemontd.component.attack.AbilityComponent;

/**
 * Created by Tomas on 10/13/13.
 */
public class BonusAttack extends AbilityComponent {
    private final float chance;
    private final int iterations;

    public BonusAttack(float chance, int iterations) {
        this.chance = chance;
        this.iterations = iterations;
    }

    public float getChance() {
        return chance;
    }

    public int getIterations() {
        return iterations;
    }
}
