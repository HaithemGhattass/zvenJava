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
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
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
public class MarketController implements Initializable {

    private Scene preScene;

    @FXML
    private Label fruitPriceLabel;
    @FXML
    private ImageView fruitImg;
    @FXML
    private ScrollPane scroll;
    @FXML
    private GridPane grid;
    private Image image;
    private MyListener myListener;
    private Label home;
    String string;
    @FXML
    private Label desciption;
    @FXML
    private VBox chosenproduitCard;
    @FXML
    private Label produitNameLable;
    @FXML
    private Label restaurantname;
    @FXML
    private HBox reclamations;

    /**
     * Initializes the controller class.
     */
    private void setChosenProduct(Produits produit) {
        produitNameLable.setText(produit.getNom());
        fruitPriceLabel.setText(produit.getPrix() + " dt");
        desciption.setText(produit.getDescriptionProduit());
        image = new Image(produit.getImageProduit());
        fruitImg.setImage(image);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void getProduits(Restaurant restaurant) {
        
              
             

        ArrayList<Produits> list = (ArrayList<Produits>) ServiceProduit.getInstance().afficherbyrestaurants(restaurant);
        if (list.size() > 0) {
            setChosenProduct(list.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Produits produit) {
                    setChosenProduct(produit);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (Produits p : list) {
                 
               
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(p, myListener);

                if (column == 3) {
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

    public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }
    public void setnom(String valeur) {
       
               this.restaurantname.setText(valeur);
  
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

    private void goReclamation(MouseEvent event) throws IOException {
       /* FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        ReclamationController controller = fxmlLoader.getController();
                        //controller.setPreScene(item1.getScene());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();*/

    }
    
    

}
