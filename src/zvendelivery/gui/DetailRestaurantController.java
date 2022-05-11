/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.MyListener;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class DetailRestaurantController implements Initializable {

    @FXML
    private Label name;
    @FXML
    private Label category;
    @FXML
    private Label gouv;
    @FXML
    private Label heureF;
    @FXML
    private Label heureO;
    @FXML
    private Text desc;
    @FXML
    private ImageView img;
    @FXML
    private Label adress;
    @FXML
    private Label cite;
    @FXML
    private Label codep;
    @FXML
    private VBox item1;

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
    private Restaurant restaurant;

    public void setData(Restaurant restaurant) {
        this.restaurant = restaurant;
        adress.setText(restaurant.getAddresse());
        cite.setText(restaurant.getCite());
        codep.setText(restaurant.getCodePostal()+"");
        name.setText(restaurant.getNom());
        desc.setText(restaurant.getDescription());
        category.setText(restaurant.getCategorieRestaurant());
        gouv.setText(restaurant.getGouvernorat());
        heureF.setText(restaurant.getHeureFermeture().substring(0,5));
        heureO.setText(restaurant.getHeureOuverture().substring(0,5));
        Image image = new Image(restaurant.getImageRestaurant(),526,150,false,false);
        img.setImage(image);
         item1.setOnMouseClicked((Event event) -> {

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("market.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        MarketController controller = fxmlLoader.getController();
                        controller.getProduits(restaurant);
                        controller.setPreScene(item1.getScene());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                        

                        controller.setnom(restaurant.getNom());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                });
       

    }

}
