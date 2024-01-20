package org.example;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import static org.example.WestminsterShoppingManager.gui1;
import static org.example.WestminsterShoppingManager.listOfProductsUser;


public class GUI1 extends JFrame {
    static ArrayList<Product> productList = new ArrayList<>();
    public static ArrayList<Product> getProductList() {
        // Your logic to populate the list
        return productList;
    }

    public GUI1(ArrayList<Product> productList) {
        this.productList = productList;
//        openGUI();
    }
    static GUI2 gui2 = new GUI2();

    public static void openGUI() {
        JFrame frame = new JFrame("Westminster Shopping Center");
        frame.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        // Panel for selected product details (assuming this will be dynamically updated)
        JPanel detailsPanel = new JPanel();
        detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
        detailsPanel.setBackground(Color.PINK);
        detailsPanel.add(new JLabel("Selected Product - Details"));

//        JPanel panel3 = new JPanel(new FlowLayout(FlowLayout.LEFT)); // Changed to FlowLayout for vertical display
//        panel3.setPreferredSize(new Dimension(10, 10)); // Adjusted size for printed details
//        panel3.setBorder(BorderFactory.createEmptyBorder(0, -5, 0, 0)); // Added padding
        JPanel panel4 = new JPanel();
        JPanel subPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPanel subPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

//        JPanel subPanel3 = new JPanel(); // New subPanel3 for displaying saved data
//        subPanel3.setBorder(BorderFactory.createEmptyBorder(50,150,50,50)); // Adding border to subPanel3
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
                gui2.GUI2method();
            }
        });

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
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        JScrollPane scrollPane = new JScrollPane(table);
        panel2.add(scrollPane);

        // Add ListSelectionListener to the table
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();

                    if (selectedRow != -1) {
                        String selectedProductID = (String) table.getValueAt(selectedRow, 0);
                        String selectedProductName = (String) table.getValueAt(selectedRow, 1);
                        String selectedCategory = (String) table.getValueAt(selectedRow, 2);
                        String selectedPrice = (String) table.getValueAt(selectedRow, 3);
                        String selectedDetails = (String) table.getValueAt(selectedRow, 4);

                        // Split the details string by comma and store the result in an array
                        String[] detailsArray = selectedDetails.split(",");

// Check the category of the product and assign the appropriate labels to the array elements
                        String label1, label2;
                        if (selectedCategory.equals("Electronics")) {
                            label1 = "Brand: ";
                            label2 = "Warranty Period: ";
                        } else if (selectedCategory.equals("Clothing")) {
                            label1 = "Size: ";
                            label2 = "Colour: ";
                        } else {
                            label1 = "Unknown: ";
                            label2 = "Unknown: ";
                        }

// Display the labels and the array elements in the details panel
//                        detailsPanel.add(new JLabel(label1 + detailsArray[0]));
//                        detailsPanel.add(new JLabel(label2 + detailsArray[1]));


                        // Update detailsPanel with selected row data
                        updateDetailsPanel(detailsPanel, selectedProductID, selectedProductName,
                                selectedCategory, selectedPrice, selectedDetails);
                        detailsPanel.add(new JLabel(label1 + detailsArray[0]));
                        detailsPanel.add(new JLabel(label2 + detailsArray[1]));
                    }
                }
            }
        });


        JButton button2 = new JButton("Add to Shopping Cart");


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
                    frame.add(detailsPanel, c);
                    c.gridx = 0;
                    c.gridy = 3;
                    frame.add(panel4, c);
                    frame.setSize(1000, 1000);
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    frame.setVisible(true);






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
    private static void updateDetailsPanel(JPanel detailsPanel, String productID, String productName,
                                           String category, String price, String details) {
        detailsPanel.removeAll();
        detailsPanel.add(new JLabel("Selected Product - Details"));
        detailsPanel.add(new JLabel("Product ID: " + productID));
        detailsPanel.add(new JLabel("Product Name: " + productName));
        detailsPanel.add(new JLabel("Category: " + category));
        detailsPanel.add(new JLabel("Price: " + price));
        detailsPanel.add(new JLabel("Details: " + details));


        detailsPanel.repaint();
        detailsPanel.revalidate();
    }





























            }
