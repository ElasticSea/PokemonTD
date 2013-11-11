package com.xkings.pokemontd.component.attack.projectile.data;

import com.xkings.pokemontd.component.attack.AbilityComponent;

/**
 * Created by Tomas on 10/13/13.
 */
public class EffectData extends AbilityComponent {
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

    public String getEffectDescription(float speed, float damage) {
        if (chance == 0) {
            return "Zero chance to ";
        } else if (chance < 1) {
            return "Chance " + (int) (chance * 100) + "% to ";
        } else {
            return "";
        }
    }

}
