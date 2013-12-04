package com.pixelthieves.pokemontd;

public class Health {

    private float maxHealth;
    private float currentHealth;
    private boolean stealLife;
    private boolean earnTreasure = true;
    private boolean destructible;

    public Health(int maxHealth) {
        this(maxHealth, maxHealth, false);
    }

    public Health(int maxHealth, int currentHealth) {
        this(maxHealth, currentHealth, true);
    }

    public Health(int maxHealth, int currentHealth, boolean destructible) {
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
        this.destructible = destructible;
    }

    public void setHealth(int maxHealth, int currentHealth){
        this.maxHealth = maxHealth;
        this.currentHealth = currentHealth;
    }


    public float getRatio() {
        return currentHealth / maxHealth;
    }

    public void decease(float count) {
        if (App.STRESS_TEST == null) {
            System.out.println("Health decreased: " + count);
            currentHealth = Math.max(0, currentHealth - count);
        }
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

    public void setStealLife(boolean stealLife) {
        this.stealLife = stealLife;
    }

    public boolean isStealLife() {
        return stealLife;
    }

    public void setEarnTreasure(boolean earnTreasure) {
        this.earnTreasure = earnTreasure;
    }

    public boolean isEarnTreasure() {
        return earnTreasure;
    }

    public void setDestructible(boolean destructible) {
        this.destructible = destructible;
    }

    public boolean isDestructible() {
        return destructible;
    }

}
