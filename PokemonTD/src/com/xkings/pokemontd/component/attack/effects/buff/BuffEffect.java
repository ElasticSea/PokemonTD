package com.xkings.pokemontd.component.attack.effects.buff;

import com.xkings.pokemontd.component.attack.effects.AbstractEffect;
import com.xkings.pokemontd.component.attack.projectile.BuffAbility;

/**
 * Created by Tomas on 10/21/13.
 */
public abstract class BuffEffect extends AbstractEffect<BuffEffect> {
    private float ratio;

    public BuffEffect set(BuffAbility.Type type, float duration, float ratio) {
        super.set(type.toString().toLowerCase(), duration, 1);
        this.ratio = ratio;
        return this;
    }

    public float getRatio() {
        return ratio;
    }

    @Override
    public int compareTo(BuffEffect o) {
        if (this.ratio > o.ratio) {
            return 1;
        } else if (this.ratio < o.ratio) {
            return -1;
        } else {
            return 0;
        }
    }

    public String getName(){
        return  "  ";
    }

    public String getDescription(){
        return "  ";
    }
}
