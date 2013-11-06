package com.xkings.pokemontd.component.attack.projectile.data;

import com.xkings.pokemontd.component.attack.AbilityComponent;

/**
 * Created by Tomas on 10/13/13.
 */
public abstract class EffectData extends AbilityComponent {
    private final String effect;
    private double chance;

    public EffectData() {
        this("", 1);
    }

    public EffectData(String effect) {
        this(effect, 1);
    }

    public EffectData(String effect, float chance) {
        this.effect = effect;
        this.chance = chance;
    }

    public String getEffect() {
        return effect;
    }

    public double getChance() {
        return chance;
    }

    public void setChance(double chance) {
        this.chance = chance;
    }

    public abstract String getEffectDescription(float speed,float damage);

}
