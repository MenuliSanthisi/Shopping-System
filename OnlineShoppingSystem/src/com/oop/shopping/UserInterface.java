package com.oop.shopping;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class UserInterface extends JPanel{
    private final WestminsterShoppingManager shoppingManager;
    private JTable productTable;
    private JComboBox<String> categorySelection;
    private JPanel productDetailsPanel;
    private final  ShoppingCart shoppingCart = new ShoppingCart();
    private JFrame cartFrame;
    private JTable cartTable;
    private final JPanel totalCostPanel = new JPanel();
    private final JLabel totalPriceLabel = new JLabel();
    private final DiscountTwenty discountManager = new DiscountTwenty();

    public UserInterface(WestminsterShoppingManager shoppingManager){
        this.shoppingManager = shoppingManager;
        initialize();
    }
    private void initialize() {

        JFrame frame = new JFrame("Westminster Shopping Manager");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        // Create a panel with BoxLayout for vertical alignment
        JPanel panel = new JPanel(new BorderLayout());

        // Label of ComboBox
        JLabel label = new JLabel("Select Product Category");
        // Create the combo box and center it
        categorySelection = new JComboBox<>(new String[]{"All", "Electronics", "Clothing"});
        //FlowLayout Adjustment
        JPanel comboBoxSelection = new JPanel(new FlowLayout(FlowLayout.LEFT, 50, 10));
        comboBoxSelection.add(label);
        comboBoxSelection.add(categorySelection);
        panel.add(comboBoxSelection, BorderLayout.NORTH);

        //Action listener is added to display information related to the specific product item when user selects the options from the comboBox
        categorySelection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayTableProducts();
            }
        });
        // Shopping cart button
        JButton shoppingButton = new JButton("Shopping Cart");
        shoppingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openShoppingCartFrame();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(comboBoxSelection);

        //Adding space between the ComboBox and Shopping cart
        buttonPanel.add(Box.createHorizontalStrut(10));
        buttonPanel.add(shoppingButton);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
        panel.add(buttonPanel, BorderLayout.NORTH);

        // Table with the Product information
        productTable = new JTable();
        productTable.setModel(new DefaultTableModel(new Object[][]{},
                new String[]{"ProductID", "Name", "Category", "Price(£)", "Info"}));

        JScrollPane scrollPane = new JScrollPane(productTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        panel.add(scrollPane, BorderLayout.CENTER); // Specify CENTER for the table

        frame.add(panel);
        frame.setVisible(true);

        // product details below the table
        productDetailsPanel = new JPanel(new GridLayout(0, 1));
        panel.add(productDetailsPanel, BorderLayout.SOUTH);

        //selection listener to the product table
        productTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = productTable.getSelectedRow();
            if (selectedRow >= 0) {
                displayProductDetails(selectedRow);
            }
        });
        totalCostPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        totalCostPanel.add(totalPriceLabel);
        //custom cell renderer red colour for the columns where number of items is less than 3
        RedItems redItemsRenderer = new RedItems(shoppingManager);
        //accessing each column in the product table to turn it red
        for (int i = 0; i < productTable.getColumnModel().getColumnCount(); i++) {
            productTable.getColumnModel().getColumn(i).setCellRenderer(redItemsRenderer);
        }
    }
    //Displaying the product information in the product table
    public void displayTableProducts() {
        DefaultTableModel tableModel = (DefaultTableModel) productTable.getModel();
        tableModel.setRowCount(0); // Clear the table
        String productId;
        String productName;
        String productCategory;
        double productPrice;
        String info;
        String selectedCategory = (String) categorySelection.getSelectedItem();

        // method to extract sorted products
        List<Product> sortedProducts = getSortedProducts(selectedCategory);

        for (Product product : sortedProducts) {
            productId = product.getProductId();
            productName = product.getProductName();
            productCategory = product.getClass().getSimpleName();
            productPrice = product.getPriceProduct();

            if (product instanceof Clothing) {
                info = ((Clothing) product).getSize() + "," + ((Clothing) product).getColor();
            } else {
                info = ((Electronics) product).getBrand() + "," + ((Electronics) product).getWarrantyPeriod();
            }
            tableModel.addRow(new Object[]{productId, productName, productCategory, productPrice, info});
        }
    }
    //calling the above product table method
    public void displayProducts() {
        displayTableProducts();
    }
    //method for the Shopping Cart frame
    private void openShoppingCartFrame() {
        // Check if the cartFrame is already created
        if (cartFrame == null) {
            cartFrame = new JFrame("Shopping Cart");
            cartFrame.setSize(800, 600);
            cartFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            cartFrame.setLocationRelativeTo(null);

            cartTable = new JTable();
            cartTable.setModel(new DefaultTableModel(new Object[][]{},
                    new String[]{"Product", "Quantity", "Price"}));

            JScrollPane scrollPane = new JScrollPane(cartTable);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(50,50,50,50));
            cartFrame.add(scrollPane);
        }
        cartFrame.setSize(800,600);
        cartFrame.add(totalCostPanel, BorderLayout.SOUTH);

        // Update the cart table with products selected
        updateCartTable();
        cartFrame.setVisible(true);
        cartFrame.toFront();
    }
    //method where the Shopping Cart table is updated
    private void updateCartTable() {
        DefaultTableModel tableModel = (DefaultTableModel) cartTable.getModel();
        tableModel.setRowCount(0);
        double totalPrice = 0;
        // method calculateDiscount in the DiscountTwenty class is called on an object discountManager
        double discount = discountManager.calculateDiscount(shoppingCart.getProductList());

        for (Product product : shoppingCart.getProductList()) {
            String productInfo = getProductDetails(product);
            int quantity = product.getNoOfItems();
            double price = product.getPriceProduct() * quantity;
            tableModel.addRow(new Object[]{productInfo, quantity, price+" £"});

            // Adding the price of each product in cart
            totalPrice += price;
            // Add the discounted price to the total
            totalPrice += price - (price * 0.2); // Assuming discount is already applied to product prices
        }
        // Create a container panel to occupy the full width
        JPanel fullWidthPanel = new JPanel();
        fullWidthPanel.setLayout(new BorderLayout());

        //container panel for total price and discount price with vertical layout
        JPanel pricePanel = new JPanel();
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));

        //total price label
        JLabel totalPriceLabel = new JLabel("Total                                                                                                    "
                + totalPrice + " £");
        pricePanel.add(totalPriceLabel);
        // Add a gap between total price and 20%discount label
        pricePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        // 20% discount label
        JLabel discountLabel = new JLabel("Three items in same category Discount(20%)                             " + "-"+discount + " £");
        pricePanel.add(discountLabel);
        //gap between 20% discount and final total
        pricePanel.add(Box.createRigidArea(new Dimension(0, 30)));
        //Final Total
        JLabel finalPriceLabel = new JLabel("Final Total                                                                                           "
                + (totalPrice - discount) + " £");
        pricePanel.add(finalPriceLabel);

        //empty border to the bottom of the 20% discount line
        pricePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 30, 100));

        //total price and 20% discount price labels are right side of fullWidthPanel
        fullWidthPanel.add(pricePanel, BorderLayout.EAST);

        // Adding fullWidthPanel to totalCostPanel
        totalCostPanel.add(fullWidthPanel);
        totalCostPanel.revalidate();
    }
    //This displays the information under the product column in shopping cart table
    private String getProductDetails(Product product) {
        StringBuilder builder = new StringBuilder();
        builder.append(product.getProductId()).append(" - ");
        builder.append(product.getProductName()).append(" - ");
        if (product instanceof Clothing clothing) {
            builder.append(clothing.getSize()).append(" - ");
            builder.append(clothing.getColor());
        } else if (product instanceof Electronics electronics) {
            builder.append(electronics.getBrand()).append(" - ");
            builder.append(electronics.getWarrantyPeriod()).append(" months");
        }
        return builder.toString();
    }
    //the array list is above is returned to display all the products in alphabetical order when All is selected in Combo Box
    private List<Product> getSortedProducts(String selectedCategory) {
        //list for sorted products
        List<Product> sortedProducts = new ArrayList<>();
        //products based on selected category(combo box)
        for (int i = 0; i < shoppingManager.productList.size(); i++) {
            Product product = shoppingManager.productList.get(i);
            if (Objects.equals(selectedCategory, "All") || product.getClass().getSimpleName().equals(selectedCategory)) {
                // Adding products to the new sortedProduct ArrayList
                sortedProducts.add(product);
            }
        }
        // products sorting in alphabetically by productID
        sortedProducts.sort(Comparator.comparing(Product::getProductId));
        return sortedProducts;
    }
    //method that display the product information when the user clicks a row of the specific item
    private void displayProductDetails(int selectedRow) {
        // Clear the existing product details
        productDetailsPanel.removeAll();

        // showing product details of the selected row
        String productId = (String) productTable.getValueAt(selectedRow, 0);
        String productCategory = (String) productTable.getValueAt(selectedRow, 2);
        String productName = (String) productTable.getValueAt(selectedRow, 1);
        String info = (String) productTable.getValueAt(selectedRow, 4);

        JLabel productTitle = new JLabel("Selected Product  -  Details");
        productDetailsPanel.add(productTitle);
        // Labels for product details
        JLabel productIdLabel = new JLabel("Product ID: " + productId);
        JLabel productCategoryLabel = new JLabel("Category: " + productCategory);
        JLabel productNameLabel = new JLabel("Product Name: " + productName);

        //checking if the product is clothing or electronic
        if ("Clothing".equals(productCategory)) {
            // Clothing item, extract size and color from the Info column
            String[] clothingInfo = info.split(",");
            String size = clothingInfo[0];
            String color = clothingInfo[1];

            JLabel sizeLabel = new JLabel("Size: " + size);
            JLabel colorLabel = new JLabel("Color: " + color);

            //product detail list for clothing
            productDetailsPanel.add(productIdLabel);
            productDetailsPanel.add(productCategoryLabel);
            productDetailsPanel.add(productNameLabel);
            productDetailsPanel.add(sizeLabel);
            productDetailsPanel.add(colorLabel);
        } else if ("Electronics".equals(productCategory)) {
            // Electronic item, extract brand and warranty period from the Info column
            String[] electronicsInfo = info.split(",");
            String brand = electronicsInfo[0];
            String warrantyPeriod = electronicsInfo[1];

            JLabel brandLabel = new JLabel("Brand: " + brand);
            JLabel warrantyLabel = new JLabel("Warranty Period: " + warrantyPeriod+"  months");

            //product detail list for electronics
            productDetailsPanel.add(productIdLabel);
            productDetailsPanel.add(productNameLabel);
            productDetailsPanel.add(productCategoryLabel);
            productDetailsPanel.add(brandLabel);
            productDetailsPanel.add(warrantyLabel);
        }
        //getting the No.of items from the array list to display it in the product list
        productId = (String) productTable.getValueAt(selectedRow, 0);
        Product product = shoppingManager.getProductById(productId);
        int numItems = product.getNoOfItems();
        //label to display the number of items
        JLabel numItemsLabel = new JLabel("Number of Items: " + numItems);
        // Adding label to the product details list
        productDetailsPanel.add(numItemsLabel);

        //"Add to shopping cart" button below product details
        JButton addToCartButton = new JButton("Add to Shopping Cart");
        //padding the button
        addToCartButton.setBorder(BorderFactory.createEmptyBorder(10, 100, 5, 100));
        productDetailsPanel.add(addToCartButton);

        //listener when the user clicks the Add to shopping cart button
        addToCartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String productId = (String) productTable.getValueAt(selectedRow, 0);
                Product product = shoppingManager.getProductById(productId);
                shoppingCart.addProduct(product,1);

                // Update the shopping cart table if already open
                if (cartFrame != null) {
                    updateCartTable();
                }
                //Message shows the product was added to the cart successfully
                JOptionPane.showMessageDialog(null, "Product added to cart successfully!");
            }
        });
        productDetailsPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 30, 100));
        // refresh the panel to update changes
        productDetailsPanel.revalidate();
        productDetailsPanel.repaint();
        // Update the UI to show changes
        updateUI();
    }
}
