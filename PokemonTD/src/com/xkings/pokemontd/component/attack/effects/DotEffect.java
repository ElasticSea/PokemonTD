package com.xkings.pokemontd.component.attack.effects;

/**
 * Created by Tomas on 10/21/13.
 */
public class DotEffect extends  AbstractEffect {
    private final float dps;

    public DotEffect(float interval, int iterations, float dps) {
        super(interval,iterations);
        this.dps = dps;
    }

    public float getDps() {
        return dps;
    }
}
