package com.xkings.pokemontd.component.attack.effects;

/**
 * Created by Tomas on 10/21/13.
 */
public class LifeStealEffect extends AbstractEffect<LifeStealEffect> {
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

    @Override
    public int compareTo(LifeStealEffect o) {
        if (this.ratio > o.ratio) {
            return 1;
        } else if (this.ratio < o.ratio) {
            return -1;
        } else {
            return 0;
        }
    }

    public String getName(){
        return  "  ";
    }

    public String getDescription(){
        return "  ";
    }
}
