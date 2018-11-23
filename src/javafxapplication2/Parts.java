/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Parts {
    private SimpleStringProperty partID, partName;
    private SimpleIntegerProperty partInventory;
    private SimpleDoubleProperty partPrice;

    public Parts(String partID, String partName, Integer partInventory, Double partPrice) {
        this.partID = new SimpleStringProperty(partID);
        this.partName = new SimpleStringProperty(partName);
        this.partInventory = new SimpleIntegerProperty(partInventory);
        this.partPrice = new SimpleDoubleProperty(partPrice);
    }

    public String getPartID() {
        return partID.get();
    }

    public void setPartID(SimpleStringProperty partID) {
        this.partID = partID;
    }

    public String getPartName() {
        return partName.get();
    }

    public void setPartName(SimpleStringProperty partName) {
        this.partName = partName;
    }

    public Integer getPartInventory() {
        return partInventory.get();
    }

    public void setPartInventory(SimpleIntegerProperty partInventory) {
        this.partInventory = partInventory;
    }

    public Double getPartPrice() {
        return partPrice.get();
    }

    public void setPartPrice(SimpleDoubleProperty partPrice) {
        this.partPrice = partPrice;
    }
    
}
