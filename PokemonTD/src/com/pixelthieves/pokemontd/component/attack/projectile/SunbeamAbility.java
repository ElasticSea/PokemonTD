package com.pixelthieves.pokemontd.component.attack.projectile;

import com.pixelthieves.pokemontd.App;
import com.pixelthieves.pokemontd.component.attack.EffectName;
import com.pixelthieves.pokemontd.component.attack.projectile.data.EffectData;

/**
 * Created by Tomas on 10/25/13.
 */
public class SunbeamAbility extends EffectData {

    private final float width;
    private final float height;

    public SunbeamAbility(float width, float height) {
        this.width = width * App.WORLD_SCALE;
        this.height = height * App.WORLD_SCALE;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public String getEffectDescription(EffectName effectName, float speed, float damage) {
        return "asdasdasdasd";
    }

}
