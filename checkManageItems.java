package pricewatcher;

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

import json.JSONArray;
import json.JSONObject;

public class checkManageItems {
	private int size;

	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	
	

	
	 
	
	/**
	 * @throws IOException ***********************************
	 * 
	 */
	public static void adItem(String fileName, Item item) throws IOException {
		try
		{
		    String filename= "MyFile.txt";
		    FileWriter fw = new FileWriter(filename,true); //the true will append the new data
		    fw.write(System.lineSeparator());
		    fw.write(item.name+","+item.url+","+item.initialPrice+","+item.currentPrice+","+item.change+","+item.date);
		    fw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
		Scanner file;
        PrintWriter writer;
        
        try {

            file = new Scanner(new File("myFile.txt"));
            writer = new PrintWriter("myFile2.txt");

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
	
	public static void seeItems(String fileName) throws IOException {
		File file = new File(fileName); 
		  
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		  
		String st; 
		while ((st = br.readLine()) != null) 
			System.out.println(st);
	}
	public static void changeItemName(String fileName, String name, String newName) throws IOException {
		File inputFile = new File (fileName);
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
	    fwFinal = new FileWriter(new File("myFile.txt"));
        for(int i = 0; i < counter; i++) {
        	fwFinal.write(String.format(info[i]));
        	if (i != counter - 1) {
        		fwFinal.write(System.lineSeparator());
        	}
        }
        fwFinal.close();	
	}
	
	public static void changeItemURL(String fileName, String url, String newUrl) throws IOException {
		File inputFile = new File (fileName);
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
	    fwFinal = new FileWriter(new File("myFile.txt"));
        for(int i = 0; i < counter; i++) {
        	fwFinal.write(String.format(info[i]));
        	if (i != counter - 1) {
        		fwFinal.write(System.lineSeparator());
        	}
        }
        fwFinal.close();	
	}
	public static void deleteItem(String fileName, String name, String url) throws IOException {
		File inputFile = new File (fileName);
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
	    fwFinal = new FileWriter(new File("myFile.txt"));
        for(int i = 0; i < counter; i++) {
        	fwFinal.write(String.format(info[i]));
        	if (i != counter - 1) {
        		fwFinal.write(System.lineSeparator());
        	}
        }
        fwFinal.close();	
	}
	public static void updateAllItems(String fileName) throws IOException {
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
        	items[i].setCurrPrice(needed);
        }
        
        FileWriter fwFinal;
	    fwFinal = new FileWriter(new File("myFile.txt"));
        for(int i = 0; i < counter; i++) {
        	fwFinal.write(items[i].name+","+items[i].url+","+items[i].initialPrice+","+items[i].currentPrice+","+items[i].change+","+items[i].date+",");
        	if (i != counter - 1) {
        		fwFinal.write(System.lineSeparator());
        	}
        }
        fwFinal.close();	
	}

	public static void updateItem(String fileName, int itemNumber) throws IOException {
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
        
        PriceFinder needed = new PriceFinder();
        items[itemNumber].setCurrPrice(needed);
        
        
        FileWriter fwFinal;
	    fwFinal = new FileWriter(new File("myFile.txt"));
        for(int i = 0; i < counter; i++) {
        	fwFinal.write(items[i].name+","+items[i].url+","+items[i].initialPrice+","+items[i].currentPrice+","+items[i].change+","+items[i].date);
        	if (i != counter - 1) {
        		fwFinal.write(System.lineSeparator());
        	}
        }
        fwFinal.close();	
	}
	public static Item[] arrayOfItems(String fileName) throws FileNotFoundException {
		Item[] items = new Item[1000];
		File inputFile = new File (fileName);
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
        	System.out.println(items[i].name+","+items[i].url+","+items[i].initialPrice+","+items[i].currentPrice+","+items[i].change+","+items[i].date);
        }
        
		return items;
	}
	public static void creatingJ(String fileName) throws IOException{
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

	    try(FileWriter fileJ = new FileWriter("jsonFile.json"))
	    {
	    	fileJ.write(obj.toString());
	    	fileJ.flush();
	    }
	    catch(IOException e) 
	    {
	    	e.printStackTrace();
	    }
	    System.out.println(obj);
    }
	
	public static void main(String[] args) throws IOException {
		
		File inputFile = new File ("fileName.txt");
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
		    		seeItems("myFile.txt");
		    		
		    		System.out.println("Select an option:");
		    		option = scan.next();
		    		break;
		    	case "2": option = "2";
		    		System.out.println("Select the name of the item you want to add");
		    		newName = scan.next();
		    		System.out.println("Select the url of the item you want to add");
		    		newUrl = scan.next();
		    		Item newItem = new Item(newName, newUrl);
		    		adItem("myFile.txt", newItem);
		    		System.out.println("Select an option:");
		    		option = scan.next();
		    		break;
		    	case "3": option = "3";
		    		System.out.println("Select the name of the file to be erased");
		    		String name = scan.next();
		    		System.out.println("Select the url of the file to be erased");
		    		String url = scan.next();
	    		
		    		deleteItem("myFile.txt", name, url);
		    		System.out.println("Select an option:");
		    		option = scan.next();
		    		break;
		    		
		    	case "4": option = "4";
		    		System.out.println("Give the name of the item you want to change");
		    		String nameOption4 = scan.next();
		    		System.out.println("Give the name you want to change it to");
		    		String newNameOption4 = scan.next();
		    		
		    		changeItemName("myFile.txt", nameOption4, newNameOption4);
		    		System.out.println("Select an option:");
		    		option = scan.next();
		    		break;
		    	case "5": option = "5";
		    		System.out.println("Give the url of the item you want to change");
		    		String urlOption4 = scan.next();
		    		System.out.println("Give the name you want to change it to");
		    		String newUrlOption4 = scan.next();
		    	
	    			changeItemURL("myFile.txt", urlOption4, newUrlOption4);
	    			System.out.println("Select an option:");
	    			option = scan.next();
	    			break;
		    	case "6": option = "6";
	    			updateAllItems("myFile.txt");
	    			System.out.println("Select an option:");
	    			option = scan.next();
    				break;
		    	case "7": option = "7";
		    		System.out.println("Give the index of the item to update");
		    		int numItem = scan.nextInt();
    				updateItem("myFile.txt", numItem);
    				System.out.println("Select an option:");
    				option = scan.next();
    				break;
		    	case "8": option = "8";
		    		arrayOfItems("myFile.txt");
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
	    
	    creatingJ("myFile.txt");
	    
	}
}

