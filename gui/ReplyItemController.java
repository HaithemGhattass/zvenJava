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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import zvendelivery.entites.Reclamation;
import zvendelivery.entites.Reply;
import zvendelivery.entites.SessionManager;
import zvendelivery.services.ServiceReclamation;
import zvendelivery.services.ServiceReply;

/**
 * FXML Controller class
 *
 * @author mtar
 */
public class ReplyItemController implements Initializable {

    @FXML
    private VBox hbox;
    @FXML
    private Label username;
    @FXML
    private Label titre;
    
    @FXML
    private ImageView modifier;
    @FXML
    private ImageView supprimerReply;
    @FXML
    private Text description;
    @FXML
    private Label date;
    @FXML
    private Slider id;
private Reply reply;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    public void setData(Reply reply, VBox vbox) {
        

        this.reply = reply;
         
       // this.myListener = myListener;
        username.setText(reply.getUser().getPseudo());
        //id.se(reclamation.getId().toString);
        titre.setText(reply.getTitre());
        description.setText(reply.getDescription());
        date.setText(reply.getDate().toString());
        if(reply.getUser_id()==SessionManager.getId())
        {
        supprimerReply.setOnMouseClicked((Event event) -> {

             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
             alert.setTitle("Confirmation Dialog");
             alert.setHeaderText(null);
             alert.setContentText("do you want to delete your reply " +titre.getText());
             Optional<ButtonType> action = alert.showAndWait();
             if (action.get() == ButtonType.OK) {
                 new ServiceReply().supprimer(reply.getId());
                 FXMLLoader loader = new FXMLLoader(getClass().getResource("ListRestaurant.fxml"));
                 ReclamationController pc = loader.getController();
                vbox.getChildren().remove(hbox);
          // nbr.setText(Integer.parseInt(nbr.getText())-1+"");
                 
            
             }

                });
        }
        else
            supprimerReply.setVisible(false);
            
      
    }

    @FXML
    private void modifier(MouseEvent event) {
    }

    @FXML
    private void supprimer(MouseEvent event) {
    }
    
}
