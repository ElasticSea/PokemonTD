package com.xkings.pokemontd.component.attack.effects.buff;

import com.xkings.pokemontd.component.attack.effects.AbstractEffect;
import com.xkings.pokemontd.component.attack.projectile.BuffAbility;

/**
 * Created by Tomas on 10/21/13.
 */
public abstract class BuffEffect extends AbstractEffect {
    private final float ratio;

    public BuffEffect(BuffAbility.Type type, float duration, float ratio) {
        super(type.toString().toLowerCase(), duration, 1);
        this.ratio = ratio;
    }

    public float getRatio() {
        return ratio;
    }
}
