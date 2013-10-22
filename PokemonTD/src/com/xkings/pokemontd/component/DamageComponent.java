package com.xkings.pokemontd.component;

import com.artemis.Component;

/**
 * Created by Tomas on 10/13/13.
 */
public class DamageComponent extends Component {
    private float damage;
    private final float dps;

    public DamageComponent(float damage) {
        this(damage,1);
    }

    public DamageComponent(float damage, float speed) {
        this.damage = damage;
        this.dps = damage / speed;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDps() {
        return dps;
    }
}
