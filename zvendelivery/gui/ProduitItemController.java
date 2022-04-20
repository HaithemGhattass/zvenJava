/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import zvendelivery.entites.Produits;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.ServiceProduit;
import zvendelivery.services.ServiceRestaurant;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class ProduitItemController implements Initializable {

    @FXML
    private ImageView edit;
    @FXML
    private ImageView delete;
    Restaurant restaurant;
    @FXML
    private AnchorPane acnhorpane;
    @FXML
    private ImageView imageP;
    @FXML
    private Label name;
    @FXML
    private Label price;
    @FXML
    private Text description;
    @FXML
    private Label date;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void click(MouseEvent event) {
    }

    public void setData2(Produits produits, GridPane gridPane) {
        /*  this.produits = produits;
       // this.myListener = myListener;
        nameLabel.setText(produits.getNom());
        priceLable.setText(produits.getPrix()+" dt");
        Image image = new Image(produits.getImageProduit());
        img.setImage(image);*/
        price.setText(produits.getPrix() + "");
        name.setText(produits.getNom());
        description.setText(produits.getDescriptionProduit());
        date.setText(produits.getCreatedAt().substring(0, 11));
        Image image = new Image(produits.getImageProduit());
        imageP.setImage(image);

        edit.setOnMouseClicked((Event event) -> {
            try {
                // System.out.print(restaurant.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierProduit.fxml"));
                Parent root2 = loader.load();
                ModifierProduitController pc = loader.getController();
                //System.out.print(produits.getRestaurant());
//                pc.setRestaurant(produits.getRestaurant());
                pc.setnom(produits.getNom());
                pc.setdesc(produits.getDescriptionProduit());
                pc.setprix(produits.getPrix() + "");
                pc.setimage("C:\\wamp64\\www\\WeDev_Zvenn\\public\\uploads\\" + produits.getNomImage());
                pc.getres(produits.getRestaurant());

                //  pc.setid(restaurant.getId());
                pc.getresid(Integer.toString(produits.getId()));

                edit.getScene().setRoot(root2);
            } catch (IOException ex) {
                Logger.getLogger(MyRestaurantItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        delete.setOnMouseClicked((Event event) -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText(null);
            alert.setContentText("do you want to delete partnership of with zvenn?");
            Optional<ButtonType> action = alert.showAndWait();
            if (action.get() == ButtonType.OK) {
                new ServiceProduit().supprimer(produits.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("myProducts.fxml"));
                MyProductsController pc = loader.getController();
                gridPane.getChildren().remove(acnhorpane);

            }
        });
    }

}
