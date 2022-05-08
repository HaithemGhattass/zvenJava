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
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;
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
public class ListRestaurantController implements Initializable {

    ImageView imgv;

    @FXML
    private VBox dealVbox;
    private Label nomrest;
    private VBox item1;
    @FXML
    private HBox hbox;
    private ImageView image;
    private Label gui_description;
    @FXML
    private Label gui_chicken;
    @FXML
    private Button detailRestaurant;
    @FXML
    private HBox myrestaurants;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        ArrayList<Restaurant> list = (ArrayList<Restaurant>) ServiceRestaurant.getInstance().afficher();
        int chicken = 0;
         myrestaurants.setOnMouseClicked((Event event) -> {

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myRestaurants.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        MyRestaurantsController controller = fxmlLoader.getController();
                       
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                        

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                });

        for (Restaurant rec : list) {
            if (rec.getCategorieRestaurant().equals("chicken") && rec.getStatus().equals("verified")) {
                chicken += 1;
            }
            gui_chicken.setText("" + chicken + " Restaurants");
            if (rec.getStatus().equals("verified")) {
                item1 = new VBox();
                hbox.getChildren().add(item1);
                item1.setPrefSize(223, 200);
                image = new ImageView(rec.getImageRestaurant());
                image.setFitHeight(130);
                image.setFitWidth(226);
                item1.setPadding(new Insets(5, 5, 5, 5));
                item1.setStyle(" -fx-background-color:#fff;" + "-fx-background-radius: 20");
                item1.getChildren().add(image);

                nomrest = new Label(rec.getNom());
                gui_description = new Label(rec.getDescription());
                //  item1.getChildren().add(iv1);
                item1.getChildren().add(nomrest);
                item1.getChildren().add(gui_description);

                //       image = new ImageView("file:avatar4.jpg");

                nomrest.setStyle("-fx-font: 24px Clibri;" + "-fx-font-weight: bold");
                gui_description.setStyle("-fx-font: 15px Clibri;" + "-fx-text-fill:#a7a290");

                item1.setOnMouseClicked((Event event) -> {

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        ReclamationController controller = fxmlLoader.getController();
                            
                        controller.getReclamation(rec.getId());
                        System.out.print(rec.getId());
                        controller.setPreScene(item1.getScene());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                        
                        

                        controller.setnom(rec.getNom());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                });
            }

        }
    }

    @FXML
    private void details(ActionEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("ListRestaurant2.fxml"));
            Parent root = loader.load();
            ListRestaurant2Controller pc = loader.getController();
            
     
            detailRestaurant.getScene().setRoot(root);
        
    }

}
