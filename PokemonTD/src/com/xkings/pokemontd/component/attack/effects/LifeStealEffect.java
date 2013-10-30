package com.xkings.pokemontd.component.attack.effects;

/**
 * Created by Tomas on 10/21/13.
 */
public class LifeStealEffect extends AbstractEffect {
    private float ratio;
    private float stoleLife;

    public LifeStealEffect set(float interval, float ratio) {
        super.set("lifesteal", interval, 1);
        this.ratio = ratio;
        return this;
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
