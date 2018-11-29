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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafxapplication2.Models.Utility;
import static javafxapplication2.Models.Inventory.allParts;
import static javafxapplication2.Models.Inventory.updateProduct;
import javafxapplication2.Models.Parts;
import javafxapplication2.Models.Products;

public class ModifyProductScreenController implements Initializable {
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
    private ObservableList<Parts> associatedParts = FXCollections.observableArrayList();
    
    private String productID;
    private Integer rowNumber;
    
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
    
    public void submitButtonGoBack(ActionEvent event) throws IOException 
    {
        if(Integer.parseInt(productMinTextField.getText()) > Integer.parseInt(productMaxTextField.getText())) 
        {
            Utility.minTooHigh("ERROR!", "The Minimum cannot be more than the maximum!");
        }
        else if(!Utility.checkDouble(productPriceTextField.getText())) {
            Utility.minTooHigh("ERROR!", "Enter a valid Price!");
        }
        else 
        {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/javafxapplication2/FXMLDocument.fxml"));
            Parent tableViewParent = loader.load();
            Scene tableViewScene = new Scene(tableViewParent);

            Products updateProduct = new Products(productID, productNameTextField.getText(), 
                                                  Integer.parseInt(productInventoryTextField.getText()), 
                                                  productPriceTextField.getText(), 
                                                  Integer.parseInt(productMaxTextField.getText()), 
                                                  Integer.parseInt(productMinTextField.getText()), associatedParts);

            updateProduct(rowNumber, updateProduct);

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }
    }
    
    public void initProductData(Products product, Integer rowNumber) {
        addAssociatedParts.addAll(allParts);   
        associatedParts = product.getParts();
        
        for(int i = 0; i < associatedParts.size(); i++) {
            for(int j = 0; j < addAssociatedParts.size(); j++) {
                if(associatedParts.get(i).getPartID() == addAssociatedParts.get(j).getPartID()) {
                    addAssociatedParts.remove(j);
                }
            }
        }
        
        addTableView.setItems(addAssociatedParts);
        deleteTableView.setItems(associatedParts);
        
        productID = product.getProductID();
        productNameTextField.setText(product.getProductName());
        productInventoryTextField.setText(Integer.toString(product.getProductInventory()));
        productPriceTextField.setText(product.getProductPrice());
        productMaxTextField.setText(Integer.toString(product.getProductMax()));
        productMinTextField.setText(Integer.toString(product.getProductMin()));
        this.rowNumber = rowNumber;
    } 
    
    public void addPartButton() {
        if(addTableView.getSelectionModel().getSelectedItem() != null) {
            Parts part = (Parts) addTableView.getSelectionModel().getSelectedItem();
            associatedParts.add(part);
            deleteTableView.setItems(associatedParts);
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
                associatedParts.remove(part);
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
        
        Utility.addListener(productInventoryTextField);
        Utility.addListener(productMinTextField);
        Utility.addListener(productMaxTextField);
    }    
    
}