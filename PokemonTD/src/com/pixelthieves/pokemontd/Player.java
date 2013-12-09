package com.pixelthieves.pokemontd;

import com.pixelthieves.pokemontd.system.resolve.ui.LightUpShops;

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
    private Treasure reservedElements = new Treasure();

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
            app.endGame(false);
        }
    }

    public int getHealth() {
        return health.getCurrentHealth();
    }

    public int getScore() {
        return (int) ((score.getScore() + treasure.getGold()) * App.DIFFICULTY.getBonus());
    }

    public void addScore(int value) {
        score.increase(value);
    }

    public int getFreeElements() {
        return freeElements;
    }

    public void addFreeElement() {
        this.freeElements++;
        notifyShops();
    }

    public void subtractFreeElement() {
        this.freeElements--;
        notifyShops();
    }

    private void notifyShops() {
        app.getWorld().getSystem(LightUpShops.class).start();
    }


    public void reset() {
        score.setScore(0);
        health.setHealth(Integer.MAX_VALUE, defaultHealth);
        treasure.set(defaultGold*3000,3,3,3,3,3,1);
        reservedElements.set(0);
    }

    public Treasure getReservedElements() {
        return reservedElements;
    }
}
