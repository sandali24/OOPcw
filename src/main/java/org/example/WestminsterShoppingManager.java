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
    public static WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Product> listOfProductsUser = new ArrayList<>();
//    public ArrayList<Product> usercart = ShoppingCart.listOfProducts;
//    ShoppingCart shoppingCartObj = new ShoppingCart();
    static GUI1 gui1 = new GUI1(GUI1.getProductList());






    public static void main(String[] args) throws IOException {
        while (true) {

            displayMenu();
            System.out.print("    Enter your choice: ");
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
                    gui1.openGUI();
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, Please enter a valid option.");
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
            System.out.println("Select product type:");
            System.out.println("    1. Electronics");
            System.out.println("    2. Clothing \n");
            System.out.print("    Enter your choice: ");
            int productType = scanner.nextInt();
            System.out.println();
            switch (productType) {
                case 1:
                    addElectronics();
                    break;
                case 2:
                    addClothing();
                    break;
                default:
                    System.out.println("Invalid product type. Please enter a valid option.");
            }
        } else {
            System.out.println("You can add only 50 items, Cannot add more!");
        }
    }

    public static void addElectronics() {
        System.out.print("Please enter the product id: ");
        String productId = scanner.next();
        System.out.print("Please enter the product name: ");
        String productName = scanner.next();
        System.out.print("Please enter the available items: ");
        int numOfAvailableItems = scanner.nextInt();
        System.out.print("Please enter the price: ");
        double price = scanner.nextDouble();
        System.out.print("Please enter the brand: ");
        String brand = scanner.next();
        System.out.print("Please enter the warranty period: ");
        String warrantyPeriod = scanner.next();
        Product electronics = new Electronics(productId, productName, numOfAvailableItems, price, brand, warrantyPeriod);
        listOfProductsUser.add(electronics);

    }


    public static void addClothing() {
        System.out.print("Please enter the product id: ");
        String productId = scanner.next();
        System.out.print("Please enter the product name: ");
        String productName = scanner.next();
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
            }
































