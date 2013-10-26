package com.xkings.pokemontd.component.attack.effects.buff;

import com.xkings.pokemontd.component.attack.projectile.BuffAbility;

/**
 * Created by Tomas on 10/21/13.
 */
public class DamageBuffEffect extends BuffEffect {
    private final float ratio;

    public DamageBuffEffect(float duration, float ratio) {
        super(BuffAbility.Type.DAMAGE, duration, 1);
        this.ratio = ratio;
    }

    public float getRatio() {
        return ratio;
    }
}
