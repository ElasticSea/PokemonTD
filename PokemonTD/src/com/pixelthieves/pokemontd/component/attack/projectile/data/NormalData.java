package com.pixelthieves.pokemontd.component.attack.projectile.data;

import com.pixelthieves.pokemontd.component.attack.EffectName;

/**
 * Created by Seda on 10/13/13.
 */
public class NormalData extends EffectData {
    public String getEffectDescription(EffectName effectName, float speed, float damage) {
        throw new IllegalStateException("This method should not be invoked on NormalData");
    }
}
