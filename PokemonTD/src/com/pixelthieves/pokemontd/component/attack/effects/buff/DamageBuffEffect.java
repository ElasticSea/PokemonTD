package com.pixelthieves.pokemontd.component.attack.effects.buff;

import com.pixelthieves.pokemontd.component.attack.projectile.BuffAbility;

/**
 * Created by Tomas on 10/21/13.
 */
public class DamageBuffEffect extends BuffEffect {

    public DamageBuffEffect set(float duration, float ratio) {
        super.set(BuffAbility.Type.DAMAGE, duration, ratio);
        return this;
    }

}
