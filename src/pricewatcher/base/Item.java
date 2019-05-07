package pricewatcher.base;

/*
Created by Edgar Escobedo and Jorge Quinonez
HW2 2-D Graphics
Advanced Objects 3331
Dr. Cheon
03/06/2019
 */

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class Item {
    public String nameManage;
    public String urlManage;
    public String initialPriceManage;
    public float initPriceManage;
    public String currentPriceManage;
    public float currPriceManage;
    public String changeManage;
    public String dateManage;

    protected  String itemName;
    protected  String itemURL;
    protected  float itemInitialPrice;
    protected  float itemCurrentPrice;
    //private static URL testURL = new URL("https://amzn.to/2HlSGMH");


    /**
     * Default constructor for the Item class
     */

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
        itemCurrentPrice = price;
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

    public Item() {
        this.nameManage = "Toshiba 43LF621U19 43-inch 4K Ultra HD Smart LED TV HDR - Fire TV Edition";
        this.urlManage = "https://www.amazon.com/Toshiba-43LF621U19-43-inch-Ultra-Smart/dp/B07D4F2P26/ref=sr_1_2_sspa?s=tv&ie=UTF8&qid=1549072299&sr=1-2-spons&keywords=television&psc=1";
        this.initialPriceManage = "200";
        this.initPriceManage = 200;
        this.currentPriceManage = "200";
        this.currPriceManage = 200;
        this.changeManage = "0";
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.dateManage = dateFormat.format(date);
    }
    public Item(String name, String url) {
        this.nameManage =  name;
        this.urlManage = url;
        this.initialPriceManage = "200";
        this.initPriceManage = 200;
        this.currentPriceManage = "200";
        this.currPriceManage = 200;
        this.changeManage = "0";
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.dateManage = dateFormat.format(date);
    }
    public Item(String name, String url, String initialPrice, float initPrice, String currentPrice, float currPrice, String change) {
        this.nameManage = name;
        this.urlManage = url;
        this.initialPriceManage = initialPrice;
        this.initPriceManage = initPrice;
        this.currentPriceManage = currentPrice;
        this.currPriceManage = currPrice;
        this.changeManage = change;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        this.dateManage = dateFormat.format(date);
    }
    public Item(String name, String url, String initialPrice, float initPrice, String currentPrice, float currPrice, String change, String date) {
        this.nameManage = name;
        this.urlManage = url;
        this.initialPriceManage = initialPrice;
        this.initPriceManage = initPrice;
        this.currentPriceManage = currentPrice;
        this.currPriceManage = currPrice;
        this.changeManage = change;
        this.dateManage = date;
    }


    public void setCurrPrice() {

        PriceFinder find = new PriceFinder();
        float initialValue = 200;
        float currentValue = find.returnNewPrice();
        float difference = ((currentValue/initialValue) * 100)-100;
        this.initialPriceManage = "200";
        this.currPriceManage = currentValue;
        String currPrice = Float.toString(currentValue);
        this.currentPriceManage = currPrice;
        String diff = Float.toString(difference);
        this.changeManage = diff;
    }
    public void setName(String name) {

    }
}