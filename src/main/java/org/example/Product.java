package org.example;

import java.io.Serializable;

//Abstract Super Class Product
public class Product implements Serializable {
    private String productId;
    private String productName;
    private int numOfAvailableItems;
    private double price;

    //Constructor of Product Class
    public Product(String productId, String productName, int numOfAvailableItems, double price) {
        this.productId = productId;
        this.productName = productName;
        this.numOfAvailableItems = numOfAvailableItems;
        this.price = price;
    }

    //Getters and Setters for Product Class
    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductName() {
        return productName;
    }

    public void setNumOfAvailableItems(int numOfAvailableItems) {
        this.numOfAvailableItems = numOfAvailableItems;
    }

    public int getNumOfAvailableItems() {
        return numOfAvailableItems;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String toString() {
        return "productId= " + productId +
                ", productName= " + productName +
                ", numOfAvailableItems= " + numOfAvailableItems +
                ", price= " + price;
    }

    public void decreaseAvailableItems() {
        if (numOfAvailableItems > 0) {
            numOfAvailableItems--;
        }
    }
}
