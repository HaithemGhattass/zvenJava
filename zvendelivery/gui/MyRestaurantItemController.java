/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import java.io.IOException;
import java.net.URL;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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
public class MyRestaurantItemController implements Initializable {

    @FXML
    private HBox hbox;
    @FXML
    private Label name;
    @FXML
    private Label stauts;
    @FXML
    private Label date;
    @FXML
    private Label numb;
    @FXML
    private Pane pane;
    @FXML
    private ImageView edit;
    @FXML
    private ImageView delete;
    @FXML
    private ImageView addProduct;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }   

    public void setData(Restaurant restaurant,VBox vbox) {
      //  this.restaurant = restaurant;
        
        name.setText(restaurant.getNom());
        stauts.setText(restaurant.getStatus());
       
        date.setText(restaurant.getCreatedAt().substring(0,11));
         if (restaurant.getStatus().contains("not")){
             stauts.setTextFill(Color.color(1, 0, 0));
        }
         delete.setOnMouseClicked((Event event) -> {
               System.out.print(restaurant.getId());

             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Confirmation Dialog");
             alert.setHeaderText(null);
             alert.setContentText("do you want to cancel partnership of " +name.getText()+" with zvenn?");
             Optional<ButtonType> action = alert.showAndWait();
             if (action.get() == ButtonType.OK) {
                 new ServiceRestaurant().supprimer(restaurant.getId());
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("myRestaurants.fxml"));
                 MyRestaurantsController pc = loader.getController();
                vbox.getChildren().remove(hbox);
             

             }
             

                });
         edit.setOnMouseClicked((Event event)-> {
             
            try {
               // System.out.print(restaurant.getId());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierRestaurant.fxml"));
                Parent root2 = loader.load();
                ModifierRestaurantController pc = loader.getController();
                
                pc.setnom(name.getText());
                pc.setdesc(restaurant.getDescription());
                pc.setcate(restaurant.getCategorieRestaurant());
                pc.setgouv(restaurant.getGouvernorat());
                 pc.setgouv(restaurant.getGouvernorat());
                  pc.setheureF(LocalTime.parse(restaurant.getHeureFermeture()));
                pc.setheureO(LocalTime.parse(restaurant.getHeureOuverture()));

                   pc.setadr(restaurant.getAddresse());
                      pc.setcite(restaurant.getCite());
                      pc.setcodep(restaurant.getCodePostal()+"");
                      pc.setimage("C:\\wamp64\\www\\WeDev_Zvenn\\public\\uploads\\"+restaurant.getNomImage());
                      pc.setid(restaurant.getId());

                name.getScene().setRoot(root2);
            } catch (IOException ex) {
                Logger.getLogger(MyRestaurantItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
    });
  addProduct.setOnMouseClicked((Event event)-> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutProduit.fxml"));

                Parent root2 = loader.load();
                 AjoutProduitController pc = loader.getController();
                 System.out.print(restaurant.getId());
                 pc.getresid(Integer.toString(restaurant.getId()));

                addProduct.getScene().setRoot(root2);
            } catch (IOException ex) {
                Logger.getLogger(MyRestaurantItemController.class.getName()).log(Level.SEVERE, null, ex);
            }
  });
         
         
        
       int x=0;
       ArrayList<Produits> lists = (ArrayList<Produits>) ServiceProduit.getInstance().afficherbyrestaurants(restaurant);
           for(Produits p : lists){
                  x+=1;
           }
                  numb.setText(x+"");
                  
                  
       numb.setOnMouseClicked((Event event) -> {
                try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("myProducts.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        MyProductsController controller = fxmlLoader.getController();
                        controller.getProduits(restaurant);
                     //   controller.setPreScene(item1.getScene());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                        

                        controller.setnom(restaurant.getNom());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }          
      });
 
         

    }

    @FXML
    private void deleteAAction(MouseEvent event) {
         
    }

    @FXML
    private void editAction(MouseEvent event) {
    }

   
    
}
