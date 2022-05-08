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
import javafx.geometry.Pos;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.apache.commons.io.FileUtils;
import zvendelivery.entites.Reclamation;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.ServiceReclamation;
import zvendelivery.services.ServiceRestaurant;
import javafx.util.Duration;
import javax.management.Notification;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.Rating;
import zvendelivery.entites.SessionManager;
/**
 * FXML Controller class
 *
 * @author mtar
 */
public class AjouterReclamationController implements Initializable {

    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxx;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private ImageView ScreenshotView;
    @FXML
    private Button add;
    Notifications n;
    String erreur;
    /**
     * Initializes the controller class.
     */
    private String path;
    File selectedFile;
    private Slider food;
    private Slider prix;
    private Slider service;
    @FXML
    private Label rec;
    @FXML
    private Slider idrestaurant;
    @FXML
    private ImageView titreCheckMark;
    @FXML
    private ImageView DescCheckMark;
    @FXML
    private Rating fo;
    @FXML
    private Rating pr;
    @FXML
    private Rating sr;
    @FXML
    private Button annuler;
    @FXML
    private HBox home;
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
    private void ajouterReclamation(ActionEvent event) {
        try {
            

            /// SAUVEGARDE DANS LA BASE
            int resfood = (int) fo.getRating();
            int resprix = (int) pr.getRating();
            int resservice = (int) sr.getRating();
            int residres = (int) idrestaurant.getValue();
            String restitre = titre.getText();
            String resdescription = description.getText();
            if ( titre.getText().length()== 0 || description.getText().length()==0 )
        {
            titre.setStyle("-fx-border-color:yellow ; -fx-border-width : 2px ; ");
            new animatefx.animation.Shake(titre).play();
             description.setStyle("-fx-border-color:yellow ; -fx-border-width : 2px ; ");
            new animatefx.animation.Shake(description).play();
           
        }
           if (testSaisie()) {
               
            Reclamation r = new Reclamation(residres,SessionManager.getId(), restitre, resdescription,path,resfood,resprix,resservice);
new animatefx.animation.Shake(titre).play();
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
                    Logger.getLogger(AjouterReclamationController.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            sr.ajouter(r);
             System.out.println("ajouté avec succes");
            n = Notifications.create()
                    .title("Succes")
                    .text("Reclamation envoyé avec succes")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(3));
            n.showInformation();
            //// REDIRECTION
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        ReclamationController controller = fxmlLoader.getController();
                            
                        controller.getReclamation(residres);
                        System.out.print(r);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
           }

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
        public void setAffiches(int valeur) {
        this.sr.setRating(valeur);
    }
         public void setAffichep(int valeur) {
        this.pr.setRating(valeur);
    }
     
            
             private Boolean testSaisie() {
        erreur = "";
        if (!testTitre()) {
            erreur = erreur + ("Veuillez verifier votre Titre: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testDesc()) {
            erreur = erreur + ("Veuillez verifier votre Description: seulement des caractères et de nombre >= 8");
        }

        if (!testTitre() || !testDesc()) {
            n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            n.showInformation();
        }

        return  testTitre() && testDesc();
    }

    


//   

    

    private Boolean testTitre() {
        int nbNonChar = 0;
        for (int i = 1; i < titre.getText().trim().length(); i++) {
            char ch = titre.getText().charAt(i);
            if (Character.isDigit(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && titre.getText().trim().length() >= 3) {
            titreCheckMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery2\\zv\\src\\zvendelivery\\img\\checkmark_48px.png"));
            return true;
        } else {
           titreCheckMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery2\\zv\\src\\zvendelivery\\img\\delete_48px.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    private Boolean testDesc() {
        int nbNonChar = 0;
        for (int i = 1; i < description.getText().trim().length(); i++) {
            char ch = description.getText().charAt(i);
            if (Character.isDigit(ch)) {
                nbNonChar++;
            }
            
        }

        if (nbNonChar == 0 && description.getText().trim().length() >= 8) {
            DescCheckMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery2\\zv\\src\\zvendelivery\\img\\checkmark_48px.png"));
            return true;
        } else {
            DescCheckMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery2\\zv\\src\\zvendelivery\\img\\delete_48px.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    @FXML
    private void annuler(ActionEvent event) {
          try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Reclamation.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());

                        ReclamationController controller = fxmlLoader.getController();
                            
                        controller.getReclamation((int) idrestaurant.getValue());
                        System.out.print((int) idrestaurant.getValue());
                       // controller.setPreScene(item1.getScene());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                        
                       // controller.setnom(rec.getNom());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
    }
           public void setAffichere(int valeur) {
        this.idrestaurant.setValue(valeur);
    }

    @FXML
    private void home(MouseEvent event) {
        try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ListRestaurant.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();
                        
                       // controller.setnom(rec.getNom());
                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
        
    }
}
