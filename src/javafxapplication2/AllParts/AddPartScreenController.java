package javafxapplication2.AllParts;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafxapplication2.FXMLDocumentController;
import javafxapplication2.Models.Utility;
import static javafxapplication2.Models.Inventory.addCompanyPart;
import javafxapplication2.Models.InHouse;
import static javafxapplication2.Models.Inventory.allParts;
import javafxapplication2.Models.Outsourced;

public class AddPartScreenController implements Initializable {
    @FXML private Label inHouseIDLabel;
    @FXML private RadioButton inHouseButton;
    @FXML private RadioButton outSourcedButton;
    @FXML private TextField partNameTextField;
    @FXML private TextField partInventoryTextField;
    @FXML private TextField partPriceTextField;
    @FXML private TextField partMaxTextField;
    @FXML private TextField partMinTextField;
    @FXML private TextField companyOrID;
    private ToggleGroup sourceButtonGroup;
    
   

    public void changeScreenGoBack(ActionEvent event) throws IOException 
    {
        
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to go back?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES) {
            Parent tableViewParent = FXMLLoader.load(getClass().getResource("/javafxapplication2/FXMLDocument.fxml"));
            Scene tableViewScene = new Scene(tableViewParent);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
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
    
    public Integer getRandomNumber() {
        Random generator=new Random();
            int randomNum = generator.nextInt(100);
            for(int i = 0; i < allParts.size(); i++) {
                if(allParts.get(i).getPartID() == Integer.toString(randomNum)) {
                    randomNum = generator.nextInt(1000);
                }
            }
        return randomNum;
    }
    
    public void addPartButton(ActionEvent event) throws IOException {
        
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

            FXMLDocumentController controller = loader.getController();

            if(this.sourceButtonGroup.getSelectedToggle().equals(this.outSourcedButton)) 
            {
                Random generator=new Random();
                int randomNum = generator.nextInt(100);
                for(int i = 0; i < allParts.size(); i++) {
                    if(allParts.get(i).getPartID() == Integer.toString(randomNum)) {
                        randomNum = generator.nextInt(1000);
                    }
                }

                Outsourced newPart = new Outsourced(companyOrID.getText(), 
                                                    Integer.toString(getRandomNumber()), 
                                                    partNameTextField.getText(), 
                                                    Integer.parseInt(partInventoryTextField.getText()), 
                                                    partPriceTextField.getText(), 
                                                    Integer.parseInt(partMaxTextField.getText()), 
                                                    Integer.parseInt(partMinTextField.getText()));

                addCompanyPart(newPart);

                Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                window.setScene(tableViewScene);
                window.show();
            } else if(this.sourceButtonGroup.getSelectedToggle().equals(this.inHouseButton))
            {
                if(companyOrID.getText().matches("-?\\d+")) {
                    InHouse newPart = new InHouse(Integer.parseInt(companyOrID.getText()), 
                                              Integer.toString(getRandomNumber()), 
                                              partNameTextField.getText(), 
                                              Integer.parseInt(partInventoryTextField.getText()), 
                                              partPriceTextField.getText(), 
                                              Integer.parseInt(partMaxTextField.getText()), 
                                              Integer.parseInt(partMinTextField.getText()));

                    addCompanyPart(newPart);

                    Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

                    window.setScene(tableViewScene);
                    window.show();
                } else {
                    Utility.minTooHigh("ERROR!", "Enter a number for MachineID!");
                }
            }
        }
     }
     
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sourceButtonGroup = new ToggleGroup();
        this.inHouseButton.setSelected(true);
        this.inHouseButton.setToggleGroup(sourceButtonGroup);
        this.outSourcedButton.setToggleGroup(sourceButtonGroup);
        
        Utility.addListener(partPriceTextField);
        Utility.addListener(partInventoryTextField);
        Utility.addListener(partMinTextField);
        Utility.addListener(partMaxTextField);
    }    
}
