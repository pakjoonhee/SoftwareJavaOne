package javafxapplication2.Models;

import javafxapplication2.Models.Parts;

public class Outsourced extends Parts {
    private String companyName;
    
    public Outsourced(String companyName, String partID, String partName, Integer partInventory, String partPrice, Integer partMax, Integer partMin) {
        super(partID, partName, partInventory, partPrice, partMax, partMin);
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    
}
