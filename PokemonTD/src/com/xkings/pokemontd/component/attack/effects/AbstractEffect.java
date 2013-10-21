package com.xkings.pokemontd.component.attack.effects;

import com.artemis.Component;
import com.xkings.core.logic.Updateable;

/**
 * Created by Tomas on 10/21/13.
 */
public abstract class AbstractEffect extends Component implements Effect, Updateable {
    private float currentTime;
    private final float goalTime;

    protected AbstractEffect(float goalTime) {
        this.goalTime = goalTime;
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
        if (currentTime >= goalTime) {
            currentTime -= goalTime;
            return true;
        }
        return false;
    }


    @Override
    public boolean isStarted() {
        return currentTime > 0;
    }
}
