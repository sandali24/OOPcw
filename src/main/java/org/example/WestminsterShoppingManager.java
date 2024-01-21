package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;


public class WestminsterShoppingManager implements ShoppingManager {
    private String productId;
    private String productName;
    private int numOfAvailableItems;
    private double price;
    private String brand;
    private String warrantyPeriod;
    public static WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Product> listOfProductsUser = new ArrayList<>();



    //    public ArrayList<Product> usercart = ShoppingCart.listOfProducts;
//    ShoppingCart shoppingCartObj = new ShoppingCart();
    static GUI1 gui1 = new GUI1();






    public static void main(String[] args) throws IOException {
        while (true) {

            displayMenu();
            System.out.print("    Enter your choice: ");
            // Check if the input is an integer using hasNextInt() method
            if (scanner.hasNextInt()) {
                // If the input is an integer, read it and store it in menuChoice variable
                int menuChoice = scanner.nextInt();
                System.out.println();

                switch (menuChoice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        deleteProduct();
                        break;
                    case 3:
                        printListOfProducts();
                        break;
                    case 4:
                        saveIntoFile();
                        break;
                    case 5:
                        fileReader();
                        break;
                    case 6:
                        gui1.opengui();
                        break;
                    case 0:
                        System.out.println("Exiting the program...");
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice, Please enter a valid option.");
                }
            } else {
                // If the input is not an integer, print a statement as "unvalid" and consume the input
                System.out.println("Invalid input, Please try again...");
                scanner.next();
            }
        }
    }

    public static void displayMenu() {
        System.out.println();
        System.out.println("---Westminster Shopping Menu---");
        System.out.println("    1. Add a new product");
        System.out.println("    2. Delete a product");
        System.out.println("    3. Print list of product");
        System.out.println("    4. Save Product");
        System.out.println("    5. Read file");
        System.out.println("    6. Open GUI");
        System.out.println("    0. Exit the Program \n");

    }

    public static void addProduct() {
        int maximumNumOfProducts = 50;
        if (listOfProductsUser.size() <= maximumNumOfProducts) {
            int productType;
            do {
                System.out.println("Select product type:");
                System.out.println("    1. Electronics");
                System.out.println("    2. Clothing \n");
                System.out.print("    Enter your choice: ");
                // check if the input is a valid integer
                if (scanner.hasNextInt()) {
                    productType = scanner.nextInt();
                    // validate that the product type is between 1 and 2
                    if (productType >= 1 && productType <= 2) {
                        System.out.println();
                        switch (productType) {
                            case 1:
                                addElectronics();
                                break;
                            case 2:
                                addClothing();
                                break;
                        }
                    } else {
                        System.out.println("Invalid product type. Please enter a valid option.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter an integer.");
                    scanner.next(); // discard the invalid input
                    productType = 0; // set a zero value to trigger the loop
                }
            } while (productType < 1 || productType > 2); // repeat until the product type is valid
        } else {
            System.out.println("You can add only 50 items, Cannot add more!");
        }
                        }
    public static void addElectronics() {
        String productId;
        do {
            System.out.print("Please enter the product id: ");
            productId = scanner.next();
            // validate that product id is not null or empty and matches the regex pattern
            // the pattern allows letters, symbols and numbers, but not spaces
            if (productId == null || productId.isEmpty() || !productId.matches("[\\w\\p{Punct}]+")) {
                System.out.println("Invalid product id...");
            }
        } while (productId == null || productId.isEmpty() || !productId.matches("[\\w\\p{Punct}]+")); // repeat until product id is valid
        String productName;
        do {
            System.out.print("Please enter the product name: ");
            productName = scanner.next();
            // validate that product name is not null or empty and matches the regex pattern
            // the pattern allows only letters, upper or lower case
            if (productName == null || productName.isEmpty() || !productName.matches("[a-zA-Z]+")) {
                System.out.println("Invalid product name...");
            }
        } while (productName == null || productName.isEmpty() || !productName.matches("[a-zA-Z]+")); // repeat until product name is valid
        int numOfAvailableItems;
        do {
            System.out.print("Please enter the number of available items: ");
            // check if the input is a valid integer
            if (scanner.hasNextInt()) {
                numOfAvailableItems = scanner.nextInt();
                // validate that the number of available items is not negative
                if (numOfAvailableItems >= 0) {
//                    System.out.println("Valid number of available items");
                } else {
                    System.out.println("Invalid number of available items...");
                }
            } else {
                System.out.println("Invalid number of available items...");
                scanner.next(); // discard the invalid input
                numOfAvailableItems = -1; // set a negative value to trigger the loop
            }
        } while (numOfAvailableItems < 0); // repeat until the number of available items is not negative
        double price;
        do {
            System.out.print("Please enter the price: ");
            // check if the input is a valid double
            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                // validate that the price is positive
                if (price > 0) {
//                    System.out.println("Valid price");
                } else {
                    System.out.println("Invalid price...");
                }
            } else {
                System.out.println("Invalid price...");
                scanner.next(); // discard the invalid input
                price = 0; // set a zero value to trigger the loop
            }
        } while (price <= 0);
        String brand;
        do {
            System.out.print("Please enter the brand: ");
            brand = scanner.next();
            // validate that brand is not null or empty and matches the regex pattern
            // the pattern allows any letter, upper or lower case, from any alphabet
            if (brand == null || brand.isEmpty() || !brand.matches("\\p{L}+")) {
                System.out.println("Invalid brand name...");
            }
        } while (brand == null || brand.isEmpty() || !brand.matches("\\p{L}+")); // repeat until brand is valid
        String warrantyPeriod;
        do {
            System.out.print("Please enter the warranty period: ");
            warrantyPeriod = scanner.next();
            // validate that product id is not null or empty and matches the regex pattern
            // the pattern allows letters, symbols and numbers, but not spaces
            if (warrantyPeriod == null || warrantyPeriod.isEmpty() || !warrantyPeriod.matches("[\\w\\p{Punct}]+")) {
                System.out.println("Invalid warranty period...");
            }
        } while (warrantyPeriod == null || warrantyPeriod.isEmpty() || !warrantyPeriod.matches("[\\w\\p{Punct}]+")); // repeat until product id is valid

        Product electronics = new Electronics(productId, productName, numOfAvailableItems, price, brand, warrantyPeriod);
        listOfProductsUser.add(electronics);

//
//        System.out.print("Please enter the product id: ");
//        String productId = scanner.next();
//        System.out.print("Please enter the product name: ");
//        String productName = scanner.next();
//        System.out.print("Please enter the available items: ");
//        int numOfAvailableItems = scanner.nextInt();
//        System.out.print("Please enter the price: ");
//        double price = scanner.nextDouble();
//        System.out.print("Please enter the brand: ");
//        String brand = scanner.next();
//        System.out.print("Please enter the warranty period: ");
//        String warrantyPeriod = scanner.next();
//        Product electronics = new Electronics(productId, productName, numOfAvailableItems, price, brand, warrantyPeriod);
//        listOfProductsUser.add(electronics);

    }


