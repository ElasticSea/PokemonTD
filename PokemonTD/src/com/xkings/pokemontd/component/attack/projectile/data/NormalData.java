package com.xkings.pokemontd.component.attack.projectile.data;

/**
 * Created by Tomas on 10/13/13.
 */
public class NormalData extends EffectData {
    @Override
    public String getEffectName() {
        throw new IllegalStateException("This method should not be invoked on NormalData");
    }

    public String getEffectDescription(float speed, float damage) {
        throw new IllegalStateException("This method should not be invoked on NormalData");
    }
}
