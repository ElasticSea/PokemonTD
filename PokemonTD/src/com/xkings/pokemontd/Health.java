package com.xkings.pokemontd;

public class Health {

    private int health;

    public Health(int health) {
        this.health = health;
    }

    public int getLives() {
        return health;
    }

    public void decrees(int count) {
        health -= count;
    }
}
