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

                JFrame frame = new JFrame("My GUI");
                frame.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                JPanel panel1 = new JPanel();
                JPanel panel2 = new JPanel();
                JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Changed to FlowLayout for vertical display
                panel3.setBackground(Color.PINK);
                JPanel panel4 = new JPanel();
                JPanel subPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
                JPanel subPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                panel1.setLayout(new FlowLayout(FlowLayout.CENTER));
                JLabel label = new JLabel("Select Product Category");
                label.setBorder(BorderFactory.createEmptyBorder(0, 185, 0, 0));
                JComboBox<String> comboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
                subPanel1.add(label);
                subPanel1.add(Box.createRigidArea(new Dimension(10, 0)));
                subPanel1.add(comboBox);
                subPanel1.add(Box.createHorizontalGlue());
                subPanel1.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
                JButton button = new JButton("Shopping Cart");
                subPanel2.add(button);
                subPanel2.setBounds(0, 0, 187, 75);
                subPanel1.setPreferredSize(new Dimension(500, 100));
                subPanel2.setPreferredSize(new Dimension(200, 100));
                panel1.add(subPanel1);
                panel1.add(subPanel2);
                DefaultTableModel tableModel = new DefaultTableModel();
                tableModel.addColumn("id");
                tableModel.addColumn("name");
                tableModel.addColumn("category");
                tableModel.addColumn("price");
                tableModel.addColumn("details");

                // Assuming Product, Electronics, and Clothing classes are defined

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
                    tableModel.addRow(new Object[]{
                            product.getProductId(),
                            product.getProductName(),
                            category,
                            String.format("%.2f €", product.getPrice()),
                            details
                    });
                }
                JTable table = new JTable(tableModel);
                table.setRowHeight(40);
                table.setPreferredScrollableViewportSize(new Dimension(500, 200));
                JScrollPane scrollPane = new JScrollPane(table);
                panel2.add(scrollPane);
                JButton button2 = new JButton("This is a button");
                panel4.add(button2);
                c.gridx = 0;
                c.gridy = 0;
                c.gridwidth = 1;
                c.fill = GridBagConstraints.BOTH;
                frame.add(panel1, c);
                c.gridx = 0;
                c.gridy = 1;
                frame.add(panel2, c);
                c.gridx = 0;
                c.gridy = 2;
                c.weighty = 1.0; // Vertical fill
                c.anchor = GridBagConstraints.NORTH;
                frame.add(panel3, c);
                c.gridx = 0;
                c.gridy = 3;
                frame.add(panel4, c);
                frame.setSize(1000, 1000);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);

                table.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int selectedRow = table.getSelectedRow();
                        int selectedColumn = table.getSelectedColumn();
                        if (selectedRow != -1 && selectedColumn == 0) {
                            Object productID = table.getValueAt(selectedRow, 0);
                            Object productName = table.getValueAt(selectedRow, 1);
                            Object category = table.getValueAt(selectedRow, 2);
                            Object price = table.getValueAt(selectedRow, 3);
                            Object details = table.getValueAt(selectedRow, 4);

                            StringBuilder sb = new StringBuilder();
                            sb.append("<html><body>");
                            sb.append("<b>Selected Product - Details</b><br>");
                            sb.append("Product ID: ").append(productID).append("<br>");
                            sb.append("Product Name: ").append(productName).append("<br>");
                            sb.append("Category: ").append(category).append("<br>");
                            sb.append("Price: ").append(price).append("<br>");
                            sb.append("Details: ").append(details);
                            sb.append("</body></html>");

                            JLabel rowLabel = new JLabel(sb.toString());
                            panel3.removeAll(); // Clear the panel before adding the new label
                            panel3.add(rowLabel);
                            panel3.revalidate();
                            panel3.repaint();
                        }
                    }
                });
            }

        }






















