package com.xkings.pokemontd.component.attack.effects;

import com.artemis.Component;
import com.xkings.core.logic.Updateable;

/**
 * Created by Tomas on 10/21/13.
 */
public abstract class AbstractEffect extends Component implements Effect, Updateable {
    private float currentTime;
    private final float interval;
    private int iterations;

    protected AbstractEffect(float interval, int iterations) {
        this.interval = interval;
        this.iterations = iterations;
    }

    @Override
    public void update(float delta) {
        currentTime += delta;
    }

    @Override
    public boolean isActive() {
        return true;
    }

    @Override
    public void setActive(boolean active) {
    }

    @Override
    public boolean isReady() {
        if (currentTime >= interval && iterations > 0) {
            currentTime -= interval;
            iterations--;
            return true;
        }
        return false;
    }


    @Override
    public boolean isStarted() {
        return currentTime > 0;
    }

    @Override
    public void reset() {
        // FIXME this is a hack that wont trigger is started, because its not zero but very close to zero.
        currentTime =  Float.MIN_VALUE;
    }
}
