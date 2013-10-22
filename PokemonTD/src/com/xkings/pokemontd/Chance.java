package com.xkings.pokemontd;

import java.math.BigDecimal;
import java.util.Random;

/**
 * Created by Tomas on 10/22/13.
 */
public class Chance {
    private final Random random;

    public Chance(Random random) {
        this.random = random;
    }

    private static final int factor = (int) Math.pow(10,9);
    public boolean happens(double chance) {
        if (chance > 1) return true;
        if (chance <= 0) return false;
        return random.nextInt(factor) < (int) (factor * chance);
    }
}
