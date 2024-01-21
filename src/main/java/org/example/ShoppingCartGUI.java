//package org.example;
//
//import javax.swing.*;
//import javax.swing.table.DefaultTableModel;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.example.WestminsterShoppingManager.listOfProductsUser;
//
//public class ShoppingCartGUI {
//    public ShoppingCartGUI(List<Product> productsInCart) {
//
//            // Create the main window (frame)
//            JFrame frame = new JFrame("Shopping Cart");
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//            // Convert List<Product> to a two-dimensional array
//            Object[][] data = new Object[productsInCart.size()][3];
//
//            for (int i = 0; i < productsInCart.size(); i++) {
//                Product product = productsInCart.get(i);
//                data[i][0] = product.getProductId() + " - " + product.getProductName(); // Add a dash between product id and name
//                data[i][1] = product.getNumOfAvailableItems();  // Assuming getAvailableItems returns the quantity
//                data[i][2] = String.format("%.2f €", product.getPrice() * product.getNumOfAvailableItems());
//                if (product instanceof Clothing) {
//                    // Assuming Clothing class has getSize and getColor methods
//                    data[i][0] += " (" + ((Clothing) product).getSize() + ", " + ((Clothing) product).getColour() + ")"; // Append the size and color in parentheses
//                } else if (product instanceof Electronics) {
//                    // Assuming Electronic class has getBrand and getWarranty methods
//                    data[i][0] += " (" + ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarrantyPeriod() + ")"; // Append the brand and warranty in parentheses
//                }
////                Product product = productsInCart.get(i);
////                data[i][0] = product.getProductId() + product.getProductName();
////                data[i][1] = product.getNumOfAvailableItems();  // Assuming getAvailableItems returns the quantity
////                data[i][2] = String.format("%.2f €", product.getPrice() * product.getNumOfAvailableItems());
//            }
//
//            // Table for shopping cart listing
//            String[] columnNames = {"Product", "Quantity", "Price"};
//
//            DefaultTableModel model = new DefaultTableModel(data, columnNames) {
//                public boolean isCellEditable(int row, int column) {
//                    return false; // Make table non-editable
//                }
//            };
//            JTable table = new JTable(model);
//            table.setPreferredScrollableViewportSize(new Dimension(400, 100));
//            table.setFillsViewportHeight(true);
//            JScrollPane scrollPane = new JScrollPane(table);// Panel for totals and discounts
//            JPanel totalsPanel = new JPanel();
//            totalsPanel.setLayout(new BoxLayout(totalsPanel, BoxLayout.Y_AXIS));
//            ShoppingCart shoppingCartObj = new ShoppingCart();
//            double[] totalCost = shoppingCartObj.calculateTotalCost(productsInCart);
//
//            totalsPanel.add(new JLabel("Total: "+ "" +" " + totalCost[0]));
//            totalsPanel.add(new JLabel("First Purchase Discount (10%): - 8.58 €"));
//            totalsPanel.add(new JLabel("Three Items in the Same Category Discount (20%): " + " "+ " " + totalCost[2]));
//            totalsPanel.add(new JLabel("Final Total: "+ " "+ " "+ totalCost[1]));
//             //Main panel to organize table and totals panel
//            JPanel mainPanel = new JPanel(new BorderLayout());
//            // Swap the order and change the regions
//            mainPanel.add(totalsPanel, BorderLayout.NORTH);
//            mainPanel.add(scrollPane, BorderLayout.SOUTH);
//
//            // Add the main panel to the frame
//            frame.add(mainPanel);
//
//            // Set the size and position of the frame
//            frame.pack();
//            frame.setLocationRelativeTo(null);
//
//            // Display the window
//            frame.setVisible(true);
//        }
//
//    }
//        // Create the main window (frame)
//        JFrame frame = new JFrame("Shopping Cart");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Convert List<Product> to a two-dimensional array
//        Object[][] data = new Object[productsInCart.size()][3];
//
//        for (int i = 0; i < productsInCart.size(); i++) {
//            Product product = productsInCart.get(i);
//            data[i][0] = product.getProductName();
//            data[i][1] = product.getNumOfAvailableItems();  // Assuming getAvailableItems returns the quantity
//            data[i][2] = String.format("%.2f €", product.getPrice() * product.getNumOfAvailableItems());
//        }
//
//        // Table for shopping cart listing
//        String[] columnNames = {"Product", "Quantity", "Price"};
//
//        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
//            public boolean isCellEditable(int row, int column) {
//                return false; // Make table non-editable
//            }
//        };
//        JTable table = new JTable(model);
//        table.setPreferredScrollableViewportSize(new Dimension(400, 100));
//        table.setFillsViewportHeight(true);
//        JScrollPane scrollPane = new JScrollPane(table);// Panel for totals and discounts
//        JPanel totalsPanel = new JPanel();
//        totalsPanel.setLayout(new BoxLayout(totalsPanel, BoxLayout.Y_AXIS));
//        ShoppingCart shoppingCartObj = new ShoppingCart();
//        double[] totalCost = shoppingCartObj.calculateTotalCost(productsInCart);
//        totalsPanel.add(new JLabel("Total: " + "" + " " + totalCost));
//
//        totalsPanel.add(new JLabel("Total: " + "" + " " + totalCost[0]));
//        totalsPanel.add(new JLabel("First Purchase Discount (10%): - 8.58 €"));
//        totalsPanel.add(new JLabel("Three Items in the Same Category Discount (20%): " + " " + " " + totalCost[2]));
//        totalsPanel.add(new JLabel("Final Total: " + " " + " " + totalCost[1]));
//        // Main panel to organize table and totals panel
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.add(scrollPane, BorderLayout.CENTER);
//        // Uncomment this line and change the region to NORTH
//        mainPanel.add(totalsPanel, BorderLayout.NORTH);
//
//        // Add the main panel to the frame
//        frame.add(mainPanel);
//
//        // Set the size and position of the frame
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//
//        // Display the window
//        frame.setVisible(true);
//    }
//}

