package com.pixelthieves.pokemontd;

/**
 * Created by Tomas on 11/26/13.
 */
public enum Difficulty {
    Easy(0.7f, 0.5f), Normal(0.7f, 1f), Hard(1f, 2f), Insane(1.75f, 7.5f), Impossible(2.1f, 12f);
    private final float multiplyer;
    private final float bonus;

    Difficulty(float multiplyer, float bonus) {
        this.multiplyer = multiplyer;
        this.bonus = bonus;
    }

    public float getMultiplyer() {
        return multiplyer;
    }

    public float getBonus() {
        return bonus;
    }
}
