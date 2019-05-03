package pricewatcher;

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

import pricewatcher.PriceFinder;

public class Item {
	public String name;
	public String url;
	public String initialPrice;
	public float initPrice;
	public String currentPrice;
	public float currPrice;
	public String change;
	public String date;
	public Item() {
		this.name = "Toshiba 43LF621U19 43-inch 4K Ultra HD Smart LED TV HDR - Fire TV Edition";
		this.url = "https://www.amazon.com/Toshiba-43LF621U19-43-inch-Ultra-Smart/dp/B07D4F2P26/ref=sr_1_2_sspa?s=tv&ie=UTF8&qid=1549072299&sr=1-2-spons&keywords=television&psc=1";
		this.initialPrice = "200";
		this.initPrice = 200;
		this.currentPrice = "200";
		this.currPrice = 200;
		this.change = "0";
		Date date = new Date();
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	 
	    this.date = dateFormat.format(date);
	}
	public Item(String name, String url) {
		this.name =  name;
		this.url = url;
		this.initialPrice = "200";
		this.initPrice = 200;
		this.currentPrice = "200";
		this.currPrice = 200;
		this.change = "0";
		Date date = new Date();
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	 
	    this.date = dateFormat.format(date);
	}
	public Item(String name, String url, String initialPrice, float initPrice, String currentPrice, float currPrice, String change) {
		this.name = name;
		this.url = url;
		this.initialPrice = initialPrice;
		this.initPrice = initPrice;
		this.currentPrice = currentPrice;
		this.currPrice = currPrice;
		this.change = change;
		Date date = new Date();
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");	 
	    this.date = dateFormat.format(date);
	}
	public Item(String name, String url, String initialPrice, float initPrice, String currentPrice, float currPrice, String change, String date) {
		this.name = name;
		this.url = url;
		this.initialPrice = initialPrice;
		this.initPrice = initPrice;
		this.currentPrice = currentPrice;
		this.currPrice = currPrice;
		this.change = change;
	    this.date = date;
	}
	
	
	public void setCurrPrice(pricewatcher.PriceFinder find) {
		float initialValue = 200;
		float currentValue = (float) find.PriceFinder("simulated");
		float difference = ((currentValue/initialValue) * 100)-100;
		this.initialPrice = "200";
		this.currPrice = currentValue;
		String currPrice = Float.toString(currentValue);
		this.currentPrice = currPrice;
		String diff = Float.toString(difference);
		this.change = diff;
	}
	public void setName(String name) {
		
	}
}