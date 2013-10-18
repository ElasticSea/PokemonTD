package com.xkings.pokemontd;

public class Health {

    private int health;
    private int currentHealth;

    public Health(int health) {
        this.health = health;
        this.currentHealth = health;
    }

    public int getHealth() {
        return currentHealth;
    }

    public void decrees(int count) {
        currentHealth = Math.max(0, currentHealth - count);
    }

    public float getRatio() {
        return (float) currentHealth / health;
    }
}
