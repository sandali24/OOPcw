package org.example;

//Sub Class Electronics
public class Electronics extends Product{
    private String brand;
    private int warrantyPeriod;

    //Constructor for Electronics
    public Electronics(String productId, String productName, int numOfAvailableItems, double price, String brand, int warrantyPeriod) {
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
    public void setWarrantyPeriod(int warrantyPeriod){
        this.warrantyPeriod=warrantyPeriod;
    }
    public int getWarrantyPeriod(){
        return warrantyPeriod;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", brand= " + brand +
                ", warrantyPeriod= " + warrantyPeriod;
    }
}
