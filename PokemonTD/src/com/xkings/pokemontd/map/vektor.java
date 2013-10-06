package com.xkings.pokemontd.map;

import java.util.Random;

/**
 * User: Seda
 * Date: 6.10.13
 * Time: 17:21
 */
/* vektor pro pohyb nepřátel*/

public class Vector {

    private int x;
    private int y;

    public static final int X_DIRECTION 1;
    public static final int Y_DIRECTION 2;

    public Vector(int x, int y){
        this.x = ( x==0 ? ++x : x);
        this.y = ( y==0 ? ++y : y);
    }
    public void changeDirection(int direction){
       if ( direction == Vector.X_DIRECTION){
           this.x = -this.x;
       } else if (direction == Vector.Y_DIRECTION){
           this.y = -this.y;
       }
    }
    public void newDirection(int direction){
        Random rnd = new Random();
        if (direction == Vector.X_DIRECTION){
            int newX = rnd.nextInt( 5 );
            this.x = this.x < 0 ? -newX: newX;
        } else if (direction == Vector.Y_DIRECTION){
            int newY = rnd.nextInt( 5 );
            this.y = this.y < 0 ? -newY: newY;
        }

    }
}
