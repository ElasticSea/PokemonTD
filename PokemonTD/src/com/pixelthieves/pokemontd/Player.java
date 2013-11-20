package com.pixelthieves.pokemontd;

/**
 * Created by Tomas on 10/5/13.
 */
public class Player {
    private final Treasure treasure;
    private final Health health;
    private final App app;
    private final Score score;
    private int freeElements;

    public Player(App app, int health, int gold, int freeElements) {
        this.app = app;
        this.treasure = new Treasure(gold);
        this.health = new Health(Integer.MAX_VALUE, health);
        this.freeElements = freeElements;
        this.score = new Score(0);
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
}
