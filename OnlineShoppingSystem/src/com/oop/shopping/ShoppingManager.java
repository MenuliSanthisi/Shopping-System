package com.oop.shopping;

public interface ShoppingManager {
    void addProduct(Product product);
    void deleteProduct(String productId);
    void printProductList();
    void saveProductList();
}
