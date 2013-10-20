package com.xkings.pokemontd;

import com.xkings.pokemontd.system.autonomous.DeathSystem;

/**
 * Created by Tomas on 10/5/13.
 */
public class Player {
    private final Treasure treasure;
    private final Health health;
    private int score;

    public Player(int health, int gold, int water, int fire, int nature, int light, int darkness, int neutral,
                  int pure,int score) {
        this.treasure = new Treasure(gold, water, fire, nature, light, darkness, neutral, pure);
        this.health = new Health(health);
        this.score = score;
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public Health getHealth() {
        return health;
    }

    public DeathSystem getScore(){
        return getScore().getScoree();
    }

    public void setScore(int score){
        this.score = score;
    }
}
