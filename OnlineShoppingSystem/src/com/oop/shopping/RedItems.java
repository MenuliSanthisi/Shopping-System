package com.oop.shopping;

import javax.swing.table.DefaultTableCellRenderer;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;

public class RedItems extends DefaultTableCellRenderer {
    private final WestminsterShoppingManager shoppingManager;

    //constructor for RedItems having the product information from the WestminsterShoppingManager class and UserInterface class
    public RedItems(WestminsterShoppingManager shoppingManager) {
        this.shoppingManager = shoppingManager;
    }
    //checks for the object,data value displayed in the cell,cell currently selected by the user,keyboard focus,row and column index being rendered
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        try {
            //accessing the number of items from the array list in WestminsterShoppingManager class
            int numItems = shoppingManager.productList.get(row).getNoOfItems();

            // checking if the number of items are less than 3
            if (numItems < 3) {
                setForeground(Color.RED);
            } else {
                setForeground(table.getForeground());
            }
        } catch (Exception e) {
            System.out.println("Error occurred while rendering!");
        }
        //return the table cell
        return this;
    }
}
