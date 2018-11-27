package javafxapplication2.Models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Products {
    private SimpleStringProperty productID, productName;
    private SimpleIntegerProperty productInventory;
    private SimpleStringProperty productPrice;
    private SimpleIntegerProperty productMax;
    private SimpleIntegerProperty productMin;
    private SimpleStringProperty productCompanyMachineID; 
    
    public Products(String productID, String productName, Integer productInventory, String productPrice, Integer productMax, Integer productMin, String productCompanyMachineID) {
        this.productID = new SimpleStringProperty(productID);
        this.productName = new SimpleStringProperty(productName);
        this.productInventory = new SimpleIntegerProperty(productInventory);
        this.productPrice = new SimpleStringProperty(productPrice);
        this.productMax = new SimpleIntegerProperty(productMax);
        this.productMin = new SimpleIntegerProperty(productMin);
        this.productCompanyMachineID = new SimpleStringProperty(productCompanyMachineID);
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

    public String getProductPrice() {
        return productPrice.get();
    }

    public void setProductPrice(SimpleStringProperty productPrice) {
        this.productPrice = productPrice;
    }

    public Integer getProductMax() {
        return productMax.get();
    }

    public void setProductMax(SimpleIntegerProperty productMax) {
        this.productMax = productMax;
    }

    public Integer getProductMin() {
        return productMin.get();
    }

    public void setProductMin(SimpleIntegerProperty productMin) {
        this.productMin = productMin;
    }

    public String getProductCompanyMachineID() {
        return productCompanyMachineID.get();
    }

    public void setProductCompanyMachineID(SimpleStringProperty productCompanyMachineID) {
        this.productCompanyMachineID = productCompanyMachineID;
    }
    
    
}
