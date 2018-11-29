package javafxapplication2.AllParts;

import javafxapplication2.Models.InHouse;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import javafxapplication2.Models.Utility;
import static javafxapplication2.Models.Inventory.updatePart;

public class ModifyPartScreenController implements Initializable {
    @FXML private Label inHouseIDLabel;
    @FXML private RadioButton inHouseButton;
    @FXML private RadioButton outSourcedButton;
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to go back?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/javafxapplication2/FXMLDocument.fxml"));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }
    }
    
    public void submitButtonGoBack(ActionEvent event) throws IOException 
    {
        if(Integer.parseInt(partMinTextField.getText()) > Integer.parseInt(partMaxTextField.getText())) 
        {
            Utility.minTooHigh("ERROR!", "The Minimum cannot be more than the maximum!");
        }
        else 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/javafxapplication2/FXMLDocument.fxml"));
            Parent tableViewParent = loader.load();
            Scene tableViewScene = new Scene(tableViewParent);

            if(parts instanceof InHouse && this.sourceButtonGroup.getSelectedToggle().equals(this.inHouseButton)) 
            {
                if(companyOrID.getText().matches("-?\\d+")) {
                    InHouse updatePart = new InHouse(Integer.parseInt(companyOrID.getText()), partID, partNameTextField.getText(), 
                                                 Integer.parseInt(partInventoryTextField.getText()), 
                                                 partPriceTextField.getText(), 
                                                 Integer.parseInt(partMaxTextField.getText()), 
                                                 Integer.parseInt(partMinTextField.getText()));

                    updatePart(rowNumber, updatePart);
                    
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(tableViewScene);
                    window.show();
                } else {
                    Utility.minTooHigh("Error!", "Enter a Number for MachineID!");
                }
                
            }
            
            if(parts instanceof InHouse && this.sourceButtonGroup.getSelectedToggle().equals(this.outSourcedButton)) 
            {
                Outsourced updatePart = new Outsourced(companyOrID.getText(), partID, partNameTextField.getText(), 
                                                       Integer.parseInt(partInventoryTextField.getText()), 
                                                       partPriceTextField.getText(), 
                                                       Integer.parseInt(partMaxTextField.getText()), 
                                                       Integer.parseInt(partMinTextField.getText()));

                updatePart(rowNumber, updatePart);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();
            }

            if(parts instanceof Outsourced && this.sourceButtonGroup.getSelectedToggle().equals(this.outSourcedButton)) 
            {
                Outsourced updatePart = new Outsourced(companyOrID.getText(), partID, partNameTextField.getText(), 
                                                       Integer.parseInt(partInventoryTextField.getText()), 
                                                       partPriceTextField.getText(), 
                                                       Integer.parseInt(partMaxTextField.getText()), 
                                                       Integer.parseInt(partMinTextField.getText()));

                updatePart(rowNumber, updatePart);
                
                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();
            }
            
            if(parts instanceof Outsourced && this.sourceButtonGroup.getSelectedToggle().equals(this.inHouseButton)) 
            {
                if(companyOrID.getText().matches("-?\\d+")) {
                    InHouse updatePart = new InHouse(Integer.parseInt(companyOrID.getText()), partID, partNameTextField.getText(), 
                                                 Integer.parseInt(partInventoryTextField.getText()), 
                                                 partPriceTextField.getText(), 
                                                 Integer.parseInt(partMaxTextField.getText()), 
                                                 Integer.parseInt(partMinTextField.getText()));

                    updatePart(rowNumber, updatePart);
                    
                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(tableViewScene);
                    window.show();
                } else {
                    Utility.minTooHigh("Error!", "Enter a Number for MachineID!");
                }
            }

        }
    }
    
    public void radioButtonToggled() 
    {
        if(this.sourceButtonGroup.getSelectedToggle().equals(this.inHouseButton)) {
            inHouseIDLabel.setText("Machine ID");
            companyOrID.setPromptText("Mach ID");
        }
        if(this.sourceButtonGroup.getSelectedToggle().equals(this.outSourcedButton)) {
            inHouseIDLabel.setText("Company Name");
            companyOrID.setPromptText("Comp Nm");
        }
    }
    
    public void initPartData(Parts parts, Integer rowNumber) {
        this.parts = parts;
        if(parts instanceof InHouse) 
        {
            this.inHouseButton.setSelected(true);
            inHouseIDLabel.setText("Machine ID");
            companyOrID.setPromptText("Mach ID");

            InHouse inHouseIDPart = (InHouse)parts;
            partID = inHouseIDPart.getPartID();
            partNameTextField.setText(inHouseIDPart.getPartName());
            partInventoryTextField.setText(Integer.toString(inHouseIDPart.getPartInventory()));
            partPriceTextField.setText(inHouseIDPart.getPartPrice());
            partMaxTextField.setText(Integer.toString(inHouseIDPart.getPartMax()));
            partMinTextField.setText(Integer.toString(inHouseIDPart.getPartMin()));
            companyOrID.setText(Integer.toString(inHouseIDPart.getInHouse()));
            
        }
        
        if(parts instanceof Outsourced) 
        {
            this.outSourcedButton.setSelected(true);
            inHouseIDLabel.setText("Company Name");
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
        this.outSourcedButton.setToggleGroup(sourceButtonGroup);
        
        Utility.addListener(partPriceTextField);
        Utility.addListener(partInventoryTextField);
        Utility.addListener(partMinTextField);
        Utility.addListener(partMaxTextField);
    }    
    
}
