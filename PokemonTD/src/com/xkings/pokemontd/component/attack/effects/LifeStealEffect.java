package com.xkings.pokemontd.component.attack.effects;

/**
 * Created by Tomas on 10/21/13.
 */
public class LifeStealEffect extends  AbstractEffect {
    private final float ratio;
    private float stoleLife;

    public LifeStealEffect(float goalTime, float ratio) {
        super(goalTime);
        this.ratio = ratio;
    }

    public float getRatio() {
        return ratio;
    }

    public void setStoleLife(float stoleLife) {
        this.stoleLife = stoleLife;
    }

    public float getStoleLife() {
        return stoleLife;
    }
}
