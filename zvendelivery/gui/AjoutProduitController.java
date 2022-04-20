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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.VBox;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import zvendelivery.entites.Produits;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.ServiceProduit;
import zvendelivery.services.ServiceRestaurant;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class AjoutProduitController implements Initializable {

    @FXML
    private Button add;
    @FXML
    private TextField nom;

    String res;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxx;
    @FXML
    private TextField desc;
    @FXML
    private TextField prix;
    @FXML
    private ImageView ScreenshotView;
    
        private String path;
    File selectedFile;
     Notifications n;
    String erreur;


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
    Restaurant restaurant;

    public void getresid(String valeur) {
        this.res = valeur;
    }

    @FXML
    private void addproduct(ActionEvent event) throws IOException {
               try {

        String resnom = nom.getText();
         String resdesc = desc.getText();
          String resprix = prix.getText();
         if (testSaisie()) {
        Produits pr = new Produits(Integer.parseInt(res), Float.parseFloat(resprix), resnom, resdesc, "", path, path);
        ServiceProduit sr = new ServiceProduit();
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
                        sr.ajouter(pr);

           n = Notifications.create()
                        .title("Succes")
                        .text("Produit ajouté avec succes")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(3));
                n.showInformation();
        

        FXMLLoader loader = new FXMLLoader(getClass().getResource("myRestaurants.fxml"));
        Parent root2 = loader.load();
        MyRestaurantsController pc = loader.getController();

        nom.getScene().setRoot(root2);
         }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
      private Boolean testSaisie() {
        erreur = "";

        if (!testPrix()) {
            erreur = erreur + ("Prix doit etre entier \n");
        }

        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testDesc()) {
            erreur = erreur + ("Veuillez verifier votre description: seulement des caractères et de nombre >= 5 \n");
        }
       if (!testImage()) {
            erreur = erreur + ("Il faut avoir une image pour votre produit \n");
        }

        if (!testPrix() || !testNom() || !testDesc() || !testImage()) {
            n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            n.showInformation();
        }

        return testPrix() && testNom() && testDesc() &&  testImage();
    }
       private Boolean testPrix() {
            int nbChar = 0;
             for (int i = 1; i < prix.getText().trim().length(); i++) {
            char ch = prix.getText().charAt(i);
            if (!Character.isDigit(ch)) {
                nbChar++;
            }
        }

        if ( nbChar==0 &&prix.getText().trim().length() >= 1) {
          //  NomCheckMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\checkmark_48px.png"));
            return true;
        } else {
          //  NomCheckMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\delete_48px.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }
         


    }

    private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < nom.getText().trim().length(); i++) {
            char ch = nom.getText().charAt(i);
            if (Character.isDigit(ch)) {
                nbNonChar++;
            }
        }

        if ( nbNonChar==0 &&nom.getText().trim().length() >= 3) {
          //  NomCheckMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\checkmark_48px.png"));
            return true;
        } else {
          //  NomCheckMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\delete_48px.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    private Boolean testDesc() {
    

        if (desc.getText().trim().length() >= 5) {
          //  descMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\checkmark_48px.png"));
            return true;
        } else {
          //  descMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\delete_48px.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

     private Boolean testImage() {
       
         if (path!=null) {
                        // timeCheck.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\checkmark_48px.png"));

              return true;
            }
        else {
                        // timeCheck.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\delete_48px.png"));

      return false;
        }
    }


}
