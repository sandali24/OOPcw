package org.example;

import java.io.Serializable;

//Sub Class Electronics
public class Electronics extends Product implements Serializable {
    private String brand;
    private String warrantyPeriod;

    //Constructor for Electronics
    public Electronics(String productId, String productName, int numOfAvailableItems, double price, String brand, String warrantyPeriod) {
        super(productId, productName, numOfAvailableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
    }

    //Getters and Setters of Electronic Class
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getBrand(){
        return brand;
    }
    public void setWarrantyPeriod(String warrantyPeriod){
        this.warrantyPeriod=warrantyPeriod;
    }
    public String getWarrantyPeriod(){
        return warrantyPeriod;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", brand= " + brand +
                ", warrantyPeriod= " + warrantyPeriod;
    }
}
