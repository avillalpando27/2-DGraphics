/**
 * CS 3331 -- Advanced Object Oriented Programming
 * HW 03
 * Main.java
 * By: Angel Villalpando / Edgar Escobedo / Jorge Quinonez
 * Instructor: Yoonsik Cheon
 * Last Modified: April 22, 2019
 */

package pricewatcher.base;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.*;
import java.awt.event.*;


/**
 *  @author VillalpandoEscobedoQuinonez
 *  @version 1.2
 *  @since 1.0
 * */

@SuppressWarnings("serial")
public class Main extends JFrame {

    private  Item[] userItems = new Item[100];


    private int currentCounter = 0;
    private JOptionPane userInput = new JOptionPane();
    private PriceFinder refreshedPrice = new PriceFinder();

    private checkManageItems  manager = new checkManageItems();


    private  DefaultListModel<Item> itemList = new DefaultListModel<>();
    private final  JList<Item> jItemList = new JList<>(itemList);
    private ItemViewRenderer itemRenderer = new ItemViewRenderer();

    /**final file path for the images. */
    final private static String FILE_PATH = "/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/image/";

    /** Default dimension of the dialog. */
    private final static Dimension DEFAULT_SIZE = new Dimension(600, 400);

    /** Special panel to display the watched item. */
    private ItemView itemView;


    /** Message bar to display various messages. */
    private JLabel msgBar = new JLabel(" ");

    /** Create a new dialog. */
    public Main() {
        this(DEFAULT_SIZE);
    }

    /** Create a new dialog of the given screen dimension. */
    public Main(Dimension dim) {

        super("Price Watcher");

        setSize(dim);

        configureUI();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setResizable(true);
        showMessage("Welcome to Price Watcher!");

    }

    /** Callback to be invoked when the refresh button is clicked.
     * Find the current price of the watched item and display it
     * along with a percentage price change. */
    private void refreshButtonClicked(ActionEvent event) {


        int index = jItemList.getSelectedIndex();
        if(index > -1){
            userItems[index].setPrice(refreshedPrice.returnNewPrice());

            jItemList.setCellRenderer(itemRenderer);
            try {
                manager.updateItem("myFile.txt", userItems[index].itemURL, userItems[index].itemCurrentPrice, userItems[index].getChange());
            }catch(Exception z){
                z.printStackTrace();
            }
            jItemList.clearSelection();

            showMessage("Item price updated! ");
        }

        jItemList.clearSelection();

        showMessage("Item price updated! ");
    }

    /** Callback to be invoked when the view-page icon is clicked.
     * Launch a (default) web browser by supplying the URL of
     * the item. */
    private void viewPageClicked(ActionEvent event) {

        int index = jItemList.getSelectedIndex();

        if(index > -1) {
            String itemURL = userItems[index].itemURL;
            Desktop dk = Desktop.getDesktop();
            try {
                dk.browse(new java.net.URI(itemURL));
            } catch (IOException | URISyntaxException e) {
                System.out.println("The URL on file is invalid.");
            }

            showMessage("Visiting Item Web Page");
            jItemList.clearSelection();
        }

    }

    private void deleteItemClicked(ActionEvent e){

        int dialogButton = userInput.YES_NO_OPTION;
        int selectedIndex = 0;

        int dialogResult = JOptionPane.showConfirmDialog (null, "Are you sure you would like to delete this item?","WARNING",dialogButton);
        if(dialogResult == JOptionPane.YES_OPTION){
            selectedIndex = jItemList.getSelectedIndex();
            if (selectedIndex != -1) {
                try{
                    manager.deleteItem("myFile.txt", userItems[selectedIndex].itemName, userItems[selectedIndex].itemURL);
                }catch(Exception a){
                    a.printStackTrace();
                }
                itemList.remove(selectedIndex);
            }
        }
        jItemList.clearSelection();
        currentCounter--;
    }

