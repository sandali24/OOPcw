package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static org.example.WestminsterShoppingManager.listOfProductsUser;


public class GUI1 {
    public static List<Product> productsInCart = new ArrayList<>();
    public static void opengui(){
        JFrame frame = new JFrame("Westminster Shopping Center");// Create a frame call Westminster Shopping Center
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JPanel panel1 = new JPanel();// Create four panels to hold the components
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel panel4 = new JPanel();

        panel1.setLayout(new FlowLayout(FlowLayout.CENTER));

        // Set the preferred size and border of panel3
        panel3.setPreferredSize(new Dimension(10, 10));
        panel3.setBorder(BorderFactory.createEmptyBorder(0, -5, 0, 0));

        // Create three sub-panels to hold the components
        JPanel subPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel subPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel subPanel3 = new JPanel();

        // Add a label and set the border to subPanel3
        subPanel3.add(new JLabel("Selected Product - Details"));
        subPanel3.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 50));

        // Create a label for the product category and set the border
        JLabel label = new JLabel("Select Product Category");
        label.setBorder(BorderFactory.createEmptyBorder(0, 185, 0, 0));

        // Create a combo box with three options
        JComboBox<String> comboBox = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});

        // Add the label, a rigid area, and the combo box to subPanel1
        subPanel1.add(label);
        subPanel1.add(Box.createRigidArea(new Dimension(10, 0)));
        subPanel1.add(comboBox);
        subPanel1.add(Box.createHorizontalGlue());
        subPanel1.setBorder(BorderFactory.createEmptyBorder(40, 0, 0, 0));
        subPanel1.setPreferredSize(new Dimension(500, 100));

        subPanel2.setBounds(0, 0, 187, 75);
        subPanel2.setPreferredSize(new Dimension(200, 100));
        panel1.add(subPanel1);
        panel1.add(subPanel2);

        // Create a button for the shopping cart
        JButton button = new JButton("Shopping Cart");
        subPanel2.add(button);

        // Add an action listener to the button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUI2(productsInCart);
            }
        });

        // Create a table model to store the product data
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Product ID");// Add five columns to the table model
        tableModel.addColumn("Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Price");
        tableModel.addColumn("Info");

        for (Product product : listOfProductsUser) {
            String category = "";// Declare variables to store the category and details of each product
            String details = "";
            if (product instanceof Electronics) {// Check if the product is an instance of Electronics and set the category to Electronics
                category = "Electronics";
                // Set the details to the brand and warranty period of the product
                details = ((Electronics) product).getBrand() + " ," + " " + ((Electronics) product).getWarrantyPeriod();
            } else if (product instanceof Clothing) {// Check if the product is an instance of Clothing and set the category to clothing
                category = "Clothing";
                // Set the details to the size and colour of the product
                details = ((Clothing) product).getSize() + " ," + " " + ((Clothing) product).getColour();
            }
            tableModel.addRow(new Object[]{// Add a row to the table model with the product information
                    product.getProductId(),
                    product.getProductName(),
                    category,
                    String.format("%.2f €", product.getPrice()),
                    details
            });
        }
        JTable table = new JTable(tableModel);// Create a table with the table model
        table.setRowHeight(40);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); // Create a cell renderer for center alignment
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Set the horizontal alignment to center
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        JScrollPane scrollPane = new JScrollPane(table);// Create a scroll pane to hold the table
        panel2.add(scrollPane);// Add the scroll pane to panel2

        // Create a button for adding products to the shopping cart
        JButton button2 = new JButton("Add to Shopping Cart");

        // Add an action listener to the button
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();// Get the index of the selected row in the table
                // Check if a row is selected and get the product object from the list of products
                if (selectedRow != -1) {
                    Product selectedProduct = listOfProductsUser.get(selectedRow);
                    int setItem = 1;// Declare a variable to store the number of items to add
                    if (productsInCart.size() > 0) {// Check if the products in cart list is not empty
                        for (int i = 0; i < productsInCart.size(); i++) {
                            // Check if the selected product ID matches the product ID in the list
                            if (selectedProduct.getProductId() == productsInCart.get(i).getProductId()) {
                                setItem = productsInCart.get(i).getNumOfAvailableItems() + 1;// Increment the number of items by one
                                productsInCart.get(i).setNumOfAvailableItems(setItem);// Set the number of items for the product in the list
                                productsInCart.remove(i);// Remove the product from the list
                                break;
                            }
                        }
                    }

                    selectedProduct.setNumOfAvailableItems(setItem);
                    productsInCart.add(selectedProduct);
                    setItem = 1;

                    System.out.println();
                    System.out.println("------------------------------------");
                    for (int i = 0; i < productsInCart.size(); i++) {
                        System.out.println("sas " + productsInCart.get(i).getProductId());
                        System.out.println(productsInCart.get(i).getNumOfAvailableItems());
                    }
                    System.out.println("------------------------------------");
                }
            }
        });

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


        table.addMouseListener(new MouseAdapter() {// Add a mouse listener to the table
            @Override
            public void mouseClicked(MouseEvent e) {
                // Get the index of the selected row and column in the table
                int selectedRow = table.getSelectedRow();
                int selectedColumn = table.getSelectedColumn();
                // Check if a row is selected and the column is the first one
                if (selectedRow != -1 && selectedColumn == 0) {
                    // Get the values of the product ID, name, category, price, and details from the table
                    Object productID = table.getValueAt(selectedRow, 0);
                    Object productName = table.getValueAt(selectedRow, 1);
                    Object category = table.getValueAt(selectedRow, 2);
                    Object price = table.getValueAt(selectedRow, 3);
                    Object details = table.getValueAt(selectedRow, 4);

                    // Declare variables to store the specific details of each product
                    String specificDetail1 = "";
                    String specificDetail2 = "";

                    // Check if the category is Electronics
                    if ("Electronics".equals(category)) {
                        String[] detailsArray = ((String) details).split(",");
                        // Set the specific details to the brand and warranty period of the product
                        specificDetail1 = "Brand: " + detailsArray[0].trim();
                        specificDetail2 = "Warranty Period: " + detailsArray[1].trim();
                    } else if ("Clothing".equals(category)) {// Check if the category is Clothing
                        String[] detailsArray = ((String) details).split(",");
                        // Set the specific details to the size and color of the product
                        specificDetail1 = "Size: " + detailsArray[0].trim();
                        specificDetail2 = "Color: " + detailsArray[1].trim();
                    }

                    // Create a string builder to append the HTML tags and product information
                    StringBuilder sb = new StringBuilder();
                    sb.append("<html><body>");
                    sb.append("<b>Selected Product - Details</b><br>");
                    sb.append("Product ID: ").append(productID).append("<br>");
                    sb.append("Product Name: ").append(productName).append("<br>");
                    sb.append("Category: ").append(category).append("<br>");
                    sb.append("Price: ").append(price).append("<br>");
                    sb.append(specificDetail1).append("<br>");
                    sb.append(specificDetail2).append("<br>");
                    sb.append("</body></html>");

                    JLabel rowLabel = new JLabel(sb.toString());// Create a label with the string builder as the text

                    subPanel3.removeAll(); // Clear the subPanel3 before adding the new label
                    subPanel3.add(rowLabel);// Add the label to subPanel3
                    panel3.add(subPanel3);
                    // Revalidate and repaint panel3 and subPanel3
                    panel3.revalidate();
                    panel3.repaint();
                    subPanel3.revalidate();
                    subPanel3.repaint();
                }
            }
        });

        // Add an action listener to the combo box
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the source of the event as a combo box
                JComboBox cb = (JComboBox) e.getSource();
                String selectedCategory = (String) cb.getSelectedItem(); // Get the selected item as a string
                // Create a table row sorter with the table model
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
                table.setRowSorter(sorter);
                // Check if the selected item is All
                if ("All".equals(selectedCategory)) {
                    sorter.setRowFilter(null);
                } else {
                    // Set the row filter to match the selected category in the third column
                    sorter.setRowFilter(RowFilter.regexFilter(selectedCategory, 2));
                }
            }
        });
    }
}
