/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zvendelivery.gui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author karra
 */
public class HomePage extends Application {
    private double x,y;
    @Override
    public void start(Stage primaryStage) {
      
        try {
                Parent root = FXMLLoader.load(getClass().getResource("ListRestaurant.fxml"));
            
            Scene scene = new Scene(root,1315,855);
            
            primaryStage.setTitle("Food Delivery");
          //  primaryStage.initStyle(StageStyle.UNDECORATED);
          primaryStage.initStyle(StageStyle.DECORATED);
            primaryStage.setScene(scene);
            primaryStage.show();
          /*  root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
            primaryStage.setOpacity(0.75);

            
        });
            root.setOnMouseReleased(event -> {
            primaryStage.setOpacity(1.0);

          


        });
            
        root.setOnMouseDragged(event -> {

            primaryStage.setX(event.getScreenX() - x);
            
            primaryStage.setY(event.getScreenY() - y);
            

        });*/
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
