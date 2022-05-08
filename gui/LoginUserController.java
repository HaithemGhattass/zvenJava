/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;


import java.io.File;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
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
public class LoginUserController implements Initializable {

   
  

    @FXML
    private JFXPasswordField password2;
    @FXML
    private TextField email2;
    @FXML
    private Button add;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxx;
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException, NoSuchAlgorithmException {
        String emaill = email2.getText();
            String passwordd = password2.getText();
         
                ServiceUser su = new ServiceUser();
                      MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(passwordd.getBytes());
    byte[] digest = md.digest();
    String myHash = DatatypeConverter
      .printHexBinary(digest).toUpperCase();
                Utilisateur a=  su.login(emaill,myHash );
                if(emaill.equals("")){
                JOptionPane.showMessageDialog(null,"veuillez remplir le champs de email","Message",JOptionPane.WARNING_MESSAGE);
            }else{
             if(passwordd.equals("")){
                JOptionPane.showMessageDialog(null,"veuillez remplir le champs de mot de passe","Message",JOptionPane.WARNING_MESSAGE);
            } else {
                if(a.getId()==0)
                JOptionPane.showMessageDialog(null,"nexiste pas","Message",JOptionPane.WARNING_MESSAGE);
                else{
                    
                     JOptionPane.showMessageDialog(null,"existe ","Message",JOptionPane.WARNING_MESSAGE);
                    SessionManager.setActif(true);
                    SessionManager.setEmail(emaill);
                    SessionManager.setId(a.getId());
                    SessionManager.setAdresse(a.getAdresse());
                    SessionManager.setNom(a.getNom());
                    SessionManager.setPrenom(a.getPrenom());
                    SessionManager.setNumtel(a.getNumtel());
                    SessionManager.setUserName(a.getPseudo());
                    SessionManager.setNomImage(a.getNomimage());
                    SessionManager.setRole(a.getRole());
                 
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("ListRestaurant.fxml"));
            Parent root2 = loader.load();
            email2.getScene().setRoot(root2);
            

                }}}


    }


    /**
     * Initializes the controller class.
     */


   

}
