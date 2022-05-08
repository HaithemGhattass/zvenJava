/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import chatbot.Chatbot;
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
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import zvendelivery.entites.Produits;
import zvendelivery.entites.Reclamation;
import zvendelivery.entites.Reply;
import zvendelivery.entites.Restaurant;
import zvendelivery.entites.SessionManager;
import zvendelivery.services.MyListener;
import zvendelivery.services.ServiceProduit;
import zvendelivery.services.ServiceReclamation;
import zvendelivery.services.ServiceReply;
import zvendelivery.services.ServiceRestaurant;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class ReclamationController implements Initializable {

    @FXML
    private Label restaurantname;
    @FXML
    private Label nombre;
    @FXML
    private ScrollPane scroll;
    @FXML
    private VBox vbox;
    private Image image;
    private MyListener myListener;
    @FXML
    private HBox home;
    private Scene preScene;
    private HBox add;
    @FXML
    private HBox addReclamation;
         int nbr = 0;
         int id=0;
         int idr=0;
    @FXML
    private Slider idrestaurant;
    private Restaurant r;
    String[] args;
    @FXML
    private HBox chat;
    @FXML
    private Label user;
    @FXML
    private HBox dash;
    
    
   


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
     
     public void getReclamation(int id) {
         ArrayList<Reclamation> list = (ArrayList<Reclamation>) ServiceReclamation.getInstance().affichers(id);

 
         for (Reclamation rec : list) {
            //idrestaurant.setValue(restaurant.getId());
                 nbr= nbr+1;
               //  id=rec.getRestaurant_id();
                 idr=rec.getId();
                 r=rec.getRestaurant();
                 System.out.print(r);
 }
          addReclamation.setOnMouseClicked((Event event) -> {

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ajouterReclamation.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        AjouterReclamationController controller = fxmlLoader.getController();
                        controller.setAffichere(id);
                       // controller.setre(r);
                        
                        
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                        

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                });
          
          home.setOnMouseClicked((Event event) -> {
              try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListRestaurant.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                        
                       // controller.setnom(rec.getNom());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                });
             dash.setOnMouseClicked((Event event) -> {
              try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MyRestaurants.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                        
                       // controller.setnom(rec.getNom());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                });

             for (Reclamation rec : list) {
            try {
               
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ReclamationItem.fxml"));
                VBox anchorPane = fxmlLoader.load();
                ReclamationItemController ic = fxmlLoader.getController();
                ic.setData(rec,vbox,nombre);
                vbox.getChildren().add(anchorPane);
                 
               
            //   vbox.getChildren().remove(vbox);
            } catch (IOException ex) {
                Logger.getLogger(MyRestaurantsController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
             nombre.setText(nbr+"");
             user.setText(SessionManager.getNom());
    }

    
      public void setPreScene(Scene preScene) {
        this.preScene = preScene;
    }
       public void setnom(String valeur) {
       
               this.restaurantname.setText(valeur);
  
    }
       public void setAfficheidR(int valeur) {
        this.idrestaurant.setValue(valeur);
    }

    @FXML
    private void chat(MouseEvent event) {
         Stage aa = new Stage();
              Chatbot b = new Chatbot();
              b.start(aa);
    }
    
       
}

    

