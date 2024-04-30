package com.oop.shopping;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> productList = new ArrayList<>();//private is used for encapsulation

    public List<Product> getProductList() {
        return productList;
    }
    public void addProduct(Product product, int quantity){
        this.productList.add(product);
        System.out.println();
        System.out.println(product.getProductName()+" "+"product added to the cart!");
    }
    public void removeProduct(String productName){
        for(Product product : productList){
            if(product.getProductName().equals(productName)){
                productList.remove(product);
                System.out.println("Product removed from the cart!");
                return;
            }
        }
        System.out.println("Product not fount in the cart!");
    }
    public double calculateTotalCost(){
        double totalCost = 0;
        for(Product product : productList){
            totalCost += product.getPriceProduct()*product.getNoOfItems();
        }
        return totalCost;
    }
}
