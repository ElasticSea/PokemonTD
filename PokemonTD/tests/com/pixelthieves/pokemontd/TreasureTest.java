package com.pixelthieves.pokemontd;

import org.junit.Assert;
import org.junit.Test;
import org.testng.annotations.BeforeTest;

/**
 * Created by Tomas on 10/8/13.
 */
public class TreasureTest {

    private Treasure TreasureA;
    private Treasure TreasureB;
    private Treasure TreasureC;

    @BeforeTest
    public void setUp() {
        TreasureA = new Treasure(50, 1, 2, 4, 2, 5, 0);
        TreasureB = new Treasure(40, 1, 2, 4, 2, 5, 0);
        TreasureC = new Treasure(50, 1, 1, 6, 5, 4, 1);
        System.out.println(TreasureA);
        System.out.println(TreasureB);
        System.out.println(TreasureC);
    }

    @Test
    public void testIncludes() throws Exception {
        Assert.assertTrue(TreasureA.includes(TreasureA));
        Assert.assertFalse(TreasureB.includes(TreasureA));
        Assert.assertFalse(TreasureC.includes(TreasureA));
        Assert.assertTrue(TreasureA.includes(TreasureB));
        Assert.assertFalse(TreasureA.includes(TreasureC));
    }
}