    public static void addClothing() {
        System.out.print("Please enter the product id: ");
        String productId = scanner.next();
        System.out.print("Please enter the product name: ");
        String productName = scanner.nextLine();
        System.out.print("Please enter the available items: ");
        int numOfAvailableItems = scanner.nextInt();
        System.out.print("Please enter the price: ");
        double price = scanner.nextDouble();
        System.out.print("Please enter the size: ");
        String size = scanner.next();
        System.out.print("Please enter the colour: ");
        String colour = scanner.next();
        Product clothing = new Clothing(productId, productName, numOfAvailableItems, price, size, colour);
        listOfProductsUser.add(clothing);
    }

    public static void deleteProduct() {

        if (listOfProductsUser.isEmpty()) {
            System.out.println("ArrayList is empty now.");
        } else {
            System.out.print("Insert the Product ID: ");
            String productId = scanner.next();
            for (int i = 0; i < listOfProductsUser.size(); i++) {
                if ((productId.equals(listOfProductsUser.get(i).getProductId()))) {
                    System.out.println("Deleted Product ID: " + listOfProductsUser.get(i).getProductId());
                    listOfProductsUser.remove(i);
                    System.out.println("Remaining elements in the ArrayList:");
                    for (Product element : listOfProductsUser) {
                        System.out.println(element);
                    }
                } else {
                    System.out.println("not in array");
                }
            }
        }
    }

    public static void printListOfProducts() {
        if (listOfProductsUser.isEmpty()) {
            System.out.println("array is empty");
        } else {
            Collections.sort(listOfProductsUser, Comparator.comparing(Product::getProductId));


            for (Product product : listOfProductsUser) {
                System.out.println(product.toString());
            }
        }
    }

    public static void saveIntoFile() {
        String fileName = "product.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : listOfProductsUser) {
                if (product != null) {
                    writer.write(product.toString());
                    writer.newLine();
                }
            }
            System.out.println("Products written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void fileReader() {
        try (BufferedReader reader = new BufferedReader(new FileReader("product.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                reader.toString();
            }
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
    public static ArrayList<Product> getListOfProductsUser() {
        return listOfProductsUser;
    }



}

































