package com.oop.shopping;

import java.io.*;
import java.util.Scanner;

public class ConsoleMenu {
    //creating the object for WestminsterShoppingManager
    static ShoppingManager manager = new WestminsterShoppingManager();
    //getting user inputs
    final static Scanner USER_INPUT = new Scanner(System.in);

    public static void main(String[] args){
        loadProductList();

        choiceLoop:
        while(true){
            displayMenu();
            int choice  = USER_INPUT.nextInt();
            switch(choice){
                case 1:
                    addProduct();
                    break;
                case 2:
                    deleteProduct();
                    break;
                case 3:
                    printProductList();
                    break;
                case 4:
                    saveProductList();
                    break;
                case 5:
                    openGraphicalInterface();
                    break;
                case 6:
                    System.out.println("Thank you for Using the system!");
                    break choiceLoop;
                default:
                    System.out.println("Selected an Invalid Option. Please Try Again!");
            }
        }
    }

    private static void displayMenu() {
        System.out.println("******************************************************************************************");
        System.out.println("  Welcome to the Westminster Shopping Manager! Please select the Option You would Like: ");
        System.out.println("******************************************************************************************");
        System.out.println();
        System.out.println("1. Add a new product");
        System.out.println("2. Delete a product");
        System.out.println("3. Print the List of product");
        System.out.println("4. Save in a File");
        System.out.println("5: Open the Graphical User Interface");
        System.out.println("6. Quit the Application");
        System.out.println();
        System.out.print("Please enter the option: ");
    }
    private static void addProduct() {
        Product product;
        String choice;
        do{
            System.out.println("Please Enter the type of product (Electronic/Clothing):");
            choice = USER_INPUT.next().toLowerCase();  //to make sure that all the inputs entered by the user is in lower case

            //validating so that the user can input only 'electronic' or 'clothing'
            if(!choice.equals("electronic") && !choice.equals("clothing")){
                System.out.println("Invalid Product type! Please enter either 'Electronic or 'Clothing'");
            }
        }while(!choice.equals("electronic") && !choice.equals("clothing"));


        //validation for the productId
        String productId;
        do {
            System.out.println("Please Enter the productID (eg: AD1256): ");
            productId = USER_INPUT.next();

            if (!productId.isEmpty()) {
                if (productId.matches("^[A-Z]{2}\\d{4}$")) {
                    break; // Valid product ID entered, exit the loop
                } else {
                    System.out.println("ProductId invalid! Please try again.");
                }
            } else {
                System.out.println("ProductId cannot be empty. Please try again.");
            }
        } while (true);

        //Product Name
        System.out.println("Please Enter the Name of product: ");
        String productName = USER_INPUT.next();

        //Number of Items
        System.out.println("Please Enter the no.of items: ");
        int noOfItems = USER_INPUT.nextInt();

        //Product Price
        System.out.println("Please Enter the Price of product: ");
        double priceProduct = USER_INPUT.nextDouble();

        switch(choice){
            //Getting inputs for Electronic items
            case"electronic":
                System.out.println("Please enter the Brand: ");
                String brand = USER_INPUT.next();

                System.out.println("Please enter the warranty period (give in months): ");
                double warrantyPeriod = USER_INPUT.nextDouble();
                product = new Electronics(productId,productName,noOfItems,priceProduct,brand,warrantyPeriod);
                break;
            //Getting Clothing items
            case "clothing":
                //validating the size of the item entered by the user
                String size;
                do{
                    System.out.println("Please enter the size of the clothing product (Small,Medium,Large): ");
                    size = USER_INPUT.next().toLowerCase();
                    if(!size.equals("small") && !size.equals("medium") && !size.equals("large")){
                        System.out.println("Invalid Product Size! Please Enter either (Small/Medium/Large)");
                    }
                }while(!size.equals("small") && !size.equals("medium") && !size.equals("large"));


                System.out.println("Please enter the color of the clothing product: ");
                String color = USER_INPUT.next();

                product = new Clothing(productId,productName,noOfItems,priceProduct,size,color);
                break;

            default:
                product = null;
        }
        manager.addProduct(product);
    }
    private static void deleteProduct() {
        while(true) {
            System.out.println("Please enter the productID (eg: AD1256): ");
            String productId = USER_INPUT.next();

            //Validating the productId using the RegEx Validation method
            if (productId.matches("^[A-Z]{2}\\d{4}$")) {
                System.out.println("ProductId Valid! Product deleted!");
                try{
                    manager.deleteProduct(productId);
                    break;
                }catch (Exception e){
                    System.out.println("Error deleting product: " + e.getMessage());
                }
            } else {
                System.out.println("Invalid ProductID!Please type in the correct pattern!");
            }
        }
    }
    private static void printProductList() {
        manager.printProductList();
    }
    private static void saveProductList() {
        manager.saveProductList();
    }
    private static void loadProductList() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("productsInfo.txt"))) {
            while (true) {
                try {
                    Object object = in.readObject();
                    if (object instanceof Product product) {
                        manager.addProduct(product);
                    }
                } catch (EOFException e) {
                    // Break the loop end of te file reached
                    break;
                }
            }
            System.out.println("Product list was loaded successfully from productsInfo.txt");
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error loading products: " + e.getMessage());
        }
    }
    private static void openGraphicalInterface(){
        //Initiating the UserInterface class
        UserInterface shoppingGUI = new UserInterface((WestminsterShoppingManager) manager);
        shoppingGUI.displayProducts();
    }
}
