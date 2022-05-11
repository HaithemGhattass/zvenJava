/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;
import java.util.regex.*; 
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;
import org.apache.commons.io.FileUtils;
import zvendelivery.entites.Message;
import zvendelivery.entites.SessionManager;
import zvendelivery.entites.Utilisateur;
import zvendelivery.services.ServiceMessage;
import zvendelivery.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class AjoutMessageController implements Initializable {

   @FXML
    ObservableList<String> user = FXCollections.observableArrayList("19", "20");
  @FXML
    private TextField message;
  
  @FXML
    private JFXComboBox users;


    @FXML
    private Button add;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxx;
 
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
          users.setItems(user);
     
    }

    @FXML
    private void ajoutmessage(ActionEvent event) throws NoSuchAlgorithmException {
        try {
   String messagee = message.getText();
    String userr = (String)users.getValue();

     
                      if(messagee.length()<0){
                JOptionPane.showMessageDialog(null,"Veuillez remplir le champs de mot de passe","Message",JOptionPane.WARNING_MESSAGE);
            } else {
   

  Message r = new Message(SessionManager.getId(), parseInt(userr),0,messagee,"2022-03-24");

            ServiceMessage su = new ServiceMessage();
               su.ajouter(r);
                          FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
            Parent root2 = loader.load();

            add.getScene().setRoot(root2);
            
}
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }



}
