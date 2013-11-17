package com.pixelthieves.pokemontd.component.attack.projectile.data;

import com.pixelthieves.pokemontd.component.attack.EffectName;

/**
 * Created by Tomas on 10/13/13.
 */
public class ChangeDirectionData extends EffectData {

    private final float duration;

    public ChangeDirectionData(float duration, float chance) {
        super("", chance);
        this.duration = duration;
    }

    public float getDuration() {
        return duration;
    }

    @Override
    public String getEffectDescription(EffectName effectName, float speed, float damage) {
        return super.getEffectDescription(effectName, speed, damage)+  "asd";
    }
}
