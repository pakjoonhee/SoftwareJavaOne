package javafxapplication2.AllParts;

import javafxapplication2.Models.MachineID;
import javafxapplication2.Models.Outsourced;
import javafxapplication2.Models.Parts;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafxapplication2.FXMLDocumentController;

public class ModifyPartScreenController implements Initializable {
    @FXML private Label machineIDLabel;
    @FXML private RadioButton inHouseButton;
    @FXML private RadioButton outsourcedButton;
    @FXML private TextField partNameTextField;
    @FXML private TextField partInventoryTextField;
    @FXML private TextField partPriceTextField;
    @FXML private TextField partMaxTextField;
    @FXML private TextField partMinTextField;
    @FXML private TextField companyOrID;
    private Parts parts;
    private String partID;
    private Integer rowNumber;
    private ToggleGroup sourceButtonGroup;

    
    public void cancelButtonGoBack(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
                
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void submitButtonGoBack(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        FXMLDocumentController controller = loader.getController();
        
        if(parts instanceof MachineID) 
        {
            controller.modifyDataMachineID(Integer.parseInt(companyOrID.getText()), rowNumber, partID, partNameTextField.getText(), 
                                           Integer.parseInt(partInventoryTextField.getText()), 
                                           partPriceTextField.getText(), Integer.parseInt(partMaxTextField.getText()), 
                                           Integer.parseInt(partMinTextField.getText()), companyOrID.getText());
        }
        
        if(parts instanceof Outsourced) 
        {
            controller.modifyDataCompanyName(companyOrID.getText(), rowNumber, partID, partNameTextField.getText(), 
                                             Integer.parseInt(partInventoryTextField.getText()), 
                                             partPriceTextField.getText(), Integer.parseInt(partMaxTextField.getText()), 
                                             Integer.parseInt(partMinTextField.getText()), companyOrID.getText());
        }
        
                
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void radioButtonToggled() 
    {
        if(this.sourceButtonGroup.getSelectedToggle().equals(this.inHouseButton)) {
            machineIDLabel.setText("Machine ID");
            companyOrID.setPromptText("Mach ID");
        }
        if(this.sourceButtonGroup.getSelectedToggle().equals(this.outsourcedButton)) {
            machineIDLabel.setText("Company Name");
            companyOrID.setPromptText("Comp Nm");
        }
    }
    
    public void initPartData(Parts parts, Integer rowNumber) {
        this.parts = parts;
        if(parts instanceof MachineID) 
        {
            this.inHouseButton.setSelected(true);
            machineIDLabel.setText("Machine ID");
            companyOrID.setPromptText("Mach ID");

            MachineID machineIDPart = (MachineID)parts;
            partID = machineIDPart.getPartID();
            partNameTextField.setText(machineIDPart.getPartName());
            partInventoryTextField.setText(Integer.toString(machineIDPart.getPartInventory()));
            partPriceTextField.setText(machineIDPart.getPartPrice());
            partMaxTextField.setText(Integer.toString(machineIDPart.getPartMax()));
            partMinTextField.setText(Integer.toString(machineIDPart.getPartMin()));
            companyOrID.setText(Integer.toString(machineIDPart.getMachineID()));
        }
        
        if(parts instanceof Outsourced) 
        {
            this.outsourcedButton.setSelected(true);
            machineIDLabel.setText("Company Name");
            companyOrID.setPromptText("Comp Nm");
            
            Outsourced outsourcedPart = (Outsourced)parts;
            partID = outsourcedPart.getPartID();
            partNameTextField.setText(outsourcedPart.getPartName());
            partInventoryTextField.setText(Integer.toString(outsourcedPart.getPartInventory()));
            partPriceTextField.setText(outsourcedPart.getPartPrice());
            partMaxTextField.setText(Integer.toString(outsourcedPart.getPartMax()));
            partMinTextField.setText(Integer.toString(outsourcedPart.getPartMin()));
            companyOrID.setText(outsourcedPart.getCompanyName());
        }
        
        this.rowNumber = rowNumber;
     } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sourceButtonGroup = new ToggleGroup();
        this.inHouseButton.setToggleGroup(sourceButtonGroup);
        this.outsourcedButton.setToggleGroup(sourceButtonGroup);
    }    
    
}