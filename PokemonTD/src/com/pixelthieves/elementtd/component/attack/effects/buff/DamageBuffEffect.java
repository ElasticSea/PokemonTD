package com.pixelthieves.elementtd.component.attack.effects.buff;

import com.pixelthieves.elementtd.component.attack.projectile.BuffData;

/**
 * Created by Tomas on 10/21/13.
 */
public class DamageBuffEffect extends BuffEffect {

    public DamageBuffEffect set(float duration, float ratio) {
        super.set(BuffData.Type.DAMAGE, duration, ratio);
        return this;
    }

}
