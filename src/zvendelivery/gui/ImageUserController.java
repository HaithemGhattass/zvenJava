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
import org.apache.commons.io.FileUtils;
import zvendelivery.entites.SessionManager;
import zvendelivery.entites.Utilisateur;
import zvendelivery.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class ImageUserController implements Initializable {

    private String path;
    File selectedFile;



    @FXML
    private Button modif;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxx;
 
    @FXML
    private ImageView ScreenshotView;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
        ScreenshotView.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                if (db.hasFiles()) {
                    event.acceptTransferModes(TransferMode.COPY);
                } else {
                    event.consume();
                }
            }
        });

        // Dropping over surface
        ScreenshotView.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard db = event.getDragboard();
                boolean success = false;
                if (db.hasFiles()) {
                    success = true;
                    path = null;
                    for (File file : db.getFiles()) {
                        path = file.getName();
                        selectedFile = new File(file.getAbsolutePath());
                        System.out.println("Drag and drop file done and path=" + file.getAbsolutePath());//file.getAbsolutePath()="C:\Users\X\Desktop\ScreenShot.6.png"
                        ScreenshotView.setImage(new Image("file:" + file.getAbsolutePath()));
//                        screenshotView.setFitHeight(150);
//                        screenshotView.setFitWidth(250);
                        //   image.setText(path);
                    }
                }
                event.setDropCompleted(success);
                event.consume();
            }
        });
    }

    @FXML
    private void updateimage(ActionEvent event) {
        try {

            /// SAUVEGARDE DANS LA BASE
           




    
            if (selectedFile == null) {
                JOptionPane.showMessageDialog(null,"Veuillez remplir le champs de photo","Message",JOptionPane.WARNING_MESSAGE);

            }                                                    
            else{                 

            ServiceUser su = new ServiceUser();
               su.modiferphoto(path,SessionManager.getId());
               try {
                    System.out.println(selectedFile.toString());
                    File source = new File(selectedFile.toString());
                    File dest = new File("C:\\Users\\HP\\Desktop\\zvendelivery\\src\\zvendelivery\\img");
                    FileUtils.copyFileToDirectory(source, dest);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutUserController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

         

            //// REDIRECTION
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileUser.fxml"));
            Parent root2 = loader.load();

            modif.getScene().setRoot(root2);
             SessionManager.setNomImage(path);
        
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

  

}
