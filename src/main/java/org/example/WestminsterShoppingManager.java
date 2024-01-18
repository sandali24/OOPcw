package org.example;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

    public static void openGUI() {
        DefaultTableModel tableModel = new DefaultTableModel ();
        tableModel.addColumn ("name");
        tableModel.addColumn ("id");
        tableModel.addColumn ("price");
        tableModel.addColumn ("details");

//Loop through the array list and add each product as a row to the table model
        for (Product product : listOfProductsUser) {
            Object [] row = new Object [] {product.getProductName(), product.getProductId(), product.getPrice (), product.getNumOfAvailableItems()};
            tableModel.addRow (row);
        }

//Create a JTable with the table model
        JTable table = new JTable (tableModel);

        //Set the row height and the preferred scrollable viewport size of the table
        table.setRowHeight (20);
        table.setPreferredScrollableViewportSize (new Dimension (400, 200));

//Create a JScrollPane with the table
        JScrollPane scrollPane = new JScrollPane (table);

//Create a JPanel and add the scroll pane to it
        JPanel tablePanel = new JPanel ();
        tablePanel.add (scrollPane);
        tablePanel.setBackground(Color.white);

//Create a JFrame and set the layout manager to BorderLayout
        JFrame frame = new JFrame ("Products Table");
        frame.setLayout (new BorderLayout ());

//Add the table panel to the center region of the frame
        frame.add (tablePanel, BorderLayout.CENTER);

        //Create two JPanels for the top region of the frame
        JPanel topPanel1 = new JPanel (new FlowLayout (FlowLayout.LEFT));
//        topPanel1.setBounds(0, 0, 400, 75);
        JPanel topPanel2 = new JPanel (new FlowLayout(FlowLayout.RIGHT));





         //Add components (label, combo box, and button) to the panel
        JLabel label = new JLabel("Select Product Category");
        // Add an empty border to the label to create space around it
        label.setBorder(BorderFactory.createEmptyBorder(0, 150, 0, 0));
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});

        topPanel1.add(label);
        topPanel1.add(Box.createRigidArea(new Dimension(10, 0))); // Add space between JLabel and JComboBox
        topPanel1.add(comboBox);
        topPanel1.add(Box.createHorizontalGlue());
//
//        // Add space between the combo box and the top border
        topPanel1.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));



        // Add a button to the panel
        JButton button = new JButton("Shopping Cart");
        topPanel2.add(button);
        topPanel2.setBackground(Color.white);
        topPanel2.setBounds(400, 0, 187, 75);





        //Set the background color and preferred size of the panels
        topPanel1.setBackground (Color.white);
        topPanel2.setBackground (Color.white);
        topPanel1.setPreferredSize (new Dimension (500, 100));
        topPanel2.setPreferredSize (new Dimension (200, 100));


        //Create another JPanel to hold the two panels horizontally
        JPanel topPanel = new JPanel ();
        topPanel.setLayout (new FlowLayout (FlowLayout.CENTER));
        topPanel.setBounds(0, 0, 600, 750);
//        topPanel.setBounds(0,0,600,750);

//Add the two panels to the top panel
        topPanel.add (topPanel1);
        topPanel.add (topPanel2);

//Add the top panel to the north region of the frame
        frame.add (topPanel, BorderLayout.NORTH);


//Set the size, location, and default close operation of the frame
        frame.setSize(800,1000);
        frame.setLocationRelativeTo (null);
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);

//Make the frame visible and pack it
        frame.pack ();
        frame.setVisible (true);































































        }
    }


