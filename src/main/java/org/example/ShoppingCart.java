package org.example;

import java.util.ArrayList;

public class ShoppingCart {
    private ArrayList<Product> listOfProducts;

//    public ShoppingCart(){
//        this.listOfProducts=new ArrayList<>();
//    }

    //Constructor of ShoppingCart
    public ShoppingCart(){
        this.listOfProducts = new ArrayList<>();
    }

    //Getters and Setters
    public void setListOfProducts(ArrayList<Product> listOfProducts) {
        this.listOfProducts = listOfProducts;
    }
    public ArrayList<Product> getListOfProducts() {
        return listOfProducts;
    }

    public void addProduct(Product product){
        listOfProducts.add(product);
    }
    public void removeProduct(Product product){
        listOfProducts.remove(product);
    }
    public double calculateTotalCost(){
        double totalCost=0;
        for (Product product:listOfProducts){
            totalCost += product.getPrice();
        }
        return totalCost;
    }
}

