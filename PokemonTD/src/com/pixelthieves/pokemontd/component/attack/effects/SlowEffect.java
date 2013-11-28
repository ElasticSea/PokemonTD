package com.pixelthieves.pokemontd.component.attack.effects;

/**
 * Created by Seda on 10/21/13.
 */
public class SlowEffect extends AbstractEffect<SlowEffect> {
    private float slowRatio;
    private float oldSpeed;

    public SlowEffect set(String effect, float interval, float slowRatio) {
        super.set(effect, interval, 1);
        this.slowRatio = slowRatio;
        return this;
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

    @Override
    public int compareTo(SlowEffect o) {
        if (this.slowRatio > o.slowRatio) {
            return 1;
        } else if (this.slowRatio < o.slowRatio) {
            return -1;
        } else {
            return 0;
        }
    }

    public String getName() {
        return "Slow";
    }

    public String getDescription() {
        return "Decreases creep movement speed.";
    }
}

