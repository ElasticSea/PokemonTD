package com.pixelthieves.pokemontd.component.attack.effects.buff;

import com.pixelthieves.pokemontd.component.attack.projectile.BuffData;

/**
 * Created by Tomas on 10/21/13.
 */
public class SpeedBuffEffect extends BuffEffect {

    public SpeedBuffEffect set(float duration, float ratio) {
        super.set(BuffData.Type.SPEED, duration, ratio);
        return this;
    }

}
