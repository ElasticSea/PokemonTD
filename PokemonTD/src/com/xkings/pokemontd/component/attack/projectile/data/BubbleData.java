package com.xkings.pokemontd.component.attack.projectile.data;

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
    public String getEffectDescription(float speed, float damage) {
        return " ";
    }
}
