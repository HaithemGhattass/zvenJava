/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;
import java.io.File;
import java.io.IOException;
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
import org.apache.commons.io.FileUtils;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.ServiceRestaurant;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class AjoutRestaurantController implements Initializable {

    private String path;
    File selectedFile;
    ObservableList<String> gouveList = FXCollections.observableArrayList("mm", "a");
    ObservableList<String> cateList = FXCollections.observableArrayList("chicken", "Italian","Burgers","chinese");

    @FXML
    private TextField nom;
    @FXML
    private TextField desc;
    @FXML
    private JFXComboBox cate;
    @FXML
    private TextField addresse;
    @FXML
    private TextField cite;
    @FXML
    private TextField codep;
    @FXML
    private JFXTimePicker time;

    @FXML
    private Button add;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxx;
    @FXML
    private JFXComboBox gouve;
    @FXML
    private ImageView ScreenshotView;
    @FXML
    private JFXTimePicker heureF;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cate.setItems(cateList);
        gouve.setItems(gouveList);
        
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
    private void addrestaurant(ActionEvent event) {
        try {

            /// SAUVEGARDE DANS LA BASE
            String resnom = nom.getText();
            String resdesc = desc.getText();
            String rescate = (String) cate.getValue();
            String resaddresse = addresse.getText();
            String rescite = cite.getText();
            String rescodep = codep.getText();
            String resgouv = (String) gouve.getValue();
            String restime = (time.getValue() != null ? time.getValue().toString() : "");
            String resF = (heureF.getValue() != null ? heureF.getValue().toString() : "");
            if( heureF.getValue().isBefore(time.getValue())){
                System.out.print("jawek nice ya haithem");
            }
            Restaurant r = new Restaurant(1, resnom, resdesc, resaddresse, rescite, restime, rescate, resF, resgouv, Integer.parseInt(rescodep), "", path, path, "not verified");

            ServiceRestaurant sr = new ServiceRestaurant();
            if (selectedFile == null) {
                System.out.print("walah lani khadem :// ");
            }

            if (selectedFile != null) {
                try {
                    System.out.println(selectedFile.toString());
                    File source = new File(selectedFile.toString());
                    File dest = new File("C:\\wamp64\\www\\WeDev_Zvenn\\public\\uploads");
                    FileUtils.copyFileToDirectory(source, dest);
                } catch (IOException ex) {
                    Logger.getLogger(AjoutRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            sr.ajouter(r);

            //// REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass().getResource("myRestaurants.fxml"));
            Parent root2 = loader.load();

            nom.getScene().setRoot(root2);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void setAfficheId(String valeur) {
        this.nom.setText(valeur);
    }

}
