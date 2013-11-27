package com.pixelthieves.pokemontd;

/**
 * Created by Tomas on 10/5/13.
 */
public class Player {
    private final Treasure treasure = new Treasure();
    private final Health health = new Health(0, 0);
    private final App app;
    private final Score score = new Score(0);
    private final int defaultHealth;
    private final int defaultGold;
    private int freeElements;

    public Player(App app, int health, int gold) {
        this.app = app;
        this.defaultHealth = health;
        this.defaultGold = gold;
        reset();
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public void increaseHealth(int add) {
        health.increase(add);
    }

    public void decreaseHealth(int sub) {
        health.decease(sub);
        if (!health.isAlive()) {
            app.endGame();
        }
    }

    public int getHealth() {
        return health.getCurrentHealth();
    }

    public int getScore() {
        return score.getScore() + treasure.getGold();
    }

    public void addScore(int value) {
        score.increase(value);
    }

    public int getFreeElements() {
        return freeElements;
    }

    public void addFreeElement() {
        this.freeElements++;
    }

    public void subtractFreeElement() {
        this.freeElements--;
    }

    public void reset() {
        score.setScore(0);
        health.setHealth(Integer.MAX_VALUE, defaultHealth);
        treasure.set(defaultGold);
    }
}
