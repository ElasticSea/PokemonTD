package com.xkings.core.utils;

import com.badlogic.gdx.math.Vector3;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * Created by Tomas on 10/8/13.
 */
public class CollisionTest {
    private Rect rectA;
    private Rect rectB;
    private Rect rectC;
    private Rect rectD;

    @BeforeTest
    public void setupRects() {
        rectA = new Rect(new Vector3(3, 2, 0), new Vector3(4, 2, 0));
        rectB = new Rect(new Vector3(4, 4, 0), new Vector3(2, 3, 0));
        rectC = new Rect(new Vector3(7, 4.5f, 0), new Vector3(4, 1, 0));
        rectD = new Rect(new Vector3(9, 3, 0), new Vector3(4, 4, 0));
    }

    @Test
    void testIntersectRects() {
        Assert.assertTrue(testRect(rectA, rectB));
        Assert.assertFalse(testRect(rectA, rectC));
        Assert.assertFalse(testRect(rectA, rectD));
        Assert.assertTrue(testRect(rectB, rectC));
        Assert.assertFalse(testRect(rectB, rectD));
        Assert.assertTrue(testRect(rectC, rectD));
    }

    boolean testRect(Rect a, Rect b) {
        return Collision.intersectRects(a.getPosition(), b.getPosition(), a.getSize(), b.getSize());
    }

    private static class Rect {
        private final Vector3 position;
        private final Vector3 size;

        public Rect(Vector3 position, Vector3 size) {
            this.position = position;
            this.size = size;
        }

        Vector3 getPosition() {
            return position;
        }

        Vector3 getSize() {
            return size;
        }
    }
}
