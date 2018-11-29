/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import javafxapplication2.AllParts.ModifyPartScreenController;
import javafxapplication2.Models.Parts;
import javafxapplication2.Models.Products;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafxapplication2.AllProducts.ModifyProductScreenController;
import static javafxapplication2.Models.Inventory.allParts;
import static javafxapplication2.Models.Inventory.allProducts;


public class FXMLDocumentController implements Initializable {
    
    @FXML private Button exitButton;
    @FXML private TableView<Parts> partTableView;
    @FXML private TableColumn<Parts, String> partIdColumn;
    @FXML private TableColumn<Parts, String> partNameColumn;
    @FXML private TableColumn<Parts, Integer> partInventoryColumn;
    @FXML private TableColumn<Parts, String> partPriceColumn;
    
    @FXML private TableView<Products> productTableView;
    @FXML private TableColumn<Products, String> productIdColumn;
    @FXML private TableColumn<Products, String> productNameColumn;
    @FXML private TableColumn<Products, Integer> productInventoryColumn;
    @FXML private TableColumn<Products, String> productPriceColumn;
    
    @FXML private TextField partSearchTextField;
    @FXML private TextField productSearchTextField;
    
    @FXML
     private void exitButtonAction(){
         Stage stage = (Stage) exitButton.getScene().getWindow();
         stage.close();
     }
    
    public void searchParts() 
    {
        TableColumn<Parts, String> column = partIdColumn;
    
        ObservableList<Parts> allParts;
        allParts = partTableView.getItems();

        for(int parts = 0; parts < allParts.size(); parts++) 
        {
            if(Integer.parseInt(column.getCellObservableValue(parts).getValue()) == Integer.parseInt(partSearchTextField.getText())) 
            {
                partTableView.getSelectionModel().select(parts);
            }
        }
    }
    
    public void searchProducts() 
    {
        TableColumn<Products, String> column = productIdColumn;
    
        ObservableList<Products> allProducts;
        allProducts = productTableView.getItems();

        for(int products = 0; products < allProducts.size(); products++) 
        {
            if(Integer.parseInt(column.getCellObservableValue(products).getValue()) == Integer.parseInt(productSearchTextField.getText())) 
            {
                productTableView.getSelectionModel().select(products);
            }
        }
    }
     
    public void changeScreenAddPart(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/javafxapplication2/AllParts/AddPartScreen.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void changeScreenModifyPart(ActionEvent event) throws IOException 
    {
        if(partTableView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/javafxapplication2/AllParts/ModifyPartScreen.fxml"));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            ModifyPartScreenController controller = loader.getController();
            controller.initPartData(partTableView.getSelectionModel().getSelectedItem(), 
                    partTableView.getSelectionModel().selectedIndexProperty().get());

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }
    }
    
    public void deletePart() 
    {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();
        
        if (alert.getResult() == ButtonType.YES) {
            ObservableList<Parts> selectedRows, allParts;
            allParts = partTableView.getItems();

            selectedRows = partTableView.getSelectionModel().getSelectedItems();

            for (Parts parts: selectedRows)
            {
                allParts.remove(parts);
            }
        }
    }
    
    public void changeScreenAddProduct(ActionEvent event) throws IOException 
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("/javafxapplication2/AllProducts/AddProductScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void changeScreenModifyProduct(ActionEvent event) throws IOException 
    {
        
        if(productTableView.getSelectionModel().getSelectedItem() != null) {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/javafxapplication2/AllProducts/ModifyProductScreen.fxml"));
            Parent tableViewParent = loader.load();

            Scene tableViewScene = new Scene(tableViewParent);

            ModifyProductScreenController controller = loader.getController();
            controller.initProductData(productTableView.getSelectionModel().getSelectedItem(), 
                                       productTableView.getSelectionModel().selectedIndexProperty().get());

            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();

            window.setScene(tableViewScene);
            window.show();
        }
    }
    
    public void deleteProduct() 
    {
        Alert alert = new Alert(AlertType.CONFIRMATION, "Are you sure you want to delete?", ButtonType.YES, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            ObservableList<Products> selectedRows, allProducts;
            allProducts = productTableView.getItems();

            selectedRows = productTableView.getSelectionModel().getSelectedItems();

            for(Products products: selectedRows)
            {
                allProducts.remove(products);
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        partIdColumn.setCellValueFactory(new PropertyValueFactory<Parts, String>("partID"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<Parts, String>("partName"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<Parts, Integer>("partInventory"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<Parts, String>("partPrice"));
        
        productIdColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("productID"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("productName"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<Products, Integer>("productInventory"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<Products, String>("productPrice"));
        
        partTableView.setItems(allParts);
        productTableView.setItems(allProducts);
    }    
    
}
