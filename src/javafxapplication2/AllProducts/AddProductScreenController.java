package javafxapplication2.AllProducts;

import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafxapplication2.Models.Utility;
import static javafxapplication2.Models.Inventory.addProduct;
import static javafxapplication2.Models.Inventory.allParts;
import javafxapplication2.Models.Parts;
import javafxapplication2.Models.Products;

public class AddProductScreenController implements Initializable {
    @FXML private TextField productNameTextField;
    @FXML private TextField productInventoryTextField;
    @FXML private TextField productPriceTextField;
    @FXML private TextField productMaxTextField;
    @FXML private TextField productMinTextField;
    @FXML private TableView addTableView;
    @FXML private TableColumn<Parts, String> addPartIdColumn;
    @FXML private TableColumn<Parts, String> addPartNameColumn;
    @FXML private TableColumn<Parts, Integer> addPartInventoryColumn;
    @FXML private TableColumn<Parts, String> addPartPriceColumn;
    @FXML private TableView deleteTableView;
    @FXML private TableColumn<Parts, String> deletePartIdColumn;
    @FXML private TableColumn<Parts, String> deletePartNameColumn;
    @FXML private TableColumn<Parts, Integer> deletePartInventoryColumn;
    @FXML private TableColumn<Parts, String> deletePartPriceColumn;
    @FXML private TextField searchTextField;
    private ObservableList<Parts> addAssociatedParts = FXCollections.observableArrayList();
    private ObservableList<Parts> deleteAssociatedParts = FXCollections.observableArrayList();
    
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
    
    public void addProductButton(ActionEvent event) throws IOException {
        if(Integer.parseInt(productMinTextField.getText()) > Integer.parseInt(productMaxTextField.getText())) 
        {
            Utility.showError("ERROR!", "The Minimum cannot be more than the maximum!");
        }
        else if(!Utility.checkDouble(productPriceTextField.getText())) {
            Utility.showError("ERROR!", "Enter a valid Price!");
        }
        else if(deleteAssociatedParts.isEmpty()) {
            Utility.showError("ERROR!", "Need at least one Associated Part!");
        }
        else 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/javafxapplication2/FXMLDocument.fxml"));
            Parent tableViewParent = loader.load();
            Scene tableViewScene = new Scene(tableViewParent);
            Products newProduct = new Products( Integer.toString(getRandomNumber()), 
                                                productNameTextField.getText(), 
                                                Integer.parseInt(productInventoryTextField.getText()), 
                                                Utility.formatPrice(productPriceTextField.getText()), 
                                                Integer.parseInt(productMaxTextField.getText()), 
                                                Integer.parseInt(productMinTextField.getText()),
                                                deleteAssociatedParts);

            addProduct(newProduct);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }
    }
    
    public void addPartButton() {
        if(addTableView.getSelectionModel().getSelectedItem() != null) {
            Parts part = (Parts) addTableView.getSelectionModel().getSelectedItem();
            deleteAssociatedParts.add(part);
            deleteTableView.setItems(deleteAssociatedParts);
            addAssociatedParts.remove(part);
        }
    }
    
    public void deletePartButton() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES) {
            if(deleteTableView.getSelectionModel().getSelectedItem() != null) {
                Parts part = (Parts) deleteTableView.getSelectionModel().getSelectedItem();
                addAssociatedParts.add(part);
                addTableView.setItems(addAssociatedParts);
                deleteAssociatedParts.remove(part);
            }
        }
    }
    
    public void searchParts() 
    {
        TableColumn<Parts, String> column = addPartIdColumn;

        ObservableList<Parts> allParts;
        allParts = addTableView.getItems();

        for(int addParts = 0; addParts < allParts.size(); addParts++) 
        {
            if(Integer.parseInt(column.getCellObservableValue(addParts).getValue()) == Integer.parseInt(searchTextField.getText())) 
            {
                addTableView.getSelectionModel().select(addParts);
            }
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        addPartIdColumn.setCellValueFactory(new PropertyValueFactory<Parts, String>("partID"));
        addPartNameColumn.setCellValueFactory(new PropertyValueFactory<Parts, String>("partName"));
        addPartInventoryColumn.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("partInventory"));
        addPartPriceColumn.setCellValueFactory(new PropertyValueFactory<Parts, String>("partPrice"));
        
        deletePartIdColumn.setCellValueFactory(new PropertyValueFactory<Parts, String>("partID"));
        deletePartNameColumn.setCellValueFactory(new PropertyValueFactory<Parts, String>("partName"));
        deletePartInventoryColumn.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("partInventory"));
        deletePartPriceColumn.setCellValueFactory(new PropertyValueFactory<Parts, String>("partPrice"));
        
        addAssociatedParts.addAll(allParts);
        addTableView.setItems(addAssociatedParts);
        
        Utility.addListener(productInventoryTextField);
        Utility.addListener(productMinTextField);
        Utility.addListener(productMaxTextField);
    }    
}
