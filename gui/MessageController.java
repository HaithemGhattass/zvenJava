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
import javafx.scene.control.Button;
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
import zvendelivery.entites.Message;
import zvendelivery.entites.Produits;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.MyListener;
import zvendelivery.services.ServiceMessage;
import zvendelivery.services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class MessageController implements Initializable {

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
    @FXML
    private Button envbtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                ArrayList<Message> lists = (ArrayList<Message>)ServiceMessage.getInstance().afficher();
      
       
        for (Message p : lists) {
           System.out.print(p);    
    
        }
    }    

 

    @FXML
    private void goProduits(MouseEvent event) throws IOException {
      

    }
     public void getProduitss(Restaurant restaurant) {
        // System.out.print("xxxxxxxxxxxxx");
 /*  GridPane.setMargin(anchorPane, new Insets(10));
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
     
     @FXML
        private void envopen(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutMessage.fxml"));
            Parent root2 = loader.load();

            envbtn.getScene().setRoot(root2);

    }
}

    

