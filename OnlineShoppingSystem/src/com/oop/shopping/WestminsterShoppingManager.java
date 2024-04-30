package com.oop.shopping;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.*;
public class WestminsterShoppingManager implements ShoppingManager{
    public static final int MAX_ITEMS = 50;  //system can contain only 50 products
    List<Product> productList = new ArrayList<>();
    private int freeItems = MAX_ITEMS;
    @Override
    public void addProduct(Product product){
        if(freeItems > 0){
            productList.add(product);
            freeItems--;
            System.out.println("Product added to the System!");
        }
        else{
            System.out.println("System is Full! Only 50 products can be added.");
        }
    }

    @Override
    public void deleteProduct(String productId) {
        if (productList.isEmpty()){
            System.out.println("Products are not yet entered to the system.");
        }
        else{
            boolean found = false;
            for (Product product : productList){
                if (product.getProductId().equals(productId)){
                    found = true;     //if the productId id valid
                    productList.remove(product);
                    //Format specifiers are used to indicate the information of the product removed
                    System.out.printf("The %s has been deleted from the System.%n" , product instanceof Electronics ? "Electronic" : "Clothing");

                    System.out.println("Total number of products remaining: " + productList.size());
                    break;
                }
            }
            if (!found){
                System.out.println("Invalid productId! Please check and try again!");
            }
        }
    }

    @Override
    public void printProductList() {
        if (productList.isEmpty()){
            System.out.println("Products are not yet added to the System!");
        }
        else{
            //sorting alphabetically according to the productId
            productList.sort(Comparator.comparing(Product::getProductId));

            System.out.println("List of Products Available in the System!");
            System.out.println();
            //Printing the list of products using  a for loop
            for (Product products : productList){
                System.out.println("ProductId: "+products.getProductId());
                System.out.println("Product Name: "+products.getProductName());
                System.out.println("No.of Items: "+products.getNoOfItems());
                System.out.println("Price of Product: "+products.getPriceProduct());

                //Checking for the product type Electronics or Clothing
                System.out.printf("Product Type: %s%n",products instanceof Electronics ? "Electronics" : "Clothing");
                System.out.println("----------------------------------------------------------------------");
            }
        }
    }

    @Override
    public void saveProductList() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("productsInfo.txt"))) {
            for (Product product : productList) {
                oos.writeObject(product);
            }
            System.out.println("Product List was saved to the file Successfully!");
        } catch (IOException e) {
            throw new RuntimeException("Error saving product list: " + e.getMessage());
        }
    }
    // method to get a product by its ID to add the no.of products to the product detail list below the table in the GUI
    public Product getProductById(String productId) {
        for (Product product : productList) {
            if (product.getProductId().equals(productId)) {
                return product;
            }
        }
        return null; // Return null if the product with the given ID is not found
    }


}
