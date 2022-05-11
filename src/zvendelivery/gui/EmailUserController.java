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
public class EmailUserController implements Initializable {




    @FXML
    private TextField code;


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
    private void verifemail(ActionEvent event) throws NoSuchAlgorithmException {
        try {

            /// SAUVEGARDE DANS LA BASE
           
            String codee = code.getText();  
         

                              if(!codee.equals(SessionManager.getPhoneCode())){
                JOptionPane.showMessageDialog(null,"Le code verification est invalid","Message",JOptionPane.WARNING_MESSAGE);
            } else {
                               
                                                               



                                  ServiceUser su = new ServiceUser();
            su.modiferetatemail(SessionManager.getId());
           SessionManager.setIs_verified(1);

            
          
         

       
         

            //// REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileUser.fxml"));
            Parent root2 = loader.load();

            add.getScene().setRoot(root2);
            
}
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }



}
