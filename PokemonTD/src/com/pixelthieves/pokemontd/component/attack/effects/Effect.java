package com.pixelthieves.pokemontd.component.attack.effects;

/**
 * Created by Seda on 10/21/13.
 */
public interface Effect {

    public boolean isReady();

    public boolean isStarted();

    public boolean isFinished();

    public void reset();
}
