package org.example;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    public static ArrayList<Product> listOfProducts;

//    public ShoppingCart(){
//        this.listOfProducts=new ArrayList<>();
//    }

    //Constructor of ShoppingCart
    public ShoppingCart() {
        this.listOfProducts = new ArrayList<>();
    }

    //Getters and Setters
    public void setListOfProducts(ArrayList<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }

    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }

    void addProduct(Product product) {
        listOfProducts.add(product);
    }

    public void removeProduct(Product product) {
        listOfProducts.remove(product);
    }

    public double[] calculateTotalCost(List<Product> productsInCart) {
        double discount = 0;
        int electronic = 0;
        int clothing = 0;
        double totalCost = 0;
        double finalCost = 0;
        for (Product product : productsInCart) {
            if (product instanceof Electronics) {
                electronic += product.getNumOfAvailableItems();
            }
            if (product instanceof Clothing){
                clothing += product.getNumOfAvailableItems();
            }
            totalCost += (product.getPrice())*(product.getNumOfAvailableItems());
        }
        finalCost = totalCost;
        if (electronic >= 3 || clothing >= 3) {
            discount = totalCost * 0.2;
            finalCost = totalCost - discount;
        }

        // Create a new array of doubles and assign the values
        double[] result = new double[3];
        result[0] = totalCost;
        result[1] = finalCost;
        result[2] = discount;

        // Return the array
        return result;
    }
}

