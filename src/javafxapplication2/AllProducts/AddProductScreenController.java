package javafxapplication2.AllProducts;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafxapplication2.FXMLDocumentController;
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
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/javafxapplication2/FXMLDocument.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void addProductButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/javafxapplication2/FXMLDocument.fxml"));
        Parent tableViewParent = loader.load();
        Scene tableViewScene = new Scene(tableViewParent);
        Products newProduct = new Products(Double.toString(Math.random()), 
                                           productNameTextField.getText(), 
                                           Integer.parseInt(productInventoryTextField.getText()), 
                                           productPriceTextField.getText(), 
                                           Integer.parseInt(productMaxTextField.getText()), 
                                           Integer.parseInt(productMinTextField.getText()));

        addProduct(newProduct);

        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

        window.setScene(tableViewScene);
        window.show();
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
        if(deleteTableView.getSelectionModel().getSelectedItem() != null) {
            Parts part = (Parts) deleteTableView.getSelectionModel().getSelectedItem();
            addAssociatedParts.add(part);
            addTableView.setItems(addAssociatedParts);
            deleteAssociatedParts.remove(part);
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
    }    
    
}
