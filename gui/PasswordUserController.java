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
import zvendelivery.entites.SessionManager;
import zvendelivery.entites.Utilisateur;
import zvendelivery.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class PasswordUserController implements Initializable {




    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField password2;



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
      
     
    }

    @FXML
    private void modifpsw(ActionEvent event) throws NoSuchAlgorithmException {
        try {

            /// SAUVEGARDE DANS LA BASE
           
            String passwordd = password.getText();
          String passwordd2 = password2.getText();

        
        
     


     
                      if(passwordd.length()<8){
                JOptionPane.showMessageDialog(null,"le mot de passe doit contenir un minimum de 8 chiffre","Message",JOptionPane.WARNING_MESSAGE);
            } else {
                              if(!passwordd.equals(passwordd2)){
                JOptionPane.showMessageDialog(null,"Les mot de passe n'est sont pas identiques","Message",JOptionPane.WARNING_MESSAGE);
            } else {
                               
                                                               
MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(passwordd.getBytes());
    byte[] digest = md.digest();
    String myHash = DatatypeConverter
      .printHexBinary(digest).toUpperCase();

                                  ServiceUser su = new ServiceUser();
            su.modiferpassword(myHash, SessionManager.getId());
          
         

       
         

            //// REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileUser.fxml"));
            Parent root2 = loader.load();

            add.getScene().setRoot(root2);
            
}}
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }



}
