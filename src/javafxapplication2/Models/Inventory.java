package javafxapplication2.Models;
    
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
    
    public static Parts lookupPart(Integer integer) {
        return allParts.get(integer);
    }
    
    public static void updatePart(Integer integer, Parts part) {
        allParts.set(integer, part);
    }
    
    public static void addProduct(Products product) {
        allProducts.add(product);
    }
    
    public static boolean removeProduct(Integer integer) {
        return false;
    }
    
    public static Products lookupProduct(Integer integer) {
        return allProducts.get(integer);
    }
    
    public static void updateProduct(Integer integer, Products product) {
        allProducts.set(integer, product);
    }
        
}
