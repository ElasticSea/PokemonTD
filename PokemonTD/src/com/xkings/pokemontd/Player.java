package com.xkings.pokemontd;

/**
 * Created by Tomas on 10/5/13.
 */
public class Player {
    private final Treasure treasure;
    private final Health health;
    private final Score score;

    public Player(int health, int gold, int water, int fire, int nature, int light, int darkness, int neutral,
                  int pure) {
        this.treasure = new Treasure(gold, water, fire, nature, light, darkness, neutral, pure);
        this.health = new Health(health);
        this.score = new Score(0);
    }

    public Treasure getTreasure() {
        return treasure;
    }

    public Health getHealth() {
        return health;
    }

   public Score getScore(){
        return score;
    }


}
