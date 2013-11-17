package com.pixelthieves.pokemontd.component.attack.effects;

/**
 * Created by Tomas on 10/21/13.
 */
public class IncreasingDamageEffect extends AbstractEffect<IncreasingDamageEffect> {
    private float damageIncreasing;
    private float damage;
    private float max;
    private float currentDamage;

    public IncreasingDamageEffect set(String effect, float damageIncreasing, float duration, float damage, float max) {
        super.set(effect, duration, 1);
        this.damageIncreasing = damageIncreasing;
        this.damage = damage;
        this.max = max;
        this.currentDamage = Math.min(currentDamage == 0 ? damageIncreasing : currentDamage * damageIncreasing, max);
        return this;
    }

    public float getDamageIncreasing() {
        return damageIncreasing;
    }

    public float getMax() {
        return max;
    }

    public float getCurrentDamage() {
        return currentDamage*damage;
    }

    @Override
    public int compareTo(IncreasingDamageEffect o) {
        if (this.max > o.max) {
            return 1;
        } else if (this.max < o.max) {
            return -1;
        } else {
            return 0;
        }
    }

    public String getName(){
        return  "Increasing damage";
    }

    public String getDescription(){
        return "Increases creep damage";
    }
}

