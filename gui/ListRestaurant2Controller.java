/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import zvendelivery.entites.Produits;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.MyListener;
import zvendelivery.services.ServiceProduit;
import zvendelivery.services.ServiceRestaurant;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class ListRestaurant2Controller implements Initializable {

    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private MyListener myListener;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         ArrayList<Restaurant> list = (ArrayList<Restaurant>) ServiceRestaurant.getInstance().afficher();
     
         int column = 0;
        int row = 1;
        try {
            for (Restaurant p : list) {
                                System.out.print(p);

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("DetailRestaurant.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                DetailRestaurantController DetailController = fxmlLoader.getController();
                DetailController.setData(p);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // TODO
    }    
    
}
    

