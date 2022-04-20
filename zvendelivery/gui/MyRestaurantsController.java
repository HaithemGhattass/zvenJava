/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import zvendelivery.entites.Produits;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.ServiceProduit;
import zvendelivery.services.ServiceRestaurant;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class MyRestaurantsController implements Initializable {
    @FXML
    private VBox vbox;
    @FXML
    private ImageView addRestaurant;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            ArrayList<Restaurant> list = (ArrayList<Restaurant>) ServiceRestaurant.getInstance().afficher();

             for (Restaurant rec : list) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("myRestaurantItem.fxml"));
                HBox anchorPane = fxmlLoader.load();

                MyRestaurantItemController ic = fxmlLoader.getController();
                ic.setData(rec,vbox);
                vbox.getChildren().add(anchorPane);
               
              //  vbox.getChildren().remove(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(MyRestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            }


        }       

    }
   public void setnom(Node node) {
       
               this.vbox.getChildren().remove(node);
  
    }

    @FXML
    private void ajoutRestaurant(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutRestaurant.fxml"));
            Parent root2 = loader.load();
            addRestaurant.getScene().setRoot(root2);

    }

    @FXML
    private void home(MouseEvent event) throws IOException {
          FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListRestaurant.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        ListRestaurantController controller = fxmlLoader.getController();
                        //controller.setPreScene(item1.getScene());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
    }
}
