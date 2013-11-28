package com.pixelthieves.pokemontd.component.attack.effects;

/**
 * Created by Seda on 10/21/13.
 */
public class DotEffect extends AbstractEffect<DotEffect> {
    private float damage;
    private float dps;

    public DotEffect set(String effect, float interval, int iterations, float dps) {
        super.set(effect, interval, iterations);
        this.dps = dps;
        this.damage = dps * interval / iterations;
        return this;
    }

    public float getDamage() {
        return damage;
    }

    @Override
    public int compareTo(DotEffect o) {
        if (this.dps > o.dps) {
            return 1;
        } else if (this.dps < o.dps) {
            return -1;
        } else {
            return 0;
        }
    }

    public String getName() {
        return "Damage over time";
    }

    public String getDescription() {
        return "Decreases creeps health over time";
    }
}
