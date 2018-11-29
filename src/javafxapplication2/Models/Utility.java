package javafxapplication2.Models;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class Utility {
    
    public static void addListener(TextField textField) {
        textField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    textField.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });
    }
    
    public static void showError(String title, String message) {
        Stage window = new Stage();
        
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(250);
        window.setWidth(500);
        
        Label label = new Label();
        label.setText(message);
        Button closeButton = new Button("Okay");
        closeButton.setOnAction(e -> window.close());
        
        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);
        
        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }
    
    public static boolean checkDouble(String string) {
        boolean numeric = true;

        try {
            Double num = Double.parseDouble(string);
        } catch (NumberFormatException e) {
            numeric = false;
        }
        
        return numeric;
    }
    
    public static boolean checkMinHigher(String min, String max) {
        boolean higher = true;

        if(Integer.parseInt(min) > Integer.parseInt(max)) {
            higher = false;
        }
        
        return higher;
    }
    
    public static String formatPrice(String string) {
        Double formatDouble = Double.parseDouble(string);
        DecimalFormat df = new DecimalFormat("#.00"); 
        
        return df.format(formatDouble);
    }
}
