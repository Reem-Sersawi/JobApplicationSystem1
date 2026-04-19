// ريم سعيد السرساوي 
//220220656
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package jobapplicationsystem1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author TOP
 */
public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        primaryStage.setTitle("Job Application System");
       // primaryStage.setScene(new Scene(root, 1000, 750));
       primaryStage.setScene(new Scene(root, 1000, 750)); 
       primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
