package org.example;

//Sub Class Clothing
public class Clothing extends Product{
    private String size;
    private String colour;
    //Constructor of Clothing Class
    public Clothing(String productId, String productName, int numOfAvailableItems, double price, String size, String colour) {
        super(productId, productName, numOfAvailableItems, price);
        this.size = size;
        this.colour = colour;
    }
    //Getters and Setters
    public void setSize(String size){
        this.size=size;
    }
    public String getSize(){
        return size;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }
    public String getColour() {
        return colour;
    }
}
