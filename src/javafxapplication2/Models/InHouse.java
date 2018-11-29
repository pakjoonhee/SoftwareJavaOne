package javafxapplication2.Models;

import javafxapplication2.Models.Parts;

public class InHouse extends Parts {
    private Integer inHouseID;
    
    public InHouse(Integer inHouseID, String partID, String partName, Integer partInventory, String partPrice, Integer partMax, Integer partMin) {
        super(partID, partName, partInventory, partPrice, partMax, partMin);
        this.inHouseID = inHouseID;
    }

    public Integer getInHouse() {
        return inHouseID;
    }

    public void setInHouse(Integer inHouseID) {
        this.inHouseID = inHouseID;
    }
    
    
}
