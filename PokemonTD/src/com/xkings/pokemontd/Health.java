package com.xkings.pokemontd;

public class Health {

    private int health;
    private int currentHealth;

    public Health(int health) {
        this.health = health;
        this.currentHealth = health;
    }

    public int getLives() {
        return currentHealth;
    }

    public void decrees(int count) {
        currentHealth -= count;
    }

    public float getPercentage() {
        return currentHealth / health;
    }
}
