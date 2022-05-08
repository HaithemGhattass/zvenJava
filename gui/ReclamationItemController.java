/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Rating;
import zvendelivery.entites.Produits;
import zvendelivery.entites.Reclamation;
import zvendelivery.entites.SessionManager;
import zvendelivery.services.MyListener;
import zvendelivery.services.ServiceReclamation;
import zvendelivery.services.ServiceRestaurant;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class ReclamationItemController implements Initializable {

    @FXML
    private Label username;
    @FXML
    private Label titre;
   
    
    @FXML
    private Text description;
    @FXML
    private Rating rating;
    @FXML
    private Label date;
      private Reclamation reclamation;
          private MyListener myListener;
    @FXML
    private ImageView modifier;
    @FXML
    private ImageView supprimer;
    @FXML
    private ImageView image;
    @FXML
    private VBox hbox;
    @FXML
    private Slider food;
    @FXML
    private Slider service;
    @FXML
    private Slider prix;
    @FXML
    private Slider id;
    @FXML
    private ImageView addReply;
    @FXML
    private Slider restaurant_id;
    @FXML
    private ImageView avatar;
   
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
      public void setData(Reclamation reclamation, VBox vbox,Label nbr) {
          DecimalFormat df = new DecimalFormat(".##");
          float r=0;
          float sum=0;
        this.reclamation = reclamation;
         sum =reclamation.getPrice()+reclamation.getService()+reclamation.getFoodqulaite();
         r= sum/3;
       // this.myListener = myListener;
        username.setText(reclamation.getUser().getPseudo());
        //id.se(reclamation.getId().toString);
        titre.setText(reclamation.getTitre());
        description.setText(reclamation.getDescription());
                date.setText(reclamation.getDate().toString());
                rating.setRating(r);
                food.setValue(reclamation.getFoodqulaite());
                prix.setValue(reclamation.getPrice());
                service.setValue(reclamation.getService());
                id.setValue(reclamation.getId());
                restaurant_id.setValue(reclamation.getRestaurant_id());
                
                
                
               
                       
       Image img = new Image(reclamation.getNomimage());
      
        image.setImage(img);
         Image im = new Image(reclamation.getUser().getNomimage());
       avatar.setImage(im);
       // avatar.setImage(im);
        if(reclamation.getID_user()==SessionManager.getId())
        {
          supprimer.setOnMouseClicked((Event event) -> {

             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Confirmation Dialog");
             alert.setHeaderText(null);
             alert.setContentText("do you want to delete " +titre.getText()+" with zvenn?");
             Optional<ButtonType> action = alert.showAndWait();
             if (action.get() == ButtonType.OK) {
                 new ServiceReclamation().supprimer(reclamation.getId());
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("ListRestaurant.fxml"));
                 ReclamationController pc = loader.getController();
                vbox.getChildren().remove(hbox);
           nbr.setText(Integer.parseInt(nbr.getText())-1+"");
                 
            
             }

                });
          modifier.setOnMouseClicked((Event event) -> {

              try {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("modifierReclamation.fxml"));
                  Parent root2 = loader.load();
                  ModifierReclamationController pc = loader.getController();
                  
                  pc.setAffichetitre(titre.getText());
                  pc.setAffichedescription(description.getText());
                  pc.setAffichef((int) food.getValue());
                  pc.setAfficheid((int) id.getValue());                  
                  pc.setAffiches((int) service.getValue());
                  pc.setAffichep((int) prix.getValue());
                  pc.setAfficheidres((int) restaurant_id.getValue());
                 
                  titre.getScene().setRoot(root2);

              } catch (IOException ex) {
                  Logger.getLogger(ReclamationItemController.class.getName()).log(Level.SEVERE, null, ex);
              }

                });
           
            
        }
        else
        {
        modifier.setVisible(false);
        supprimer.setVisible(false);
        
        
        }
         hbox.setOnMouseClicked((Event event) -> {
              try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("Reply.fxml"));
            Parent root = loader.load();
            ReplyController pc = loader.getController();
            
                        pc.setAfficheid((int) id.getValue());
                        pc.setAffichei( titre.getText());
     
            titre.getScene().setRoot(root);
        
              } catch (IOException ex) {
                  Logger.getLogger(ReclamationItemController.class.getName()).log(Level.SEVERE, null, ex);
              }
                        
                  
                  
                  });
              addReply.setOnMouseClicked((Event event) -> {

              try {
                  FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterReply.fxml"));
                  Parent root2 = loader.load();
                  AjouterReplyController pc = loader.getController();
                         pc.setAffichere((int) id.getValue()); 
                         
                 
                 
                  titre.getScene().setRoot(root2);

              } catch (IOException ex) {
                  Logger.getLogger(ReclamationItemController.class.getName()).log(Level.SEVERE, null, ex);
              }

                });
                 
                 
                

      
    }

    @FXML
    private void supprimer(MouseEvent event) {
    }

    @FXML
    private void modifier(MouseEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutReclamation.fxml"));
            Parent root2 = loader.load();
            AjoutRestaurantController pc = loader.getController();
            
            pc.setAfficheId(titre.getText());
           
            
            titre.getScene().setRoot(root2);
    }
    
}
