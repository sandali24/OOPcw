package org.example;

import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args){

        displayMenu();
        System.out.print("Enter your choice: ");
        int menuChoice = scanner.nextInt();

        switch (menuChoice){
            case 1:
                addProduct();
                break;
            default:
                System.out.println("Invalid choice, Please enter a valid option.");
        }
    }
    public static void displayMenu(){
        System.out.println("Westminster Shopping Menu:");
        System.out.println("1. Add a new product");
    }
    public static void addProduct(){
        ShoppingCart shoppingCart = new ShoppingCart();
        int maximumNumOfProducts=50;
        if (shoppingCart.getListOfProducts().size() <= maximumNumOfProducts){
            System.out.println("Select product type:");
            System.out.println("1. Eectronics");
            System.out.println("2. Clothing");
            System.out.print("Enter your choice: ");
            int productType = scanner.nextInt();
            switch (productType){
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

    public static void addElectronics(){

    }
    public static void addClothing(){

    }
}
