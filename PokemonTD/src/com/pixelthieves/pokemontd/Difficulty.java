package com.pixelthieves.pokemontd;

/**
 * Created by Tomas on 11/26/13.
 */
public enum Difficulty {
    Easy(1f), Medium(1.5f), Hard(2.5f), Insane(3f);
    private final float multiplyer;

    Difficulty(float multiplyer) {
        this.multiplyer =  multiplyer;
    }

    public float getMultiplyer() {
        return multiplyer;
    }
}
