package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class WestminsterShoppingManager implements ShoppingManager {
    private String productId;
    private String productName;
    private int numOfAvailableItems;
    private double price;
    private String brand;
    private String warrantyPeriod;
    private double size;
    private String colour;
    public static WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Product> listOfProductsUser = new ArrayList<>();
    static GUI1 gui1 = new GUI1();

    public static void main(String[] args) throws IOException {
        String role = "";
        boolean validInput = false;
        // use a while loop to repeat until the input is valid
        while (!validInput) {
            System.out.print("Are you an admin or a user? Print 'admin' if you are an admin or print 'user' if you are a user: ");
            role = scanner.next();
            // convert the input to lowercase for comparison
            role = role.toLowerCase();
            if (role.equals("admin") || role.equals("user")) {
                validInput = true;
            } else {
                System.out.println("Invalid input.Print admin or user...");
            }
        }

        if (role.equals("admin")) {
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
                            saveProductsDetails("product.txt");
                            break;
                        case 5:
                            loadProductDetails("product.txt");
                            break;
                        case 6:
                            // check the role of the user
                            if (role.equals("user")) {
                                loadProductDetails("product.txt");
                                gui1.opengui();
                            } else if (role.equals("admin")) {
                                System.out.println("You are not authorized to access this option...");
                            }
                            break;
                        case 0:
                            System.out.println("Exiting the program...");
                            System.exit(0);
                        default:
                            System.out.println("Invalid choice, Please enter a valid option.");
                    }
                } else {
                    // If the input is not an integer, print a statement as "Invalid" and consume the input
                    System.out.println("Invalid input, Please try again...");
                    scanner.next();
                }
            }
        } else if (role.equals("user")) {
            String email;
            do {
                System.out.print("Please enter your email: ");
                email = scanner.next();
                if (email == null || email.isEmpty() || !email.matches("^[\\w]+(\\.[\\w]+)*@gmail\\.com$")) {
                    System.out.println("Invalid email...");
                }
            } while (email == null || email.isEmpty() || !email.matches("^[\\w]+(\\.[\\w]+)*@gmail\\.com$"));
            System.out.println("Valid Email...");
            String password;
            do {
                System.out.print("Please enter your password: ");
                password = scanner.next();
                if (password == null || password.isEmpty() || !password.matches("[\\w\\p{Punct}]+")) {
                    System.out.println("Invalid password...");
                }
            } while (password == null || password.isEmpty() || !password.matches("[\\w\\p{Punct}]+"));
            System.out.println("Login Success...");

            boolean validInput1 = false;
            while (!validInput1) {
                System.out.print("Do you want to open GUI? Print 'Y' to open GUI or 'N' to exit: ");
                char input = scanner.next().charAt(0);
                if (Character.toLowerCase(input) == 'y') {
                    // load the product details and open the GUI
                    loadProductDetails("product.txt");
                    gui1.opengui();
                    // set the flag to true and exit the loop
                    validInput1 = true;
                } else if (Character.toLowerCase(input) == 'n') {
                    // print a message and exit the program
                    System.out.println("OK,Good Bye...");
                    System.exit(0);
                    // set the flag to true and exit the loop
                    validInput1 = true;
                } else {
                    // print an error message and continue the loop
                    System.out.println("Invalid input, please enter 'Y' or 'N'...");
                }
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
        System.out.println("    5. Load product details");
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
                    System.out.println("Invalid input. Please enter number 1 or 2...");
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
            if (productId == null || productId.isEmpty() || !productId.matches("[\\w\\p{Punct}]+")) {
                System.out.println("Invalid product id...");
            }
        } while (productId == null || productId.isEmpty() || !productId.matches("[\\w\\p{Punct}]+")); // repeat until product id is valid
        String productName;
        do {
            System.out.print("Please enter the product name: ");
            productName = scanner.next();
            // validate that product name is not null or empty and matches the regex pattern
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
            if (brand == null || brand.isEmpty() || !brand.matches("\\p{L}+")) {
                System.out.println("Invalid brand name...");
            }
        } while (brand == null || brand.isEmpty() || !brand.matches("\\p{L}+")); // repeat until brand is valid
        String warrantyPeriod;
        do {
            System.out.print("Please enter the warranty period: ");
            warrantyPeriod = scanner.next();
            // validate that product id is not null or empty and matches the regex pattern
            if (warrantyPeriod == null || warrantyPeriod.isEmpty() || !warrantyPeriod.matches("[\\w\\p{Punct}]+")) {
                System.out.println("Invalid warranty period...");
            }
        } while (warrantyPeriod == null || warrantyPeriod.isEmpty() || !warrantyPeriod.matches("[\\w\\p{Punct}]+")); // repeat until product id is valid

        Product electronics = new Electronics(productId, productName, numOfAvailableItems, price, brand, warrantyPeriod);
        listOfProductsUser.add(electronics);
    }


    public static void addClothing() {
        String productId;
        do {
            System.out.print("Please enter the product id: ");
            productId = scanner.next();
            // validate that product id is not null or empty and matches the regex pattern
            if (productId == null || productId.isEmpty() || !productId.matches("[\\w\\p{Punct}]+")) {
                System.out.println("Invalid product id...");
            }
        } while (productId == null || productId.isEmpty() || !productId.matches("[\\w\\p{Punct}]+")); // repeat until product id is valid
        String productName;
        do {
            System.out.print("Please enter the product name: ");
            productName = scanner.next();
            // validate that product name is not null or empty and matches the regex pattern
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
                } else {
                    System.out.println("Invalid price...");
                }
            } else {
                System.out.println("Invalid price...");
                scanner.next(); // discard the invalid input
                price = 0; // set a zero value to trigger the loop
            }
        } while (price <= 0);
        String size;
        do {
            System.out.print("Please enter the size: ");
            size = scanner.next();
            // validate that product id is not null or empty and matches the regex pattern
            if (size == null || size.isEmpty() || !size.matches("[\\w\\p{Punct}]+")) {
                System.out.println("Invalid size...");
            }
        } while (size == null || size.isEmpty() || !size.matches("[\\w\\p{Punct}]+")); // repeat until product id is valid
        String colour;
        do {
            System.out.print("Please enter the colour: ");
            colour = scanner.next();
            // validate that product name is not null or empty and matches the regex pattern
            if (colour == null || colour.isEmpty() || !colour.matches("[a-zA-Z]+")) {
                System.out.println("Invalid colour...");
            }
        } while (colour == null || colour.isEmpty() || !colour.matches("[a-zA-Z]+")); // repeat until product name is valid
        Product clothing = new Clothing(productId, productName, numOfAvailableItems, price, size, colour);
        listOfProductsUser.add(clothing);
    }

    public static void deleteProduct() {
        if (listOfProductsUser.isEmpty()) {
            System.out.println("Product list is empty...");
        } else {
            System.out.print("Insert the Product ID: ");
            String productId = scanner.next();
            for (int i = 0; i < listOfProductsUser.size(); i++) {
                if ((productId.equals(listOfProductsUser.get(i).getProductId()))) {
                    System.out.println("Deleted Product ID: " + listOfProductsUser.get(i).getProductId());
                    listOfProductsUser.remove(i);
                    System.out.println("Remaining products: ");
                    for (Product element : listOfProductsUser) {
                        System.out.println(element);
                    }
                } else {
                    System.out.println("Not in the product list...");
                }
            }
        }
    }

    public static void printListOfProducts() {
        if (listOfProductsUser.isEmpty()) { // Check if listOfProductsUser is empty, if listOfProductsUser is empty print array is empty
            System.out.println("array is empty");
        } else {
            Collections.sort(listOfProductsUser, Comparator.comparing(Product::getProductId)); // Sort the list using a comparator
            for (Product product : listOfProductsUser) { // Loop through the list and print each product
                System.out.println(product.toString());
            }
        }
    }

    public static void saveIntoFile() {
        String fileName = "product.txt"; // Define the file name
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Product product : listOfProductsUser) { // Loop through the list of products
                if (product != null) { // Check if the product is not null, if product is not null write the product to the file
                    writer.write(product.toString());
                    writer.newLine();
                }
            }
            System.out.println("Products written to " + fileName);
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public static void saveProductsDetails(String fileName) {
        File file = new File(fileName);

        try (ObjectOutputStream obJOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            // Save products to text file
            obJOutputStream.writeObject(listOfProductsUser);

            System.out.println("Details saved to file successfully");

        } catch (IOException e) {
            System.out.println("Error saving products details to file: ");
        }
    }

    private static void loadProductDetails(String fileName) {
        File file = new File(fileName);

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            // Load products  from text file
            listOfProductsUser = (ArrayList<Product>) objectInputStream.readObject();

            System.out.println("Product details loaded from text file successfully");

        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading user details from text file: " + e.getMessage());
        }
    }
}


































