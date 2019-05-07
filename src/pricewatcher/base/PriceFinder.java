/**
 * CS 3331 -- Advanced Object Oriented Programming
 * HW 04
 * Main.java
 * By: Angel Villalpando / Edgar Escobedo / Jorge Quinonez
 * Instructor: Yoonsik Cheon
 * Last Modified: May 6, 2019
 */

package pricewatcher.base;

import java.util.Random;

public class PriceFinder{

    /**
     * Returns the new, randomly generated price for item.
     * @return float Item's random new price
     */
    public float returnNewPrice(){

        Random rando = new Random();
        return 100 + rando.nextFloat() * (1000 - 100);
    }
}
//
