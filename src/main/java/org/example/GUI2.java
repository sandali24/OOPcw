package org.example;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class GUI2 extends JFrame {
    public GUI2(List<Product> productsInCart) {
        // Create a frame with 600 width and 600 height
        setSize(600, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a panel for the frame
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1)); // Use grid layout to divide the panel into two rows

        // Create two sub-panels with different colors
        JPanel panel1 = new JPanel();
        panel1.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10)); // Added padding
        JPanel panel2 = new JPanel();
        panel2.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 10)); // Added padding

        // Table for shopping cart listing
        String[] columnNames = {"Product", "Quantity", "Price"};
        // Convert List<Product> to a two-dimensional array
        Object[][] data = new Object[productsInCart.size()][3];

        for (int i = 0; i < productsInCart.size(); i++) {
            Product product = productsInCart.get(i);
            data[i][0] = product.getProductId() + " - " + product.getProductName(); // Add a dash between product id and name
            data[i][1] = product.getNumOfAvailableItems();  // Assuming getAvailableItems returns the quantity
            data[i][2] = String.format("%.2f €", product.getPrice() * product.getNumOfAvailableItems());
            if (product instanceof Clothing) {
                // Assuming Clothing class has getSize and getColor methods
                data[i][0] += " (" + ((Clothing) product).getSize() + ", " + ((Clothing) product).getColour() + ")"; // Append the size and color in parentheses
            } else if (product instanceof Electronics) {
                // Assuming Electronic class has getBrand and getWarranty methods
                data[i][0] += " (" + ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod() + ")"; // Append the brand and warranty in parentheses
            }
        }

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        JTable table = new JTable(model); // Create a table with the data and column names
        JScrollPane scrollPane = new JScrollPane(table); // Create a scroll pane to hold the table
        panel1.add(scrollPane);

        // Adjust the width and height of the table and row size
        table.setPreferredScrollableViewportSize(new Dimension(500, 200)); // Set the preferred size of the scroll pane
        table.setRowHeight(50); // Set the row height to 50 pixels
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); // Set the table to resize all columns automatically

        // Make the table's cell's font size big and move letters in table cell's to the center
        table.setFont(new Font("Arial", Font.BOLD, 12)); // Set the font of the table to Arial, bold, and 18 points
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer(); // Create a cell renderer for center alignment
        centerRenderer.setHorizontalAlignment(JLabel.CENTER); // Set the horizontal alignment to center
        for (int i = 0; i < table.getColumnCount(); i++) { // Loop through all columns
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer); // Set the cell renderer for each column
        }

        ShoppingCart shoppingCartObj = new ShoppingCart();
        double[] totalCost = shoppingCartObj.calculateTotalCost(productsInCart);

// Create a panel with grid layout to hold the labels
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new GridLayout(4, 1)); // Use grid layout to divide the panel into four rows

// Create and add the labels to the label panel
        labelPanel.add(new JLabel("Total: " + totalCost[0]));
        labelPanel.add(new JLabel("First Purchase Discount (10%): - 8.58 €"));
        labelPanel.add(new JLabel("Three Items in the Same Category Discount (20%): " + totalCost[2]));
        labelPanel.add(new JLabel("Final Total: " + totalCost[1]));

// Add the label panel to the second panel
        panel2.add(labelPanel);

        // Add the sub-panels to the main panel
        mainPanel.add(panel1);
        mainPanel.add(panel2);

        // Add the main panel to the frame
        add(mainPanel);

        // Make the frame visible
        setVisible(true);
    }

}

