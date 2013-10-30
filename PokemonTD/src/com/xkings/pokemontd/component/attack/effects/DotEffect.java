package com.xkings.pokemontd.component.attack.effects;

/**
 * Created by Tomas on 10/21/13.
 */
public class DotEffect extends AbstractEffect {
    private float damage;

    public DotEffect set(String effect, float interval, int iterations, float damage) {
        super.set(effect, interval, iterations);
        this.damage = damage;
        return this;
    }

    public float getDamage() {
        return damage;
    }
}
