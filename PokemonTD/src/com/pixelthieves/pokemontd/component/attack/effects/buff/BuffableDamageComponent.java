package com.pixelthieves.pokemontd.component.attack.effects.buff;

import com.pixelthieves.core.component.DamageComponent;

/**
 * Created by Tomas on 10/30/13.
 */
public class BuffableDamageComponent extends DamageComponent {
    private DamageBuffEffect buff;

    public BuffableDamageComponent(float damage, float speed) {
        super(damage, speed);
    }

    public BuffableDamageComponent(float damage) {
        super(damage);
    }

    public void setBuff(DamageBuffEffect buff) {
        this.buff = buff;
    }

    public DamageBuffEffect getBuff() {
        return buff;
    }

    @Override
    public float getDamage() {
        return super.getDamage() * (buff != null ? buff.getRatio() : 1);
    }

    public String getName(){
        return  "  ";
    }

    public String getDescription(){
        return "  ";
    }
}
