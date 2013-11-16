package com.xkings.pokemontd;

/**
 * Created by Tomas on 10/5/13.
 */
public class Player {
    private final Treasure treasure;
    private final Health health;
    private final App app;
    private int freeElements;
    private int kills;

    public Player(App app, int health, int gold, int freeElements) {
        this.app = app;
        this.treasure = new Treasure(gold, 3, 3, 3, 3, 3, 3, 1);
        this.health = new Health(Integer.MAX_VALUE, health);
        this.freeElements = freeElements;
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

    public Score getScore() {
        return new Score(kills * 100 + treasure.getGold());
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

    public void addKill(int add) {
        kills += add;
    }

    public int getKills() {
        return kills;
    }
}
