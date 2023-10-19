package com.pluralsight;
import java.util.HashMap;
import java.util.Scanner;
import java.io.*;
public class SearchInventoryMap {
    // the key is the product id, the value is a product object
    public static HashMap<Integer, Product> inventory = new HashMap<Integer, Product>();

    public static void main(String[] args) {
        String choice;
        do {
// this method loads product objects into inventory
            loadInventory();
            Scanner scanner = new Scanner(System.in);
            System.out.print("What item # are you interested in? ");
            int id = scanner.nextInt();
            Product matchedProduct = inventory.get(id);
            if (matchedProduct == null) {
                System.out.println("We don't carry that product");
                return;
            }
            System.out.printf("We carry %s and the price is $%.2f",
                    matchedProduct.getName(), matchedProduct.getPrice());
            System.out.println();
            scanner.nextLine();
            System.out.println("Do you want to search again?(Y/N): ");
            choice = scanner.nextLine();
        } while (choice.equalsIgnoreCase("y"));
    }

    public static void loadInventory() {

        try {
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            //read text file
            int counter = 0;
            String input;
            while ((input = bufferedReader.readLine()) != null){
                String[] line = input.split("\\|");
               int id = Integer.parseInt(line[0]);
                String name = line[1];
                float price = Float.parseFloat(line[2]);
                //add each thing into hashmap of inventory
                counter++;
                inventory.put(id,new Product(id,name,price));
            }

        } catch(IOException e) {
            e.getStackTrace();
        }//end catch

    }//end loadInventory()
}