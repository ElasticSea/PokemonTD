package com.xkings.pokemontd.component;

import com.artemis.Component;
import com.xkings.pokemontd.component.attack.effects.buff.DamageBuffEffect;

/**
 * Created by Tomas on 10/13/13.
 */
public class DamageComponent extends Component {
    private float damage;
    private final float dps;
    private DamageBuffEffect buff;

    public DamageComponent(float damage) {
        this(damage, 1);
    }

    public DamageComponent(float damage, float speed) {
        this.damage = damage;
        this.dps = damage / speed;
    }

    public float getDamage() {
        return damage * (buff != null ? buff.getRatio() : 1);
    }

    public void setDamage(float damage) {
        this.damage = damage;
    }

    public float getDps() {
        return dps;
    }

    public void setBuff(DamageBuffEffect buff) {
        this.buff = buff;
    }
}
