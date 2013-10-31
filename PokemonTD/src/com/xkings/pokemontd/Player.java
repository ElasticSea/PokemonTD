package com.xkings.pokemontd;

/**
 * Created by Tomas on 10/5/13.
 */
public class Player {
    private final Treasure treasure;
    private final Health health;
    private final Score score;
    private int freeElements;

    public Player(int health, int gold, int freeElements) {
        this.treasure = new Treasure(gold);
        this.health = new Health(health);
        this.score = new Score(0);
        this.freeElements = freeElements;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public Health getHealth() {
        return health;
    }

    public Score getScore() {
        return score;
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
