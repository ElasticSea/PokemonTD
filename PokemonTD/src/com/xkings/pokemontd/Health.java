package com.xkings.pokemontd;

public class Health {

    private float maxHealth;
    private float currentHealth;

    public Health(int maxHealth) {
        this.maxHealth = maxHealth;
        this.currentHealth = maxHealth;
    }

    public float getRatio() {
        return currentHealth / maxHealth;
    }

    public void decrees(float count) {
        currentHealth = Math.max(0, currentHealth - count);
    }

    public void increase(float count) {
        currentHealth = Math.min(maxHealth, currentHealth + count);
    }

    public int getCurrentHealth() {
        return (int) currentHealth;
    }

    public int getMaxHealth() {
        return (int) maxHealth;
    }

    public boolean isAlive() {
        return currentHealth > 0;
    }
}
