package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    public static WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Product> listOfProductsUser = new ArrayList<>();

    public static void main(String[] args) {

        displayMenu();
        System.out.print("Enter your choice: ");
        int menuChoice = scanner.nextInt();

        switch (menuChoice) {
            case 1:
                addProduct();
                break;
            case 2:
                deleteProduct();
                break;
            default:
                System.out.println("Invalid choice, Please enter a valid option.");
        }
    }

    public static void displayMenu() {
        System.out.println("Westminster Shopping Menu:");
        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
    }

    public static void addProduct() {
        int maximumNumOfProducts = 50;
        if (listOfProductsUser.size() <= maximumNumOfProducts) {
            System.out.println("Select product type:");
            System.out.println("1. Electronics");
            System.out.println("2. Clothing");
            System.out.print("Enter your choice: ");
            int productType = scanner.nextInt();
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
        int warrantyPeriod = scanner.nextInt();
        Product electronics = new Electronics(productId, productName, numOfAvailableItems, price, brand, warrantyPeriod);
        listOfProductsUser.add(electronics);
        deleteProduct();
//        for (int i = 0; i < listOfProductsUser.size(); i++) {
//            System.out.println(electronics.toString());
//            }
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
        // Get user input
        System.out.print("Insert the Product ID: ");
        String productId = scanner.next();

        // Going through the ArrayList to find and remove the element
        for (int i = 0; i < listOfProductsUser.size(); i++) {
            if ((listOfProductsUser.get(i).getProductId() != null) && (productId.equals(listOfProductsUser.get(i).getProductId()))) {
                System.out.println("Deleted Product ID: " + listOfProductsUser.get(i).getProductId());
                listOfProductsUser.remove(i);
                // Adjust the index after remove an element
                i--;
            }

            // Check if the ArrayList is empty
            if (listOfProductsUser.isEmpty()){
                System.out.println("ArrayList is empty now.");
            } else {
                // Print the remaining elements
                System.out.println("Remaining elements in the ArrayList:");
                for (Product element : listOfProductsUser){
                    System.out.println(element);
                }
            }
        }
    }
}

