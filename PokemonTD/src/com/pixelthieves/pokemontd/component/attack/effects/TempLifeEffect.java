package com.pixelthieves.pokemontd.component.attack.effects;

/**
 * Created by Seda on 10/21/13.
 */
public class TempLifeEffect extends AbstractEffect<TempLifeEffect> {
    private float ratio;
    private float stoleLife;

    public TempLifeEffect set(float interval, float ratio) {
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

    @Override
    public int compareTo(TempLifeEffect o) {
        if (this.ratio > o.ratio) {
            return 1;
        } else if (this.ratio < o.ratio) {
            return -1;
        } else {
            return 0;
        }
    }

    public String getName() {
        return "Life steal";
    }

    public String getDescription() {
        return "Temporarily decreases creep health";
    }
}
