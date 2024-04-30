package com.oop.shopping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountTwenty {
    //method takes an argument with list of products
    //is created to store the count of each category
    //here string is the name of category and integer will be the count of each category
    public double calculateDiscount(List<Product> products){
        double totalDiscount = 0.0;
        //map created to count the number of each item
        Map<String, Integer> categoryCounts = new HashMap<>();

        for (Product product : products) {
            //gets clothing or electronic and stores it as category
            String category = product.getClass().getSimpleName();
            //if category exist in the map increase the count , if the category doesn't exist add 1
            categoryCounts.put(category, categoryCounts.getOrDefault(category, 0) + 1);
        }
        //iterate through each category to get the count
        for (Map.Entry<String, Integer> entry : categoryCounts.entrySet()) {
            //if the category is recorded more than 3 times
            if (entry.getValue() >= 3) {
                for (Product product : products) {
                    if (product.getClass().getSimpleName().equals(entry.getKey())) {
                        double discount = 0.2 * product.getPriceProduct();
                        totalDiscount += discount;
                    }
                }
            }
        }
        return totalDiscount;
    }
}
