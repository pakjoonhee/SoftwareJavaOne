package javafxapplication2.Models;

import javafxapplication2.Models.Parts;

public class MachineID extends Parts {
    private Integer machineID;
    
    public MachineID(Integer machineID, String partID, String partName, Integer partInventory, String partPrice, Integer partMax, Integer partMin) {
        super(partID, partName, partInventory, partPrice, partMax, partMin);
        this.machineID = machineID;
    }

    public Integer getMachineID() {
        return machineID;
    }

    public void setMachineID(Integer machineID) {
        this.machineID = machineID;
    }
    
    
}