    private void addItemClicked(ActionEvent e){

        JTextField name = new JTextField();
        JTextField url = new JTextField();
        JTextField price = new JTextField();

        Object[] message = {

                "Name: ", name,
                "URL: ", url,
                "Price: ", price,
        };

        JOptionPane.showConfirmDialog(getParent(), message, "Add Item", JOptionPane.OK_CANCEL_OPTION);


       try{
           userItems[currentCounter] = new Item(name.getText(), url.getText(), Float.parseFloat(price.getText()));
           manager.adItem("myFile.txt", userItems[currentCounter]);
           itemList.addElement(userItems[currentCounter]);
           currentCounter++;

       }catch(Exception error){
           System.out.println("No item added. proceed.");
       }

        jItemList.setCellRenderer(itemRenderer);

    }

    private void editButtonClicked(ActionEvent event){

        int index = jItemList.getSelectedIndex();

        if(index > -1){
            JTextField nameEdit = new JTextField(userItems[index].itemName);
            JTextField urlEdit = new JTextField(userItems[index].itemURL);
            JTextField priceEdit = new JTextField(Float.toString(userItems[index].itemCurrentPrice));


            Object[] message = {

                    "Name: ", nameEdit,
                    "URL: ", urlEdit,
                    "Price: ", priceEdit,
            };


            JOptionPane.showConfirmDialog(getParent(), message, "Add Item", JOptionPane.OK_CANCEL_OPTION);

            try {
                manager.changeItemName("myFile.txt", userItems[index].itemName, nameEdit.getText());
                manager.changeItemURL("myFile.txt", userItems[index].itemURL, urlEdit.getText());
            }catch(Exception h){
                h.printStackTrace();
            }

            userItems[index].itemName = nameEdit.getText();
            userItems[index].itemURL = urlEdit.getText();
            userItems[index].itemCurrentPrice = Float.parseFloat(priceEdit.getText());
            jItemList.setCellRenderer(itemRenderer);
        }
    }

    private void updateAllClicked(ActionEvent event){

        try{
            for(int i = 0; i < userItems.length; i++){
                if(userItems[i] != null){
                    userItems[i].setPrice(refreshedPrice.returnNewPrice());
                    manager.updateItem("myFile.txt", userItems[i].itemURL, userItems[i].itemCurrentPrice, userItems[i].getChange());
                }
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        jItemList.setCellRenderer(itemRenderer);
        showMessage("All Item prices have been updated!");
    }

    /** Configure UI. */
    private void configureUI() {
        /*
        try{
            Item[] fileItems = manager.arrayOfItems("myFile.txt");
            System.out.println("size of tempitemps: " + fileItems.length);
            for(int i = 0; i < fileItems.length; i++){
                itemList.addElement(fileItems[i]);
            }
            jItemList.setCellRenderer(itemRenderer);
        }catch(Exception z){
            z.printStackTrace();
        }*/

        setLayout(new BorderLayout());
        final JPanel control = makeControlPanel();
        this.setJMenuBar(menuMaker());
        control.setBorder(BorderFactory.createEmptyBorder(10,16,0,16));
        add(control, BorderLayout.NORTH);

        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("App");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("The only menu in this program that has menu items");
        menuBar.add(menu);

        JPanel board = new JPanel();
        board.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10,16,0,16),
                BorderFactory.createLineBorder(Color.CYAN)));
        board.setLayout(new GridLayout(1,1));

        JScrollPane pane = new JScrollPane(jItemList);

        pane.setViewportView(jItemList);
        board.add(pane);


