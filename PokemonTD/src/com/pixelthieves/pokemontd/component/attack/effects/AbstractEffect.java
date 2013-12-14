package com.pixelthieves.pokemontd.component.attack.effects;

import com.artemis.Component;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.pixelthieves.core.logic.Updateable;
import com.pixelthieves.core.main.Assets;
import com.pixelthieves.pokemontd.App;

/**
 * Created by Tomas on 10/21/13.
 */
public abstract class AbstractEffect<T> extends Component implements Effect, Updateable, Comparable<T> {
    private String effect;
    private int currentIterations;
    private float currentTime;
    private float interval;
    private int iterations;
    private boolean reset;
    private boolean reattach;
    private TextureAtlas.AtlasRegion texture;

    protected void set(String effect, float interval, int iterations) {
        this.effect = effect;
        this.interval = interval;
        this.iterations = iterations;
        this.currentIterations = iterations;
        if (this.getName() != null && this.getName().length() != 0) {
            this.texture = App.getAssets().getTexture("effects/" + this.getName().toLowerCase().replaceAll(" ", ""));
        }
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
        if (currentTime >= interval && currentIterations > 0) {
            currentTime -= interval;
            currentIterations--;
            return true;
        }
        return false;
    }

    @Override
    public boolean isFinished() {
        return currentIterations == 0;
    }

    @Override
    public boolean isStarted() {
        return currentTime > 0;
    }

    @Override
    public void reset() {
        // FIXME this is a hack that wont trigger is started, because its not zero but very restart to zero.
        if (isStarted()) {
            currentIterations = iterations;
            currentTime = Float.MIN_VALUE;
            reset = true;
        }
    }

    public String getEffect() {
        return effect;
    }

    public void reattach() {
        reattach = true;
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public boolean isReattach() {
        return reattach;
    }

    public void setReattach(boolean reattach) {
        this.reattach = reattach;
    }

    public TextureAtlas.AtlasRegion getTexture() {
        return texture;
    }

    public abstract String getName();

    public abstract String getDescription();
}
