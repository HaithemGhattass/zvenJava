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
import javafx.scene.text.Text;
import javafx.stage.Stage;
import zvendelivery.entites.Message;
import zvendelivery.entites.Restaurant;
import zvendelivery.entites.SessionManager;
   
/**
 * FXML Controller class
 *
 * @author haith
 */

public class MessageItemController implements Initializable {
 @FXML
    private Label date;
    @FXML
    private Label sender;
    @FXML
    private Text message;
      @FXML
    private ImageView Image;
    javafx.scene.image.Image profile;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
    
      public void setData(Message messages) {
       
        date.setText(messages.getCreated_at());
    
        message.setText(messages.getMessage());
           this.profile = new javafx.scene.image.Image("file:/C:/Users/HP/Desktop/zvendelivery/src/zvendelivery/img/"+SessionManager.getNomImage());
             Image.setImage(profile);
  

    }

    
}
