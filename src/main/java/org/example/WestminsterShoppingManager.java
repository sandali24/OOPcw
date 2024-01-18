package org.example;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.JTable;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class WestminsterShoppingManager implements ShoppingManager {
    public static WestminsterShoppingManager shoppingManager = new WestminsterShoppingManager();
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Product> listOfProductsUser = new ArrayList<>();



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
                    openGUI();
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
        int warrantyPeriod = scanner.nextInt();
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
        }
        else {
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

    public static void printListOfProducts(){
        if (listOfProductsUser.isEmpty()){
            System.out.println("array is empty");
        } else {
            Collections.sort(listOfProductsUser, Comparator.comparing(Product::getProductId));
            for (Product product:listOfProductsUser){
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
            } System.out.println("Products written to " + fileName);
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

    public static void openGUI (){
        System.out.println(listOfProductsUser);
        // Create a JFrame (window)
      JFrame frame = new JFrame("Westminster Shopping Center");
      frame.setSize(600,750); // Set the size of the frame
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close the application when the frame is closed
        frame.setLayout(null);

        // Create a JPanel (panel)
        JPanel toppanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        toppanel1.setBackground(Color.white);
        toppanel1.setBounds(0,0,400,75);

        // Add components (label, combo box, and button) to the panel
        JLabel label = new JLabel("Select Product Category");
        // Add an empty border to the label to create space around it
        label.setBorder(BorderFactory.createEmptyBorder(0, 80, 0, 0));
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});

        toppanel1.add(label);
        toppanel1.add(Box.createRigidArea(new Dimension(10, 0))); // Add space between JLabel and JComboBox
        toppanel1.add(comboBox);
        toppanel1.add(Box.createHorizontalGlue());

        // Add space between the combo box and the top border
        toppanel1.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));

        // Add the panel to the frame
        frame.add(toppanel1);

        // Create a panel with FlowLayout
        JPanel toppanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        // Add a button to the panel
        JButton button = new JButton("Shopping Cart");
        toppanel2.add(button);
        toppanel2.setBackground(Color.white);
        toppanel2.setBounds(400,0,187,75);

        // Add the panel to the frame
        frame.add(toppanel2);

//        JPanel toppanel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel toppanel3 = new JPanel(new BorderLayout());
        toppanel3.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
        toppanel3.setBackground(Color.white);
        toppanel3.setBounds(0,75,587,250);
        toppanel3.setBackground(Color.PINK);
        frame.add(toppanel3);
        // Table for product listing
        Object[][] data = new Object[0][5]; // Change the size if needed
        String[] defaultcolumnNames = {"Product ID", "Name", "Category", "Price(€)", "Info"};

        // Create a table model and set the column names
//        DefaultTableModel model = new DefaultTableModel(defaultcolumnNames, 0);
        DefaultTableModel model = new DefaultTableModel(data, defaultcolumnNames);

        JTable table = new JTable(model);
        JTextArea textArea1 = new JTextArea(); // Initialize textArea1

//        for (String columnName : defaultcolumnNames) {
//            model.addColumn(columnName);
//        }


        // Populate the table model with product data
        for (Product product : listOfProductsUser) {
            String category = "";
            String details = "";
            if (product instanceof Electronics) {
                category = "Electronics";
                details = ((Electronics) product).getBrand() + " ," + " " + ((Electronics) product).getWarrantyPeriod();
            } else if (product instanceof Clothing) {
                category = "Clothing";
                details = ((Clothing) product).getSize() + " ," + " " + ((Clothing) product).getColour();
            }

            // Adding a row to the model
            model.addRow(new Object[]{
                    product.getProductId(),
                    product.getProductName(),
                    category,
                    String.format("%.2f €", product.getPrice()),
                    details
            });
        }

        // Initialize the class-level table field
//        JTable table = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(table);
        toppanel3.add(table);
        table.add(tableScrollPane);

        // Set the frame visibility to true
        frame.setVisible(true);











































//        // Center panel for JTable
//        JPanel centerPanel = new JPanel(new BorderLayout());
////        centerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 20, 10));
//        centerPanel.setBounds(0,75,600,250);
//
//        // Initialize the model with an empty data array and column names
//        Object[][] data = new Object[0][5]; // Change the size if needed
//        String[] columnNames = {"Product ID", "Name", "Category", "Price($)", "Info"};
//        DefaultTableModel model = new DefaultTableModel(data, columnNames);
//
//        JTable jTable1 = new JTable(model);
//        JTextArea textArea1 = new JTextArea(); // Initialize textArea1
//
////        // Populate jTable1 with initial data from productList
////        Product[] productList = products.getProductlist();
//
//
//
//        frame.add(centerPanel);
//        centerPanel.add(jTable1);
//        centerPanel.add(textArea1);
//
//        // Set the frame visibility to true
//        frame.setVisible(true);

    }

}

