/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


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
    
    @FXML
     private void exitButtonAction(){
         Stage stage = (Stage) exitButton.getScene().getWindow();
         stage.close();
     }
    
     
    public void searchParts() 
    {
        TableColumn<Parts, String> column = partNameColumn;
    
        ObservableList<Parts> allParts;
        allParts = partTableView.getItems();

        List<String> columnData = new ArrayList<>();
        for (Parts parts : allParts) 
        {
            columnData.add(column.getCellObservableValue(parts).getValue());
        }
        
        System.out.println(columnData);
    }
     
    public void changeScreenAddPart(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddPartScreen.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void changeScreenModifyPart(ActionEvent event) throws IOException 
    {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("ModifyPartScreen.fxml"));
        Parent tableViewParent = loader.load();
        
        Scene tableViewScene = new Scene(tableViewParent);
        
        ModifyPartScreenController controller = loader.getController();
        controller.initPartData(partTableView.getSelectionModel().getSelectedItem(), 
                partTableView.getSelectionModel().selectedIndexProperty().get());
                        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void deletePart() 
    {
        ObservableList<Parts> selectedRows, allParts;
        allParts = partTableView.getItems();
        
        selectedRows = partTableView.getSelectionModel().getSelectedItems();
        
        for (Parts parts: selectedRows)
        {
            allParts.remove(parts);
        }
    }
    
    public void changeScreenAddProduct(ActionEvent event) throws IOException 
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("AddProductScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void changeScreenModifyProduct(ActionEvent event) throws IOException 
    {
        Parent tableViewParent = FXMLLoader.load(getClass().getResource("ModifyProductScreen.fxml"));
        Scene tableViewScene = new Scene(tableViewParent);
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        
        window.setScene(tableViewScene);
        window.show();
    }
    
    public void getDataCallback(Integer rowNumber, String partID, String partName, Integer partInventory, 
                                String partPrice, Integer partMax, Integer partMin, String partcompanyMachineID) {
         partTableView.getItems().set(rowNumber, new Parts(partID, partName, partInventory, partPrice, partMax, partMin, partcompanyMachineID));
     } 
    
    public void addDataCallback(String partID, String partName, Integer partInventory, 
                                String partPrice, Integer partMax, Integer partMin, String partcompanyMachineID) {
         partTableView.getItems().add(new Parts(partID, partName, partInventory, partPrice, partMax, partMin, partcompanyMachineID));
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
        
        partTableView.setItems(getParts());
        productTableView.setItems(getProducts());
    }    
    
    public ObservableList<Parts> getParts() 
    {
       ObservableList<Parts> parts = FXCollections.observableArrayList();
       parts.add(new Parts("A1", "Steal Beam", 2, "1.00", 2, 1, "Boeing"));
       parts.add(new Parts("A2", "ScrewDriver", 5, "3.00", 4, 2, "Microsoft"));
       parts.add(new Parts("A4", "Nuts", 11, "4.00", 6, 1, "Nintendo"));
       return parts;
    }
    
    public ObservableList<Products> getProducts()
    {
        ObservableList<Products> products = FXCollections.observableArrayList();
        products.add(new Products("B1", "Diapers", 10, "1.01", 2, 1, "Chevron"));
        products.add(new Products("B2", "Towels", 2, "2.02", 3, 2, "Texaco"));
        products.add(new Products("B3", "Napkins", 8, "3.03", 10, 2, "Yay"));
        products.add(new Products("B4", "Soap", 12, "4.04", 9, 5, "Wonderful"));
        return products;
    }
}
