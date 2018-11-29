package javafxapplication2.Models;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.ObservableList;

public class Products {
    private SimpleStringProperty productID, productName;
    private SimpleIntegerProperty productInventory;
    private SimpleStringProperty productPrice;
    private SimpleIntegerProperty productMax;
    private SimpleIntegerProperty productMin;
    private ObservableList<Parts> parts;
    
    public Products(String productID, String productName, Integer productInventory, String productPrice, Integer productMax, Integer productMin, ObservableList<Parts> parts) {
        this.productID = new SimpleStringProperty(productID);
        this.productName = new SimpleStringProperty(productName);
        this.productInventory = new SimpleIntegerProperty(productInventory);
        this.productPrice = new SimpleStringProperty(productPrice);
        this.productMax = new SimpleIntegerProperty(productMax);
        this.productMin = new SimpleIntegerProperty(productMin);
        this.parts = parts;
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

    public ObservableList<Parts> getParts() {
        return parts;
    }
    
    public void addAssociatedPart(Parts part, ObservableList<Parts> parts) {
        parts.add(part);
    }
    
    public void removeAssociated(Parts part, ObservableList<Parts> parts) {
        parts.remove(part);
    }
    
    public Parts lookupAssociated(Integer integer) {
        return parts.get(integer);
    }
}
