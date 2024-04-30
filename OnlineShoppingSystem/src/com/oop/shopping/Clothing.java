package com.oop.shopping;

public class Clothing extends Product{
    private String size;
    private String color;
    public Clothing(String productId, String productName, int noOfItems, double priceProduct,String size, String color){
        super(productId,productName,noOfItems,priceProduct);

        this.size = size;
        this.color = color;
    }
    public String getSize(){
        return size;
    }
    public void setSize(String size){
        this.size = size;
    }
    public String getColor(){
        return color;
    }
    public void setColor(String color){
        this.color = color;
    }
    @Override
    public String toString(){
        return "Clothing{ " + super.toString() +
                "size: " + size +
                "color: " + color +
                " }";
    }
}
