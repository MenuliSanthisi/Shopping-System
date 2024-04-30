package com.oop.shopping;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;

class ConsoleMenuTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();

    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @Test
    void testAddProduct() throws Exception {
        // You can simulate user input for testing
        String input = "electronic\nAD1234\nTestProduct\n5\n100.0\nTestBrand\n6.0";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Use reflection to access the private addProduct method
        Method addProductMethod = ConsoleMenu.class.getDeclaredMethod("addProduct");
        addProductMethod.setAccessible(true);
        addProductMethod.invoke(null);  // Pass null for static methods, assuming addProduct is static

        // Assert statements based on your application's behavior
        assertEquals("""
        Please Enter the type of product (Electronic/Clothing):
        Please Enter the productID (eg: AD1256):
        Please Enter the Name of product:
        Please Enter the no.of items:
        Please Enter the Price of product:
        Please enter the Brand:
        Please enter the warranty period (give in months):
        """, outContent.toString());

        // Reset System.in to the original InputStream
        System.setIn(System.in);
    }

    @Test
    void testDeleteProduct() throws Exception {
        // You can simulate user input for testing
        String input = "AD1234";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Use reflection to access the private deleteProduct method
        Method deleteProductMethod = ConsoleMenu.class.getDeclaredMethod("deleteProduct");
        deleteProductMethod.setAccessible(true);
        deleteProductMethod.invoke(null);  // Pass null for static methods, assuming deleteProduct is static

        // Assert statements based on your application's behavior
        assertEquals("Please enter the productID (eg: AD1256): \n", outContent.toString());

        // Reset System.in to the original InputStream
        System.setIn(System.in);
    }

    // Add similar test methods for other methods in ConsoleMenu class

    @Test
    void testPrintProductList() throws Exception {
        // Use reflection to access the private printProductList method
        Method printProductListMethod = ConsoleMenu.class.getDeclaredMethod("printProductList");
        printProductListMethod.setAccessible(true);
        printProductListMethod.invoke(null);  // Pass null for static methods, assuming printProductList is static

        // Assert statements based on your application's behavior
        // For example, you can use assertEquals to compare the expected output with outContent.toString()

        // Reset System.in to the original InputStream
        System.setIn(System.in);
    }

    @Test
    void testSaveProductList() throws Exception {
        // Use reflection to access the private saveProductList method
        Method saveProductListMethod = ConsoleMenu.class.getDeclaredMethod("saveProductList");
        saveProductListMethod.setAccessible(true);
        saveProductListMethod.invoke(null);  // Pass null for static methods, assuming saveProductList is static

        // Assert statements based on your application's behavior
        // For example, you can use assertEquals to compare the expected output with outContent.toString()

        // Reset System.in to the original InputStream
        System.setIn(System.in);
    }

    @Test
    void testLoadProductList() throws Exception {
        // Use reflection to access the private loadProductList method
        Method loadProductListMethod = ConsoleMenu.class.getDeclaredMethod("loadProductList");
        loadProductListMethod.setAccessible(true);
        loadProductListMethod.invoke(null);  // Pass null for static methods, assuming loadProductList is static

        // Assert statements based on your application's behavior
        // For example, you can use assertEquals to compare the expected output with outContent.toString()

        // Reset System.in to the original InputStream
        System.setIn(System.in);
    }



    @Test
    void testOpenGraphicalInterface() throws Exception {
        // Use reflection to access the private openGraphicalInterface method
        Method openGraphicalInterfaceMethod = ConsoleMenu.class.getDeclaredMethod("openGraphicalInterface");
        openGraphicalInterfaceMethod.setAccessible(true);
        openGraphicalInterfaceMethod.invoke(null);  // Pass null for static methods, assuming openGraphicalInterface is static

        // Assert statements based on your application's behavior
        // For example, you can use assertEquals to compare the expected output with outContent.toString()

        // Reset System.in to the original InputStream
        System.setIn(System.in);
    }


    @Test
    void testMain() {
        // You can simulate user input for testing
        String input = "1\nelectronic\nAD1234\nTestProduct\n5\n100.0\nTestBrand\n6.0\n4\n6";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        // Test the main method
        ConsoleMenu.main(null);

        // Assert statements based on your application's behavior
        assertEquals("""
        ******************************************************************************************
          Welcome to the Westminster Shopping Manager! Please select the Option You would Like:
        ******************************************************************************************
        
        1. Add a new product
        2. Delete a product
        3. Print the List of product
        4. Save in a File
        5: Open the Graphical User Interface
        6. Quit the Application
        
        Please enter the option:
        Please Enter the type of product (Electronic/Clothing):
        Please Enter the productID (eg: AD1256):
        Please Enter the Name of product:
        Please Enter the no.of items:
        Please Enter the Price of product:
        Please enter the Brand:
        Please enter the warranty period (give in months):
        Please Enter the type of product (Electronic/Clothing):
        Please Enter the productID (eg: AD1256):
        Please Enter the Name of product:
        Please Enter the no.of items:
        Please Enter the Price of product:
        Please enter the Brand:
        Please enter the warranty period (give in months):
        Please enter the productID (eg: AD1256):
        ProductId Valid! Product deleted!
        Please enter the option:
        Please enter the option:
        
        ******************************************************************************************
          Welcome to the Westminster Shopping Manager! Please select the Option You would Like:
        ******************************************************************************************
        
        1. Add a new product
        2. Delete a product
        3. Print the List of product
        4. Save in a File
        5: Open the Graphical User Interface
        6. Quit the Application
        
        Please enter the option:
        Thank you for Using the system!
        """, outContent.toString());

        // Reset System.in to the original InputStream
        System.setIn(System.in);
    }

    @Test
    void testDisplayMenu() throws Exception {
        // Use reflection to access the private displayMenu method
        Method displayMenuMethod = ConsoleMenu.class.getDeclaredMethod("displayMenu");
        displayMenuMethod.setAccessible(true);
        displayMenuMethod.invoke(null);  // Pass null for static methods, assuming displayMenu is static

        // Assert statements based on your application's behavior
        // For example, you can use assertEquals to compare the expected output with outContent.toString()

        // Reset System.in to the original InputStream
        System.setIn(System.in);
    }

}