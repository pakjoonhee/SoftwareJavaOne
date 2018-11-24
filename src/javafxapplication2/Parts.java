/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Parts {
    private SimpleStringProperty partID, partName;
    private SimpleIntegerProperty partInventory;
    private SimpleStringProperty partPrice;
    private SimpleIntegerProperty partMax;
    private SimpleIntegerProperty partMin;
    private SimpleStringProperty companyMachineID; 

    public Parts(String partID, String partName, Integer partInventory, String partPrice, Integer partMax, Integer partMin, String companyMachineID) {
        this.partID = new SimpleStringProperty(partID);
        this.partName = new SimpleStringProperty(partName);
        this.partInventory = new SimpleIntegerProperty(partInventory);
        this.partPrice = new SimpleStringProperty(partPrice);
        this.partMax = new SimpleIntegerProperty(partMax);
        this.partMin = new SimpleIntegerProperty(partMin);
        this.companyMachineID = new SimpleStringProperty(companyMachineID);
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

    public String getPartPrice() {
        return partPrice.get();
    }

    public void setPartPrice(SimpleStringProperty partPrice) {
        this.partPrice = partPrice;
    }

    public Integer getPartMax() {
        return partMax.get();
    }

    public void setPartMax(SimpleIntegerProperty partMax) {
        this.partMax = partMax;
    }

    public Integer getPartMin() {
        return partMin.get();
    }

    public void setPartMin(SimpleIntegerProperty partMin) {
        this.partMin = partMin;
    }

    public String getCompanyMachineID() {
        return companyMachineID.get();
    }

    public void setCompanyMachineID(SimpleStringProperty companyMachineID) {
        this.companyMachineID = companyMachineID;
    }
    
    
}
