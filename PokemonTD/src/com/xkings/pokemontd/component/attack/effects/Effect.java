package com.xkings.pokemontd.component.attack.effects;

/**
 * Created by Tomas on 10/21/13.
 */
public interface Effect {

    public boolean isReady();
    public boolean isStarted();
    public boolean isFinished();
    public void reset();
}
