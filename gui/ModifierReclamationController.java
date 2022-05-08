/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Rating;
import zvendelivery.entites.Reclamation;
import zvendelivery.services.ServiceReclamation;

/**
 * FXML Controller class
 *
 * @author mtar
 */
public class ModifierReclamationController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxx;
    private VBox food;
    @FXML
    private Rating prix;
    @FXML
    private Rating service;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private ImageView ScreenshotView;
    @FXML
    private Button add;

    /**
     * Initializes the controller class.
     */
    private String path;
    File selectedFile;
    @FXML
    private Slider id;
    @FXML
    private Rating fo;
    @FXML
    private Button annuler;
    @FXML
    private Slider restaurant_id;
   
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
    private void ModifierReclamation(ActionEvent event) {
         try {
            

            /// SAUVEGARDE DANS LA BASE
            int resfood = (int) fo.getRating();
            int resprix = (int) prix.getRating();
            int resservice = (int) service.getRating();
            int resId= (int) id.getValue();
            int resIdd= (int) restaurant_id.getValue();
            String restitre = titre.getText();
            String resdescription = description.getText();
            System.out.print(resIdd);
           
           // String rescate = (String) cate.getValue();
         
            Reclamation r = new Reclamation(resId,resIdd,1, restitre, resdescription,path,resfood,resprix,resservice);

            ServiceReclamation sr = new ServiceReclamation();
            if (selectedFile == null) {
                System.out.print("walah lani khadem :// ");
            }

            if (selectedFile != null) {
                try {
                    System.out.println(selectedFile.toString());
                    File source = new File(selectedFile.toString());
                    File dest = new File("C:\\wamp64\\www\\denyaberda\\zvenproject\\public\\uploads");
                    FileUtils.copyFileToDirectory(source, dest);
                } catch (IOException ex) {
                    Logger.getLogger(ModifierReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            sr.modifier(r);

            //// REDIRECTION
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        ReclamationController controller = fxmlLoader.getController();
                            
                        controller.getReclamation(resIdd);
                        System.out.print(r);
                       // controller.setPreScene(item1.getScene());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
   

    public void setAffichetitre(String valeur) {
        this.titre.setText(valeur);
    }
      public void setAffichedescription(String valeur) {
        this.description.setText(valeur);
    }
       public void setAffichef(int valeur) {
        this.fo.setRating(valeur);
    }
      public void setAffichep(int valeur) {
        this.prix.setRating(valeur);
    }
      public void setAffiches(int valeur) {
        this.service.setRating(valeur);
    }
        public void setAfficheid(int valeur) {
        this.id.setValue(valeur);
    }

    @FXML
    private void annuler(ActionEvent event) {
        try {           
            int r=(int) restaurant_id.getValue();
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        ReclamationController controller = fxmlLoader.getController();
                            
                        controller.getReclamation(r);
                        System.out.print(r);
                       // controller.setPreScene(item1.getScene());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                        
                       // controller.setnom(rec.getNom());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
    }
       public void setAfficheidres(int valeur) {
        this.restaurant_id.setValue(valeur);
    }
}
