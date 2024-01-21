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
        JFrame frame = new JFrame("Westminster Shopping Center");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        // Panel for selected product details (assuming this will be dynamically updated)
//        JPanel detailsPanel = new JPanel();
//        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
//        detailsPanel.setBackground(Color.PINK);
//        detailsPanel.add(new JLabel("Selected Product - Details"));

        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Changed to FlowLayout for vertical display
        panel3.setPreferredSize(new Dimension(10, 10)); // Adjusted size for printed details
        panel3.setBorder(BorderFactory.createEmptyBorder(0, -5, 0, 0)); // Added padding
        JPanel panel4 = new JPanel();
        JPanel subPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel subPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JPanel subPanel3 = new JPanel(); // New subPanel3 for displaying saved data
        subPanel3.add(new JLabel("Selected Product - Details"));
        subPanel3.setBorder(BorderFactory.createEmptyBorder(50, 150, 50, 50)); // Adding border to subPanel3
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

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new GUI2(productsInCart);
            }
        });


//        button.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                gui2.GUI2method();
//            }
//        });

        subPanel2.setBounds(0, 0, 187, 75);
        subPanel1.setPreferredSize(new Dimension(500, 100));
        subPanel2.setPreferredSize(new Dimension(200, 100));
        panel1.add(subPanel1);
        panel1.add(subPanel2);
        DefaultTableModel tableModel = new DefaultTableModel();
        tableModel.addColumn("Product ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Price");
        tableModel.addColumn("Info");

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
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); // Create a cell renderer for center alignment
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Set the horizontal alignment to center
        for (int i = 0; i < table.getColumnCount(); i++) { // Loop through all columns
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer); // Set the cell renderer for each column
        }
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        JScrollPane scrollPane = new JScrollPane(table);
        panel2.add(scrollPane);


        JButton button2 = new JButton("Add to Shopping Cart");

        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();

                if (selectedRow != -1) {
                    Product selectedProduct = listOfProductsUser.get(selectedRow);

                    int setItem = 1;

                    if (productsInCart.size() > 0) {
                        for (int i = 0; i < productsInCart.size(); i++) {
                            if (selectedProduct.getProductId() == productsInCart.get(i).getProductId()) {
                                setItem = productsInCart.get(i).getNumOfAvailableItems() + 1;
                                productsInCart.get(i).setNumOfAvailableItems(setItem);
                                productsInCart.remove(i);
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

                    String specificDetail1 = "";
                    String specificDetail2 = "";

                    if ("Electronics".equals(category)) {
                        String[] detailsArray = ((String) details).split(",");
                        specificDetail1 = "Brand: " + detailsArray[0].trim();
                        specificDetail2 = "Warranty Period: " + detailsArray[1].trim();
                    } else if ("Clothing".equals(category)) {
                        String[] detailsArray = ((String) details).split(",");
                        specificDetail1 = "Size: " + detailsArray[0].trim();
                        specificDetail2 = "Color: " + detailsArray[1].trim();
                    }

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

                    JLabel rowLabel = new JLabel(sb.toString());


                    subPanel3.removeAll(); // Clear the subPanel3 before adding the new label
                    subPanel3.add(rowLabel);
                    panel3.add(subPanel3); // Adding subPanel3 to panel3
                    panel3.revalidate();
                    panel3.repaint();
                    subPanel3.revalidate();
                    subPanel3.repaint();
                }
            }
        });

        // Declare an ArrayList to store the details
//        ArrayList<String> detailsList = new ArrayList<String>();

//
//        // In the button2 action listener, clear the ArrayList before adding new data
//        button2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Clear the ArrayList before adding new data
//                detailsList.clear();
//                // Loop through the components of subPanel3
//                for (Component c : subPanel3.getComponents()) {
//                    // Check if the component is a JLabel
//                    if (c instanceof JLabel) {
//                        // Get the text of the JLabel and remove the HTML tags
//                        String text = ((JLabel) c).getText().replaceAll("<[^>]*>", "");
//                        // Split the text by line breaks and add each line to the ArrayList
//                        for (String line : text.split("\n")) {
//                            detailsList.add(line);
//                        }
//                    }
//                }
//                // Print the ArrayList for testing
//                System.out.println(detailsList);
//                // Create the GUI2 object only if it is null
//                if (gui2 == null) {
//                    gui2 = new GUI2(detailsList);
//                }
//                // Pass the ArrayList to the GUI2 object
//                gui2.setDetailsList(detailsList);
//            }
//        });


//        button2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Loop through the components of subPanel3
//                for (Component c : subPanel3.getComponents()) {
//                    // Check if the component is a JLabel
//                    if (c instanceof JLabel) {
//                        // Get the text of the JLabel and remove the HTML tags
//                        String text = ((JLabel) c).getText().replaceAll("<[^>]*>", "");
//                        // Split the text by line breaks and add each line to the ArrayList
//                        for (String line : text.split("\n")) {
//                            detailsList.add(line);
//                        }
//                    }
//                }
//                // Print the ArrayList for testing
//                System.out.println(detailsList);
//                // Create the GUI2 object only if it is null
//                if (gui2 == null) {
//                    gui2 = new GUI2(detailsList);
//                }
//                // Pass the ArrayList to the GUI2 object
//                gui2.setDetailsList(detailsList);
//                // Remove the line that calls the GUI2method
//                // gui2.GUI2method();
//            }
//        });


//        button2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Loop through the components of subPanel3
//                for (Component c : subPanel3.getComponents()) {
//                    // Check if the component is a JLabel
//                    if (c instanceof JLabel) {
//                        // Get the text of the JLabel and remove the HTML tags
//                        String text = ((JLabel) c).getText().replaceAll("<[^>]*>", "");
//                        // Split the text by line breaks and add each line to the ArrayList
//                        for (String line : text.split("\n")) {
//                            detailsList.add(line);
//                        }
//                    }
//                }
//                // Print the ArrayList for testing
//                System.out.println(detailsList);
//                // Create a GUI2 object and pass the ArrayList to it
//                GUI2 gui2 = new GUI2(detailsList);
//                // Call the printArrayList method to display the ArrayList in the frame
//                gui2.GUI2method();
//            }
//        });


//        button2.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                // Loop through the components of subPanel3
//                for (Component c : subPanel3.getComponents()) {
//                    // Check if the component is a JLabel
//                    if (c instanceof JLabel) {
//                        // Get the text of the JLabel and remove the HTML tags
//                        String text = ((JLabel) c).getText().replaceAll("<[^>]*>", "");
//                        // Split the text by line breaks and add each line to the ArrayList
//                        for (String line : text.split("\n")) {
//                            detailsList.add(line);
//                        }
//                    }
//                }
//                // Print the ArrayList for testing
//                System.out.println(detailsList);
//            }
//        });

//// Add the button to the panel
//        panel3.add(button);


//         Create another anonymous inner class for the combo box
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox cb = (JComboBox) e.getSource();
                String selectedCategory = (String) cb.getSelectedItem();
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(tableModel);
                table.setRowSorter(sorter);
                if ("All".equals(selectedCategory)) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(selectedCategory, 2));
                }
            }
        });
    }
}
