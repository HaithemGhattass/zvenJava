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
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import zvendelivery.entites.Reply;
import zvendelivery.entites.Restaurant;
import zvendelivery.entites.SessionManager;
import zvendelivery.services.ServiceReply;
import zvendelivery.services.ServiceRestaurant;

/**
 * FXML Controller class
 *
 * @author mtar
 */
public class ReplyController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private Slider idReclamation;
    @FXML
    private Label id;
    @FXML
    private HBox home;
    @FXML
    private Label user;
   
   
  

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         home.setOnMouseClicked((Event event) -> {

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listRestaurant.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                });
        String r =id.getText();
        int res= (int) idReclamation.getValue();
        System.out.print(idReclamation.getValue());
        System.out.print(r);
        ArrayList<Reply> list = (ArrayList<Reply>) ServiceReply.getInstance().afficher(res);

             for (Reply rec : list) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ReplyItem.fxml"));
                VBox anchorPane = fxmlLoader.load();

                ReplyItemController ic = fxmlLoader.getController();
               ic.setData(rec,vbox);
                vbox.getChildren().add(anchorPane);
                
               
              //  vbox.getChildren().remove(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(ReplyController.class.getName()).log(Level.SEVERE, null, ex);
            }
            


        }
                          user.setText(SessionManager.getNom());

        // TODO
    }    
   public void setAfficheid(int valeur) {
        this.idReclamation.setValue(valeur);
    }
    public void setAffichei(String valeur) {
         String r =id.getText();
        int res= (int) idReclamation.getValue();
        System.out.print(idReclamation.getValue());
        System.out.print(r);
        ArrayList<Reply> list = (ArrayList<Reply>) ServiceReply.getInstance().afficher(res);

             for (Reply rec : list) {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("ReplyItem.fxml"));
                VBox anchorPane = fxmlLoader.load();

                ReplyItemController ic = fxmlLoader.getController();
               ic.setData(rec,vbox);
                vbox.getChildren().add(anchorPane);
                
               
              //  vbox.getChildren().remove(anchorPane);
            } catch (IOException ex) {
                Logger.getLogger(ReplyController.class.getName()).log(Level.SEVERE, null, ex);
            }
             home.setOnMouseClicked((Event event) -> {

                    try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("listRestaurant.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }

                });


        } 
    }
    
    
    
}
