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
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import zvendelivery.entites.Produits;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.MyListener;
import zvendelivery.services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class MyProductsController implements Initializable {

    @FXML
    private ImageView addRestaurant;
    @FXML
    public GridPane grid;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
     public void getProduits(Restaurant restaurant) {
        
              
             

        ArrayList<Produits> list = (ArrayList<Produits>) ServiceProduit.getInstance().afficherbyrestaurants(restaurant);
       
        int column = 0;
        int row = 1;
        try {
            for (Produits p : list) {
                 
               
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ProduitItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ProduitItemController itemController = fxmlLoader.getController();
                itemController.setData2(p,grid);
                System.out.print("ahhawaa  "+p.getRestaurant());

                if (column == 1) {
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
         

    }

   
    public void setnom(String valeur) {
       
            //   this.restaurantname.setText(valeur);
  
    }

    @FXML
    private void ajoutRestaurant(MouseEvent event) {
    }
    
  
    
}
