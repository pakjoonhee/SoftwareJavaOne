package javafxapplication2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import static javafxapplication2.Models.Inventory.allParts;
import javafxapplication2.Models.MachineID;
import javafxapplication2.Models.Outsourced;

/**
 *
 * @author pakjo
 */
public class JavaFXApplication2 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        allParts = FXCollections.observableArrayList();
        allParts.add(new Outsourced( "Boeing", "1", "Steal Beam", 2, "1.00", 2, 1));
        allParts.add(new Outsourced("Microsoft", "2", "ScrewDriver", 5, "3.00", 4, 2));
        allParts.add(new MachineID(1990, "4", "Nuts", 11, "4.00", 6, 1));
        
        
        launch(args);
    }
    
}
