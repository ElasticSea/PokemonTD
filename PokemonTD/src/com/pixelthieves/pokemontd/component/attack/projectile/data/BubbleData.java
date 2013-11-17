package com.pixelthieves.pokemontd.component.attack.projectile.data;

import com.pixelthieves.pokemontd.component.attack.EffectName;

/**
 * Created by Tomas on 10/13/13.
 */
public class BubbleData extends EffectData {
    private final float interval;
    private final float grow;

    public BubbleData(float interval, float grow) {
        this.interval = interval;
        this.grow = grow;
    }

    public float getInterval() {
        return interval;
    }

    public float getGrow() {
        return grow;
    }

    @Override
    public String getEffectDescription(EffectName effectName, float speed, float damage) {
        return super.getEffectDescription(effectName, speed, damage)+  "Deals " + damage + " every " + speed + " ms on contact." +
                (grow != 1 ? "Bubble grows by " + (int) ((grow - 1) * 100) + "% per second." : "");
    }
}
