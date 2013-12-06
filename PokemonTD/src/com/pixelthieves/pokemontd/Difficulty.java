package com.pixelthieves.pokemontd;

/**
 * Created by Tomas on 11/26/13.
 */
public enum Difficulty {
    Easy(1f, 1f), Normal(1.5f, 2f), Hard(2.5f, 7.5f), Insane(3f, 12f);
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
        return multiplyer;
    }
}
