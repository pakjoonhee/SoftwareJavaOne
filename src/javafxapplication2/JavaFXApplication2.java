package javafxapplication2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafxapplication2.Models.Inventory.allParts;
import javafxapplication2.Models.InHouse;
import static javafxapplication2.Models.Inventory.allProducts;
import javafxapplication2.Models.Outsourced;
import javafxapplication2.Models.Parts;
import javafxapplication2.Models.Products;

public class JavaFXApplication2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        allParts = FXCollections.observableArrayList();
        allParts.add(new Outsourced( "Boeing", "1", "Steal Beam", 2, "1.50", 2, 1));
        allParts.add(new Outsourced("Microsoft", "2", "ScrewDriver", 5, "3.20", 4, 2));
        allParts.add(new InHouse(1990, "1990", "Nuts", 11, "4.30", 6, 1));
        
        allProducts = FXCollections.observableArrayList();
        ObservableList<Parts> associatedParts = FXCollections.observableArrayList();
        associatedParts.add(new Outsourced( "Boeing", "1", "Steal Beam", 2, "1.00", 2, 1));
                
        allProducts.add(new Products("1", "Diapers", 10, "1.05", 2, 1, associatedParts));
        allProducts.add(new Products("2", "Towels", 2, "2.99", 3, 2, associatedParts));
        allProducts.add(new Products("3", "Napkins", 8, "3.40", 10, 2, associatedParts));
        allProducts.add(new Products("4", "Soap", 12, "4.50", 9, 5, associatedParts));
        
        launch(args);
    }
}
