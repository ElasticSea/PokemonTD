package com.xkings.pokemontd.entity;

import com.xkings.pokemontd.Health;
import com.xkings.pokemontd.Treasure;

/**
 * Created by Tomas on 10/5/13.
 */
public class Player {
    private final Treasure treasure;
    private final Health health;

    public Player(int health, int gold) {
        this.treasure = new Treasure(gold);
        this.health = new Health(health);
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public Health getHealth() {
        return health;
    }
}
