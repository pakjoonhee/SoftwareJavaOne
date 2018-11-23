package javafxapplication2;

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

public class ModifyPartScreenController implements Initializable {
    private Parts selectedProduct;
    @FXML private Label machineIDLabel;
    @FXML private RadioButton inHouseButton;
    @FXML private RadioButton outsourcedButton;
    @FXML private TextField changeTextField;
    @FXML private TextField partNameTextField;
    @FXML private TextField partInventoryTextField;
    @FXML private TextField partPriceTextField;
    @FXML private TextField maxTextField;
    @FXML private TextField minTextField;
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
        controller.getDataCallback(rowNumber, partID, partNameTextField.getText(), Integer.parseInt(partInventoryTextField.getText()), partPriceTextField.getText());
                
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void radioButtonToggled() 
    {
        if(this.sourceButtonGroup.getSelectedToggle().equals(this.inHouseButton)) {
            machineIDLabel.setText("Machine ID");
            changeTextField.setPromptText("Mach ID");
        }
        if(this.sourceButtonGroup.getSelectedToggle().equals(this.outsourcedButton)) {
            machineIDLabel.setText("Company Name");
            changeTextField.setPromptText("Comp Nm");
        }
    }
    
    public void initPartData(Parts parts, Integer rowNumber) {
         selectedProduct = parts;
         partID = selectedProduct.getPartID();
         partNameTextField.setText(selectedProduct.getPartName());
         partInventoryTextField.setText(Integer.toString(selectedProduct.getPartInventory()));
         partPriceTextField.setText(selectedProduct.getPartPrice());
         this.rowNumber = rowNumber;
     } 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sourceButtonGroup = new ToggleGroup();
        this.inHouseButton.setSelected(true);
        this.inHouseButton.setToggleGroup(sourceButtonGroup);
        this.outsourcedButton.setToggleGroup(sourceButtonGroup);
    }    
    
}
