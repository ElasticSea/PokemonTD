package com.xkings.pokemontd.component;

import com.artemis.Component;

/**
 * Created by Tomas on 10/13/13.
 */
public class DamageComponent extends Component {
    private int damage;

    public DamageComponent(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