//        // Create the main window (frame)
//        JFrame frame = new JFrame("Shopping Cart");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // Convert List<Product> to a two-dimensional array
//        Object[][] data = new Object[productsInCart.size()][3];
//
//        for (int i = 0; i < productsInCart.size(); i++) {
//            Product product = productsInCart.get(i);
//            data[i][0] = product.getProductName();
//            data[i][1] = product.getNumOfAvailableItems();  // Assuming getAvailableItems returns the quantity
//            data[i][2] = String.format("%.2f €", product.getPrice() * product.getNumOfAvailableItems());
//        }
//
//        // Table for shopping cart listing
//        String[] columnNames = {"Product", "Quantity", "Price"};
//
//        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
//            public boolean isCellEditable(int row, int column) {
//                return false; // Make table non-editable
//            }
//        };
//        JTable table = new JTable(model);
//        table.setPreferredScrollableViewportSize(new Dimension(400, 100));
//        table.setFillsViewportHeight(true);
//        JScrollPane scrollPane = new JScrollPane(table);
//
//
//
//
//
//
//
//
//
//
//
//
//
//        // Panel for totals and discounts
//        JPanel totalsPanel = new JPanel();
//        totalsPanel.setLayout(new BoxLayout(totalsPanel, BoxLayout.Y_AXIS));
//        ShoppingCart shoppingCartObj = new ShoppingCart();
//        double[] totalCost = shoppingCartObj.calculateTotalCost(productsInCart);
//        totalsPanel.add(new JLabel("Total: "+ "" +" " + totalCost));
//
//        totalsPanel.add(new JLabel("Total: "+ "" +" " + totalCost[0]));
//        totalsPanel.add(new JLabel("First Purchase Discount (10%): - 8.58 €"));
//        totalsPanel.add(new JLabel("Three Items in the Same Category Discount (20%): " + " "+ " " + totalCost[2]));
//        totalsPanel.add(new JLabel("Final Total: "+ " "+ " "+ totalCost[1]));
//
//
//
//
//
//
//
//
//
//
//
//        // Main panel to organize table and totals panel
//        JPanel mainPanel = new JPanel(new BorderLayout());
//        mainPanel.add(scrollPane, BorderLayout.CENTER);
////        mainPanel.add(totalsPanel, BorderLayout.SOUTH);
//
//        // Add the main panel to the frame
//        frame.add(mainPanel);
//
//        // Set the size and position of the frame
//        frame.pack();
//        frame.setLocationRelativeTo(null);
//
//        // Display the window
//        frame.setVisible(true);
//    }
//
//
////        DefaultTableModel tableModel = new DefaultTableModel();
////        tableModel.addColumn("Product ID");
////        tableModel.addColumn("Name");
////        tableModel.addColumn("Category");
////        tableModel.addColumn("Price");
////        tableModel.addColumn("Info");
////        for (Product product : productsInCart) {
////            String category = "";
////            String details = "";
////            if (product instanceof Electronics) {
////                category = "Electronics";
////                details = ((Electronics) product).getBrand() + " ," + " " + ((Electronics) product).getWarrantyPeriod();
////            } else if (product instanceof Clothing) {
////                category = "Clothing";
////                details = ((Clothing) product).getSize() + " ," + " " + ((Clothing) product).getColour();
////            }
////            tableModel.addRow(new Object[]{
////                    product.getProductId(),
////                    product.getProductName(),
////                    category,
////                    String.format("%.2f €", product.getPrice()),
////                    details
////            });
////        }
////        JTable table = new JTable(tableModel);
////
////// Add the table to a scroll pane
////        JScrollPane scrollPane = new JScrollPane(table);
////
////// Add the scroll pane to the panel
////        panel1.add(scrollPane);
////
////        frame.add(panel1);
////        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
////        frame.setSize(400,400);
////        frame.setVisible(true);
////    }






