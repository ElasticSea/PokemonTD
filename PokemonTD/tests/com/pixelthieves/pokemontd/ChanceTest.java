package com.pixelthieves.pokemontd;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

/**
 * Created by Seda on 10/22/13.
 */
public class ChanceTest {
    private Chance chanceMachine;

    @BeforeMethod
    public void setUp() throws Exception {
        chanceMachine = new Chance(new Random());
    }

    @Test
    public void testHappens() throws Exception {
        /*int numberOfTries = 1000000000;
        int numberOfSuccessfulTries = 0;
        double chance = 0.2;
        for (int i = 0; i < numberOfTries; i++) {
            if (chanceMachine.happens(chance)) numberOfSuccessfulTries++;
        }
        Assert.assertEquals(chance , (float) numberOfSuccessfulTries / numberOfTries);  */
    }
}
