/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxapplication2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafxapplication2.Models.MachineID;
import javafxapplication2.Models.Outsourced;
import javafxapplication2.Models.Parts;

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
        Parts part = new Outsourced( "Boeing", "1", "Steal Beam", 2, "1.00", 2, 1);
        Parts part1 = new Outsourced("Microsoft", "2", "ScrewDriver", 5, "3.00", 4, 2);
        Parts part2 = new MachineID(1990, "4", "Nuts", 11, "4.00", 6, 1);
        launch(args);
    }
    
}
