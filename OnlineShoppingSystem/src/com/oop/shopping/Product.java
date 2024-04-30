package com.oop.shopping;
import java.io.Serializable;

public abstract class Product implements Serializable{
    private String productId;
    private String productName;
    private int noOfItems;
    private double priceProduct;

    //initializing by creating a constructor
    public Product(String productId, String productName, int noOfItems, double priceProduct){
        this.productId = productId;
        this.productName = productName;
        this.noOfItems = noOfItems;
        this.priceProduct = priceProduct;

    }
    public String getProductId(){
        return productId;
    }
    public void setProductId(String productId){
        this.productId = productId;
    }
    public String getProductName(){
        return productName;
    }
    public void setProductName(String productName){
        this.productName = productName;
    }
    public int getNoOfItems(){
        return noOfItems;
    }
    public void setNoOfItems(int noOfItems){
        this.noOfItems = noOfItems;
    }
    public double getPriceProduct(){
        return priceProduct;
    }
    public void setPriceProduct(double priceProduct){
        this.priceProduct = priceProduct;
    }

    @Override
    public String toString(){
        return "productId: " + productId + '\'' +
                ", productName: " + productName +
                ", noOfItems: " + noOfItems +
                "," +
                ", priceProduct: " + priceProduct;
    }
}
