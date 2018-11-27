package javafxapplication2.AllParts;

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


public class AddPartScreenController implements Initializable {
    @FXML private Label machineIDLabel;
    @FXML private RadioButton inHouseButton;
    @FXML private RadioButton outsourcedButton;
    @FXML private TextField partIdTextField;
    @FXML private TextField partNameTextField;
    @FXML private TextField partInventoryTextField;
    @FXML private TextField partPriceTextField;
    @FXML private TextField partMaxTextField;
    @FXML private TextField partMinTextField;
    @FXML private TextField companyOrID;
    private ToggleGroup sourceButtonGroup;
    private String whichToggle;
    
    public void changeScreenGoBack(ActionEvent event) throws IOException 
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void radioButtonToggled() 
    {
        if(this.sourceButtonGroup.getSelectedToggle().equals(this.inHouseButton)) {
            machineIDLabel.setText("Machine ID");
            companyOrID.setPromptText("Mach ID");
            whichToggle = "Machine";
        }
        if(this.sourceButtonGroup.getSelectedToggle().equals(this.outsourcedButton)) {
            machineIDLabel.setText("Company Name");
            companyOrID.setPromptText("Comp Nm");
            whichToggle = "Company";
        }
    }
    
    public void addPartButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        FXMLDocumentController controller = loader.getController();
        
        if(this.sourceButtonGroup.getSelectedToggle().equals(this.outsourcedButton)) 
        {
            controller.addDataCompanyName(companyOrID.getText(), 
                                      Double.toString(Math.random()), 
                                      partNameTextField.getText(), 
                                      Integer.parseInt(partInventoryTextField.getText()), 
                                      partPriceTextField.getText(), 
                                      Integer.parseInt(partMaxTextField.getText()), 
                                      Integer.parseInt(partMinTextField.getText()));
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
            window.setScene(tableViewScene);
            window.show();
        } else if(this.sourceButtonGroup.getSelectedToggle().equals(this.inHouseButton))
        {
            controller.addDataMachineID(Integer.parseInt(companyOrID.getText()), 
                                        Double.toString(Math.random()), 
                                        partNameTextField.getText(), 
                                        Integer.parseInt(partInventoryTextField.getText()), 
                                        partPriceTextField.getText(), 
                                        Integer.parseInt(partMaxTextField.getText()), 
                                        Integer.parseInt(partMinTextField.getText()));
            
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
            window.setScene(tableViewScene);
            window.show();
        }
         
     }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sourceButtonGroup = new ToggleGroup();
        this.inHouseButton.setSelected(true);
        this.inHouseButton.setToggleGroup(sourceButtonGroup);
        this.outsourcedButton.setToggleGroup(sourceButtonGroup);
    }    
    
}
