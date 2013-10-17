package com.xkings.pokemontd.component;

import com.artemis.Component;

/**
 * Created by Tomas on 10/13/13.
 */
public class DamageComponent extends Component {
    private float damage;

    public DamageComponent(float damage) {
        this.damage = damage;
    }

    public float getDamage() {
        return damage;
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }
}
