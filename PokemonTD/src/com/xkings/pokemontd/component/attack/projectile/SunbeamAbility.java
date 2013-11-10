package com.xkings.pokemontd.component.attack.projectile;

import com.xkings.pokemontd.App;
import com.xkings.pokemontd.component.attack.projectile.data.EffectData;

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

    @Override
    public String getEffectName() {
        return "Sun Beam";
    }

    public String getEffectDescription(float speed,float damage){
        return "asdasdasdasd";
    }

}
