package javafxapplication2;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Products {
    private SimpleStringProperty productID, productName;
    private SimpleIntegerProperty productInventory;
    private SimpleDoubleProperty productPrice;
    
    public Products(String productID, String productName, Integer productInventory, Double productPrice) {
        this.productID = new SimpleStringProperty(productID);
        this.productName = new SimpleStringProperty(productName);
        this.productInventory = new SimpleIntegerProperty(productInventory);
        this.productPrice = new SimpleDoubleProperty(productPrice);
    }

    public String getProductID() {
        return productID.get();
    }

    public void setProductID(SimpleStringProperty productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName.get();
    }

    public void setProductName(SimpleStringProperty productName) {
        this.productName = productName;
    }

    public Integer getProductInventory() {
        return productInventory.get();
    }

    public void setProductInventory(SimpleIntegerProperty productInventory) {
        this.productInventory = productInventory;
    }

    public Double getProductPrice() {
        return productPrice.get();
    }

    public void setProductPrice(SimpleDoubleProperty productPrice) {
        this.productPrice = productPrice;
    }
}
