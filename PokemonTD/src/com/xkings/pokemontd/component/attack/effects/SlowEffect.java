package com.xkings.pokemontd.component.attack.effects;

/**
 * Created by Tomas on 10/21/13.
 */
public class SlowEffect extends AbstractEffect {
    private final float slowRatio;
    private float oldSpeed;

    public SlowEffect(String effect,float interval, float slowRatio) {
        super(effect,interval, 1);
        this.slowRatio = slowRatio;
    }

    public float getSlowRatio() {
        return slowRatio;
    }

    public void setOldSpeed(float oldSpeed) {
        this.oldSpeed = oldSpeed;
    }

    public float getOldSpeed() {
        return oldSpeed;
    }
}
