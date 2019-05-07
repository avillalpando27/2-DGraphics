package pricewatcher.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.text.Document;

//import json.JSONArray;
//import json.JSONObject;

public class checkManageItems {
    private int size;
    private String fileName = "myFile.txt";

    public int size() {
        // TODO Auto-generated method stub
        return 0;
    }







    /**
     * @throws IOException ***********************************
     *
     */
    public void adItem(String fileName, Item item) throws IOException {
        try
        {
            String filename= "/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/" + fileName;
            FileWriter fw = new FileWriter(filename,true); //the true will append the new data
            fw.write(System.lineSeparator());
            fw.write(item.itemName+","+item.itemURL+","+item.itemInitialPrice+","+item.itemCurrentPrice+","+item.getChange()+","+item.returnDate());
            fw.close();
        }
        catch(IOException ioe)
        {
            System.err.println("IOException: " + ioe.getMessage());
        }
        Scanner file;
        PrintWriter writer;

        try {

            file = new Scanner(new File("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/myFile.txt"));
            writer = new PrintWriter("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/myFile1.txt");

            while (file.hasNext()) {
                String line = file.nextLine();
                if (!line.isEmpty()) {
                    writer.write(line);
                    writer.write(System.lineSeparator());
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("File not found");
        }
    }

    public void seeItems(String fileName) throws IOException {
        File file = new File(fileName);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String st;
        while ((st = br.readLine()) != null)
            System.out.println(st);
    }
    public void changeItemName(String fileName, String name, String newName) throws IOException {
        File inputFile = new File ("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/" + fileName);
        Scanner sc = new Scanner (inputFile);

        String[] info = new String[1000];
        int counter = 0;
        while(sc.hasNextLine()){
            String line	= sc.nextLine();
            String cut = "[,]";
            String[] cell = line.split(cut);

            if ( cell[0].equals(name)){
                info[counter] = newName+","+cell[1]+","+cell[2]+","+cell[3]+","+cell[4]+","+cell[5];
            }else {
                info[counter] = cell[0]+","+cell[1]+","+cell[2]+","+cell[3]+","+cell[4]+","+cell[5];

            }

            counter++;
        }
        FileWriter fwFinal;
        fwFinal = new FileWriter(new File("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/" + fileName));
        for(int i = 0; i < counter; i++) {
            fwFinal.write(String.format(info[i]));
            if (i != counter - 1) {
                fwFinal.write(System.lineSeparator());
            }
        }
        fwFinal.close();
    }

    public void changeItemURL(String fileName, String url, String newUrl) throws IOException {
        File inputFile = new File ("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/" + fileName);
        Scanner sc = new Scanner (inputFile);

        String[] info = new String[1000];
        int counter = 0;
        while(sc.hasNextLine()){
            String line	= sc.nextLine();
            String cut = "[,]";
            String[] cell = line.split(cut);

            if ( cell[1].equals(url)){
                info[counter] = cell[0]+","+newUrl+","+cell[2]+","+cell[3]+","+cell[4]+","+cell[5];
            }else {
                info[counter] = cell[0]+","+cell[1]+","+cell[2]+","+cell[3]+","+cell[4]+","+cell[5];
            }

            counter++;
        }
        FileWriter fwFinal;
        fwFinal = new FileWriter(new File("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/" + fileName));
        for(int i = 0; i < counter; i++) {
            fwFinal.write(String.format(info[i]));
            if (i != counter - 1) {
                fwFinal.write(System.lineSeparator());
            }
        }
        fwFinal.close();
    }
    public void deleteItem(String fileName, String name, String url) throws IOException {
        File inputFile = new File ("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/" + fileName);
        Scanner sc = new Scanner (inputFile);

        String[] info = new String[1000];
        int counter = 0;
        while(sc.hasNextLine()){
            String line	= sc.nextLine();
            String cut = "[,]";
            String[] cell = line.split(cut);
            if ( cell[0].equals(name) && cell[1].equals(url)){
            }else {
                info[counter] = cell[0]+","+cell[1]+","+cell[2]+","+cell[3]+","+cell[4]+","+cell[5];
                counter++;
            }
        }
        FileWriter fwFinal;
        fwFinal = new FileWriter(new File("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/" + fileName));
        for(int i = 0; i < counter; i++) {
            fwFinal.write(String.format(info[i]));
            if (i != counter - 1) {
                fwFinal.write(System.lineSeparator());
            }
        }
        fwFinal.close();
    }
    public void updateAllItems(String fileName) throws IOException {
        File inputFile = new File (fileName);
        Scanner sc = new Scanner (inputFile);

        String[] info = new String[1000];
        Item[] items = new Item[1000];
        int counter = 0;
        while(sc.hasNextLine()){
            String line	= sc.nextLine();
            String cut = "[,]";
            String[] cell = line.split(cut);
            float initPrice = Float.parseFloat(cell[2]);
            float currPrice = Float.parseFloat(cell[3]);
            items[counter] = new Item(cell[0], cell[1], cell[2], initPrice,cell[3], currPrice, cell[4], cell[5]);
            counter++;
        }
        for(int i = 0; i < counter; i++) {
            String empty = "";
            PriceFinder needed = new PriceFinder();
            items[i].setCurrPrice();
        }

        FileWriter fwFinal;
        fwFinal = new FileWriter(new File("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/" + fileName));
        for(int i = 0; i < counter; i++) {
            fwFinal.write(items[i].nameManage+","+items[i].urlManage+","+items[i].initialPriceManage+","+items[i].currentPriceManage+","+items[i].changeManage+","+items[i].dateManage+",");
            if (i != counter - 1) {
                fwFinal.write(System.lineSeparator());
            }
        }
        fwFinal.close();
    }


    public void updateItem(String fileName, String url, float currentPrice, float change) throws IOException {
        File inputFile = new File ("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/" + fileName);
        Scanner sc = new Scanner (inputFile);

        String[] info = new String[1000];
        int counter = 0;
        while(sc.hasNextLine()){
            String line	= sc.nextLine();
            String cut = "[,]";
            String[] cell = line.split(cut);

            if ( cell[1].equals(url)){
                info[counter] = cell[0]+","+cell[1]+","+cell[2]+","+currentPrice+","+ change +","+cell[5];
            }else {
                info[counter] = cell[0]+","+cell[1]+","+cell[2]+","+cell[3]+","+cell[4]+","+cell[5];
            }

            counter++;
        }
        FileWriter fwFinal;
        fwFinal = new FileWriter(new File("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/" + fileName));
        for(int i = 0; i < counter; i++) {
            fwFinal.write(String.format(info[i]));
            if (i != counter - 1) {
                fwFinal.write(System.lineSeparator());
            }
        }
        fwFinal.close();
    }

    public Item[] arrayOfItems(String fileName) throws FileNotFoundException {
        Item[] items = new Item[1000];
        File inputFile = new File ("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/" + fileName);
        Scanner sc = new Scanner (inputFile);


        int counter = 0;
        while(sc.hasNextLine()){

            String line	= sc.nextLine();
            String cut = "[,]";
            String[] cell = line.split(cut);
            float initPrice = Float.parseFloat(cell[2]);
            float currPrice = Float.parseFloat(cell[3]);
            items[counter] = new Item(cell[0], cell[1], cell[2], initPrice,cell[3], currPrice, cell[4], cell[5]);
            counter++;
        }
        for(int i = 0; i < counter ; i++) {
            System.out.println(items[i].itemName+","+items[i].itemURL+","+items[i].itemInitialPrice+","+items[i].itemCurrentPrice+","+items[i].getChange()+","+items[i].returnDate());
        }

        return items;
    }
    /*
    public void creatingJ(String fileName) throws IOException{
        File file = new File(fileName);
        Scanner sc = new Scanner (file);
        JSONObject obj = new JSONObject();
        while(sc.hasNextLine()){
            String line	= sc.nextLine();
            String cut = "[,]";
            String[] cell = line.split(cut);
            JSONArray list = new JSONArray();
            for(int i = 0; i < cell.length; i++) {
                list.put(cell[i]);
            }

            obj.append("item", list);
        }
        String house = "house";
        obj.append("house",house);

        try(FileWriter fileJ = new FileWriter("jsonFile.json"))
        {
            fileJ.write(obj.toString());
            fileJ.flush();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }
    }
    */

    public void main(String[] args) throws IOException {

        File inputFile = new File ("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/myFile.txt");
        Scanner sc = new Scanner (inputFile);
        Scanner scan = new Scanner(System.in);
        int counter = 1000;
        System.out.println("Please select an option");
        System.out.println("1: see items");
        System.out.println("2: add item");
        System.out.println("3: delete item");
        System.out.println("4: change existing item name");
        System.out.println("5: change existing item url");
        System.out.println("6: update all items");
        System.out.println("7: update one item");
        System.out.println("8: return array of current items ");
        System.out.println("9: exit");

        String option = scan.next();
        String newName = "";
        String newUrl = "";
        while(!option.equals("9")) {
            switch(option) {
                case "1": option = "1";
                    seeItems("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/myFile.txt");

                    System.out.println("Select an option:");
                    option = scan.next();
                    break;
                case "2": option = "2";
                    System.out.println("Select the name of the item you want to add");
                    newName = scan.next();
                    System.out.println("Select the url of the item you want to add");
                    newUrl = scan.next();
                    Item newItem = new Item(newName, newUrl);
                    adItem("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/myFile.txt", newItem);
                    System.out.println("Select an option:");
                    option = scan.next();
                    break;
                case "3": option = "3";
                    System.out.println("Select the name of the file to be erased");
                    String name = scan.next();
                    System.out.println("Select the url of the file to be erased");
                    String url = scan.next();

                    deleteItem("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/myFile.txt", name, url);
                    System.out.println("Select an option:");
                    option = scan.next();
                    break;

                case "4": option = "4";
                    System.out.println("Give the name of the item you want to change");
                    String nameOption4 = scan.next();
                    System.out.println("Give the name you want to change it to");
                    String newNameOption4 = scan.next();

                    changeItemName("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/myFile.txt", nameOption4, newNameOption4);
                    System.out.println("Select an option:");
                    option = scan.next();
                    break;
                case "5": option = "5";
                    System.out.println("Give the url of the item you want to change");
                    String urlOption4 = scan.next();
                    System.out.println("Give the name you want to change it to");
                    String newUrlOption4 = scan.next();

                    changeItemURL("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/myFile.txt", urlOption4, newUrlOption4);
                    System.out.println("Select an option:");
                    option = scan.next();
                    break;
                case "6": option = "6";
                    updateAllItems("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/myFile.txt");
                    System.out.println("Select an option:");
                    option = scan.next();
                    break;
                case "7": option = "7";
                    System.out.println("Give the index of the item to update");
                    int numItem = scan.nextInt();
                    //updateItem("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/myFile.txt", numItem);
                    System.out.println("Select an option:");
                    option = scan.next();
                    break;
                case "8": option = "8";
                    arrayOfItems("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/myFile.txt");
                    System.out.println("Select an option:");
                    option = scan.next();
                    break;

                default:
                    System.out.println("Wrong input, select an option:");
                    System.out.println("Please select an option");
                    System.out.println("1: see items");
                    System.out.println("2: add item");
                    System.out.println("3: delete item");
                    System.out.println("4: change existing item name");
                    System.out.println("5: change existing item url");
                    System.out.println("6: update all items");
                    System.out.println("7: update one item");
                    System.out.println("8: return array of current items ");
                    System.out.println("9: exit");
                    break;

            }
        }

        //creatingJ("/Users/angelvillalpando/Desktop/2-DGraphics/src/pricewatcher/base/myFile.txt");
	    /*
	    String url = "http://www.bestbuy.ca/en-CA/product/samsung-samsung-galaxy-tab-3-8-0-16gb-android-4-2-tablet-with-exynos-4212-processor-white-sm-t310/10254746.aspx?path=8654a6d491c7d5a9465456671fa126e4en02";
	    Document document = Jsoup.connect(url).get();
	    String amount = document.select(".amount").first().text();
	    System.out.println("Price: " + amount);
	    String name = document.select(".product-title").first().text();
	    System.out.println("Item Name: " + name);
	    */
    }
}
