package javafxapplication2.Models;
    
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    public static ObservableList<Parts> allParts;
    public static ObservableList<Products> allProducts;
    
    public static void addCompanyPart(Parts part) {
        allParts.add(part);
    }
    
    public static boolean deletePart(Parts part) {
        return false;
    }
    
//    public static Parts lookupPart(Integer integer) {
//        
//    }
    
    public static void updatePart(Integer integer) {
        
    }
    
    public static void addProduct(Products product) {
        allProducts.add(product);
    }
    
    public static boolean removeProduct(Integer integer) {
        return false;
    }
    
//    public static Products lookupProduct(Integer integer) {
//        
//    }
    
    public static void updateProduct(Integer integer) {
        
    }
        
}
