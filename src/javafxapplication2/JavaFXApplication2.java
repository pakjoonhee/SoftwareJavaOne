package javafxapplication2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafxapplication2.Models.Inventory.allParts;
import javafxapplication2.Models.InHouse;
import static javafxapplication2.Models.Inventory.allProducts;
import javafxapplication2.Models.Outsourced;
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
        allParts.add(new Outsourced( "Boeing", "1", "Steal Beam", 2, "1.00", 2, 1));
        allParts.add(new Outsourced("Microsoft", "2", "ScrewDriver", 5, "3.00", 4, 2));
        allParts.add(new InHouse(1990, "1990", "Nuts", 11, "4.00", 6, 1));
        
        allProducts = FXCollections.observableArrayList();
        allProducts.add(new Products("1", "Diapers", 10, "1.01", 2, 1));
        allProducts.add(new Products("2", "Towels", 2, "2.02", 3, 2));
        allProducts.add(new Products("3", "Napkins", 8, "3.03", 10, 2));
        allProducts.add(new Products("4", "Soap", 12, "4.04", 9, 5));
        
        launch(args);
    }
}
