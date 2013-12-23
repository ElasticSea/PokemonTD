package com.pixelthieves.elementtd.component.attack.effects;

/**
 * Created by Tomas on 10/21/13.
 */
public class DotEffect extends AbstractEffect<DotEffect> {
    private float damage;
    private float maxDamage;

    public DotEffect set(String effect, float interval, int iterations, float damage) {
        super.set(effect, interval, iterations);
        this.damage = damage;
        this.maxDamage = damage *  iterations;
        return this;
    }

    public float getDamage() {
        return damage;
    }

    @Override
    public int compareTo(DotEffect o) {
        if (this.maxDamage > o.maxDamage) {
            return 1;
        } else if (this.maxDamage < o.maxDamage) {
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
