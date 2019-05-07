/**
 * CS 3331 -- Advanced Object Oriented Programming
 * HW 03
 * Main.java
 * By: Angel Villalpando / Edgar Escobedo / Jorge Quinonez
 * Instructor: Yoonsik Cheon
 * Last Modified: April 22, 2019
 */

package pricewatcher.base;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.text.DecimalFormat;



/** A special panel to display the detail of an item. */

@SuppressWarnings("serial")
public class ItemView extends JPanel {
    
	/** Interface to notify a click on the view page icon. */
	protected Item testItem;

	public interface ClickListener {
		
		/** Callback to be invoked when the view page icon is clicked. */
		void clicked();
	}
	
	/** Directory for image files: src/image in Eclipse. */
	private final static String IMAGE_DIR = "/image/";
        
	/** View-page clicking listener. */
    private ClickListener listener;


    public void setItem(Item item){
        this.testItem = item;
    }
    /** Create a new instance. */
    public ItemView() {
    	setPreferredSize(new Dimension(100, 160));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
            	if (isViewPageClicked(e.getX(), e.getY()) && listener != null) {
            		listener.clicked();
            	}
            }
        });
    }
        
    /** Set the view-page click listener. */
    public void setClickListener(ClickListener listener) {
    	this.listener = listener;
    }
    
    /** Overridden here to display the details of the item. */
   // @Override
	public void paintComponent(final Graphics g) {

        super.paintComponent(g);
        DecimalFormat df = new DecimalFormat("###.##");
        Dimension dim = this.getSize();

        g.setColor(Color.BLACK);
        g.drawString("Name: ", 20, 40);
        g.drawString(testItem.itemName, 80, 40);
        g.drawString("URL: ", 20, 60);
        g.drawString(testItem.itemURL, 80, 60);

        g.drawString("Price:", 20, 80);
        g.drawString("$" + df.format(testItem.itemCurrentPrice), 80, 80);

        /* the following code segment is where the text color takes place,as well as where the sound is played
        * when the price of the item drops. Simple if/else statements do the trick.*/
        final float tempChange = testItem.getChange();
        if(tempChange < 0){
            g.setColor(Color.RED.brighter().brighter().brighter());
            g.drawString("Change:  % " + df.format(testItem.getChange()), 20, 100);
            g.setColor(Color.BLACK);
        }else{
            g.setColor(Color.GREEN);
            g.drawString("Change:  % " + df.format(testItem.getChange()), 20, 100);
            g.setColor(Color.BLACK);
        }
        g.drawString("Date Added: " + testItem.returnDate() + " [ $" + df.format(testItem.itemInitialPrice) + " ]",  20, 120);
    }

    
    /** Return true if the given screen coordinate is inside the viewPage icon. */
    private boolean isViewPageClicked(int x, int y) {
    	//--
    	//-- WRITE YOUR CODE HERE
    	//--
    	return new Rectangle(20, 20, 30, 20).contains(x,  y);
    }
        
    /** Return the image stored in the given file. */
    public Image getImage(String file) {
        try {
        	URL url = new URL(getClass().getResource(IMAGE_DIR), file);
            return ImageIO.read(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}