        add(board, BorderLayout.CENTER);
        msgBar.setBorder(BorderFactory.createEmptyBorder(10,16,10,0));
        add(msgBar, BorderLayout.SOUTH);
    }

    /** Create a control panel consisting of a refresh button. */
    private JPanel makeControlPanel() {

        final JPanel panel = new JPanel(new FlowLayout(3));
        final JButton refreshButton = new JButton(new ImageIcon(iconMaker("refreshREAL.png")));
        refreshButton.setFocusPainted(false);
        refreshButton.addActionListener(this::refreshButtonClicked);
        refreshButton.setToolTipText("Refresh Item Price(s)");

        final JButton viewLink = new JButton(new ImageIcon(iconMaker("visitSite.png")));
        viewLink.setToolTipText("Visit Item Website");
        final JButton deleteItem = new JButton(new ImageIcon(iconMaker("delete.png")));
        deleteItem.setToolTipText("Remove Item");
        final JButton addItem = buttonMaker("additem.png");
        addItem.setToolTipText("Add Item to Price Watcher");
        final JButton editItem = buttonMaker("edititem.png");
        editItem.setToolTipText("Edit Item Details");


        viewLink.setFocusPainted(false);
        deleteItem.setFocusPainted(false);
        addItem.setFocusPainted(false);
        editItem.setFocusPainted(false);

        viewLink.addActionListener(this::viewPageClicked);
        deleteItem.addActionListener(this::deleteItemClicked);
        addItem.addActionListener(this::addItemClicked);
        editItem.addActionListener(this::editButtonClicked);

        panel.add(refreshButton);
        panel.add(viewLink);
        panel.add(deleteItem);
        panel.add(addItem);
        panel.add(editItem);

        return panel;
    }

    /** Show briefly the given string in the message bar. */
    private void showMessage(String msg) {
        msgBar.setText(msg);
        new Thread(() -> {
            try {
                Thread.sleep(3 * 1000); // 3 seconds
            } catch (InterruptedException e) {
            }
            if (msg.equals(msgBar.getText())) {
                SwingUtilities.invokeLater(() -> msgBar.setText(" "));
            }
        }).start();
    }

    public static void main(String[] args) {
        new Main();
    }

    /**
     * Produces button icons for control panel
     * @param s Icon image file path
     * @return BufferedImage The new icon for the control panel button
     */
    public BufferedImage iconMaker(String s){
        BufferedImage buttonIcon;
        try{
            buttonIcon = ImageIO.read(new File(FILE_PATH + s));
        }catch(IOException e){
            buttonIcon = null;
            System.out.println("BooHOO");
        }
        return buttonIcon;
    }

    /**
     * Produces the buttons for the control panel
     * @param s The icon image name
     * @return JButton The new control panel button
     */
    private JButton buttonMaker(String s){
        final JButton button;
        button = new JButton(new ImageIcon(iconMaker(s)));
        button.setFocusPainted(false);
        return button;
    }

    /**
     * Produces a new menu
     * @return JMenuBar The newly produces menuBar
     */
    private JMenuBar menuMaker(){
        final JMenuBar menuBar = new JMenuBar();
        final JMenu menu = new JMenu("App");
        menuBar.add(menu);
        menu.addSeparator();

        final JMenuItem menuItem;
        (menuItem = new JMenuItem("Exit", 88)).addActionListener(p0 -> System.exit(0));
        menu.add(menuItem);

        final JMenu menu2;
        (menu2 = new JMenu("Item")).setMnemonic(73);
        menuBar.add(menu2);
        menu2.addSeparator();

        final JMenuItem menuDelete = itemMaker("delete item");
        menu2.add(menuDelete);
        menuDelete.addActionListener(this::deleteItemClicked);

        final JMenuItem menuAdd = itemMaker("add item");
        menu2.add(menuAdd);
        menuAdd.addActionListener(this::addItemClicked);

        final JMenuItem menuEdit = itemMaker("edit item");
        menu2.add(menuEdit);
        menuEdit.addActionListener(this::editButtonClicked);

        final JMenuItem menuVisit = itemMaker("visit item page");
        menu2.add(menuVisit);
        menuVisit.addActionListener(this::viewPageClicked);

        final JMenuItem updateALL = itemMaker("update all");
        menu2.add(updateALL);
        updateALL.addActionListener(this::updateAllClicked);

        return menuBar;


    }

    private JMenuItem itemMaker(String s){
        return new JMenuItem(s);
    }
}
