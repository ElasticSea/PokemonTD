package com.xkings.pokemontd.component.attack.effects;

/**
 * Created by Tomas on 10/21/13.
 */
public class SlowEffect extends AbstractEffect {
    private float slowRatio;
    private float oldSpeed;

    public float getSlowRatio() {
        return slowRatio;
    }

    public SlowEffect set(String effect, float interval, float slowRatio) {
        super.set(effect, interval, 1);
        this.slowRatio = slowRatio;
        return this;
    }

    public void setOldSpeed(float oldSpeed) {
        this.oldSpeed = oldSpeed;
    }

    public float getOldSpeed() {
        return oldSpeed;
    }
}

