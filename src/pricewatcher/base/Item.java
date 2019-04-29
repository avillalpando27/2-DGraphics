/**
 * CS 3331 -- Advanced Object Oriented Programming
 * HW 03
 * Main.java
 * By: Angel Villalpando / Edgar Escobedo / Jorge Quinonez
 * Instructor: Yoonsik Cheon
 * Last Modified: April 22, 2019
 */

package pricewatcher.base;

import java.text.SimpleDateFormat;
import java.util.Date;


public class Item {

    protected  String itemName;
    protected  String itemURL;
    protected  float itemInitialPrice;
    protected  float itemCurrentPrice;
    //private static URL testURL = new URL("https://amzn.to/2HlSGMH");


    /**
     * Default constructor for the Item class
     */
    public Item() {
    }

    /**
     * Detail setting constructor. Sets the item details manually.
     *
     * @param name  Name of the item being added to Price Watcher
     * @param uRL   URL of the item being added to Price Watcher
     * @param price Price of the item being added to Price Watcher
     */
    public Item(String name, String uRL, float price) {
        itemName = name;
        itemURL = uRL;
        itemInitialPrice = price;
    }

    /**
     * Returns the date the item was added to the Price Watcher app.
     *
     * @return Date The item's date.
     */
    public String returnDate() {

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        return formatter.format(date);
    }

    /**
     * Updates the item price manually.
     *
     * @param price The price being set for the item.
     */
    public void setPrice(float price) {
        itemCurrentPrice = price;
    }

    /**
     * Returns the items name
     *
     * @return String The item's name.
     */
    public String getName() {
        return itemName;
    }

    /**
     * Returns the item's URL
     *
     * @return String The item's URL.
     */
    public String getURL() {
        return itemURL;
    }

    /**
     * Returns the item's initial price.
     *
     * @return float The item's initial price.
     */
    public float getInitialPrice() {
        return itemInitialPrice;
    }

    /**
     * Returns the item's current price
     *
     * @return float The item's new price
     */
    public float getCurrentPrice() {
        PriceFinder randomPrice = new PriceFinder();
        itemCurrentPrice = randomPrice.returnNewPrice();
        return itemCurrentPrice;
    }

    /**
     * Calculates the change percentage
     *
     * @return float The calculated percentage change of item price
     */
    public float getChange() {

        float priceChange;

        priceChange = ((itemInitialPrice - itemCurrentPrice) / itemInitialPrice) * 100;

        return priceChange * -1;

    }

    /**
     * To string builder for item details
     *
     * @return String The item's detail string/block.
     */
    public String itemToString() {
        String details = "Name:\t" + getName() + "\n" + "URL:\t" + getURL() + "\n" + "Initial Price:\t" + getInitialPrice() + "\n" + "Current Price:\t" + getCurrentPrice() + "\n" + "Date Added:\t" + "04/20/2019";
        return details;
    }
}
