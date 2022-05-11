/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import zvendelivery.entites.Produits;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.MyListener;
import zvendelivery.services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class ReclamationController implements Initializable {

    @FXML
    private Label restaurantname;
    @FXML
    private VBox chosenproduitCard;
    @FXML
    private Label produitNameLable;
    @FXML
    private Label fruitPriceLabel;
    @FXML
    private Label desciption;
    @FXML
    private ImageView fruitImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox vbox;
    private Image image;
    private MyListener myListener;
    private Label home;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void getPrevious(MouseEvent event) throws IOException {
         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListRestaurant.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        ListRestaurantController controller = fxmlLoader.getController();
                        //controller.setPreScene(item1.getScene());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

    }

    @FXML
    private void goProduits(MouseEvent event) throws IOException {
         FXMLLoader shopingLoader = new FXMLLoader();
           shopingLoader.setLocation(getClass().getResource("market.fxml"));
           Parent shopingParent = shopingLoader.load();
           Scene shopingScene = new Scene(shopingParent);
           Stage shopingStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           shopingStage.setScene(shopingScene);
           shopingStage.show();

    }
     public void getProduitss(Restaurant restaurant) {
        // System.out.print("xxxxxxxxxxxxx");
        ArrayList<Produits> lists = (ArrayList<Produits>) ServiceProduit.getInstance().afficherbyrestaurants(restaurant);
       System.out.print(restaurant);
       
        for (Produits p : lists) {
           System.out.print(p);
           
            
           
        } /*  GridPane.setMargin(anchorPane, new Insets(10));
        reclamations.setOnMouseClicked((Event event) -> {
        try {
        FXMLLoader fxmlLoaders = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
        Scene scene = new Scene(fxmlLoaders.load());
        ReclamationController controller = fxmlLoaders.getController();
        controller.getProduits(restaurant);
        controller.setPreScene(reclamations.getScene());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        controller.setnom(rec.getNom());
        } catch (IOException ex) {
        System.out.println(ex.getMessage());
        }
        });*/
         

    }
}

    

