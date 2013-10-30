package com.xkings.pokemontd.component.attack.effects;

/**
 * Created by Tomas on 10/21/13.
 */
public class SlowEffect extends AbstractEffect<SlowEffect> {
    private float slowRatio;
    private float oldSpeed;

    public SlowEffect set(String effect, float interval, float slowRatio) {
        super.set(effect, interval, 1);
        this.slowRatio = slowRatio;
        System.out.println(this+" setting slow ratio: "+slowRatio);
        return this;
    }

    public float getSlowRatio() {
        System.out.println(this+" Getting slow ratio: "+slowRatio);
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
}

