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
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
import zvendelivery.entites.Restaurant;
import zvendelivery.services.ServiceRestaurant;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class ModifierRestaurantController implements Initializable {

    ObservableList<String> gouveList = FXCollections.observableArrayList("tunis", "ariana", "beja", "ben arous",
                "bizerte", "gabes", "gafsa","Jendouba","Kairouan","Kasserine","kebili","kef","mahdia","manouba","manouba","medenine","monastir","nabeul","sfax"
        ,"nabeul","sidi bouzid","siliana","tataouine","Tozeur","zaghouan");
    ObservableList<String> cateList = FXCollections.observableArrayList("chicken", "Italian", "Burgers", "chinese");

    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxx;
    @FXML
    private TextField nom;
    @FXML
    private TextField desc;
    @FXML
    private ComboBox<String> cate;
    @FXML
    private ComboBox<String> gouve;
    @FXML
    private TextField addresse;
    @FXML
    private TextField cite;
    @FXML
    private TextField codep;
    @FXML
    private JFXTimePicker time;
    @FXML
    private JFXTimePicker heureF;
    @FXML
    private ImageView ScreenshotView;
    @FXML
    private Button edit;
    private String path;
    String erreur;

    File selectedFile;
    Integer idR;
    Notifications n;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        cate.setItems(cateList);
        gouve.setItems(cateList);
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
    private void editR(ActionEvent event) {
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
            if (heureF.getValue().isBefore(time.getValue())) {
                System.out.print("jawek nice ya haithem");
            }
            if (testSaisie()) {
                Restaurant s = new Restaurant(1, idR, resnom, resdesc, resaddresse, rescite, restime, rescate, resF, resgouv, Integer.parseInt(rescodep), "", path, path, "not verified");
                // Restaurant ss = new Restaurant(1,idR, resnom, resdesc, resaddresse, rescite, restime, rescate, resF, resgouv, Integer.parseInt(rescodep), "", path, path, "not verified");

//  Restaurant s = new Restaurant(31,idR,"lalalalalla","BOOdM","TUNIS","WAHHAAAT","13:00","fast ","21:00","tunis",2055,"2022-03-24 14:46:03","imagekfc","imagekfc.jpg","verified");
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
                        Logger.getLogger(ModifierRestaurantController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }

                ServiceRestaurant.getInstance().modifer(s);
                n = Notifications.create()
                        .title("Succes")
                        .text("Restaurant modifié avec succes")
                        .graphic(null)
                        .position(Pos.TOP_CENTER)
                        .hideAfter(Duration.seconds(3));
                n.showInformation();
                //// REDIRECTION
                FXMLLoader loader = new FXMLLoader(getClass().getResource("myRestaurants.fxml"));
                Parent root2 = loader.load();

                nom.getScene().setRoot(root2);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void setnom(String valeur) {
        this.nom.setText(valeur);
    }

    public void setdesc(String valeur) {
        this.desc.setText(valeur);
    }

    public void setcate(String valeur) {
        this.cate.setValue(valeur);
    }

    public void setheureF(LocalTime valeur) {
        this.heureF.setValue(valeur);
    }

    public void setheureO(LocalTime valeur) {
        this.time.setValue(valeur);
    }

    public void setgouv(String valeur) {
        this.gouve.setValue(valeur);
    }

    public void setadr(String valeur) {
        this.addresse.setText(valeur);
    }

    public void setcite(String valeur) {
        this.cite.setText(valeur);
    }

    public void setcodep(String valeur) {
        this.codep.setText(valeur);
    }

    public void setimage(String valeur) {
        this.ScreenshotView.setImage(new Image("file:" + valeur));
    }

    public void setid(Integer id) {
        idR = id;

    }

    private Boolean testSaisie() {
        erreur = "";

        if (!testCodep()) {
            erreur = erreur + ("Code postale doit avoir 4 chiffres et ne doit pas contenir des caracteres \n");
        }

        if (!testNom()) {
            erreur = erreur + ("Veuillez verifier votre Nom: seulement des caractères et de nombre >= 3 \n");
        }
        if (!testDesc()) {
            erreur = erreur + ("Veuillez verifier votre description: seulement des caractères et de nombre >= 10");
        }
        if (!testAddresse()) {
            erreur = erreur + ("Veuillez verifier votre addresse: seulement des caractères et de nombre >= 10");
        }
        if (!testCite()) {
            erreur = erreur + ("Veuillez verifier votre cite: seulement des caractères et de nombre >= 4");
        }
        if (!testTime()) {
            erreur = erreur + ("Heure fermeture doit etre aprés l'heure d'ouverture");
        }
        if (!testImage()) {
            erreur = erreur + ("Il faut comfirmer l'image pour votre restaurant");
        }

        if (!testCodep() || !testNom() || !testDesc() || !testAddresse() || !testCite() || !testTime() || !testImage()) {
            n = Notifications.create()
                    .title("Erreur de format")
                    .text(erreur)
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(6));
            n.showInformation();
        }

        return testCodep() && testNom() && testDesc() && testAddresse() && testCite() && testTime() && testImage();
    }

    private Boolean testCodep() {
        if (codep.getText().trim().length() == 4) {
            int nbChar = 0;
            for (int i = 1; i < codep.getText().trim().length(); i++) {
                char ch = codep.getText().charAt(i);
                if (Character.isLetter(ch) || Character.isSpaceChar(ch)) {
                    nbChar++;
                }
            }

            if (nbChar == 0) {
//            postalMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\checkmark_48px.png"));
                return true;
            } else {
                //       NomCheckMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\delete_48px.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
                return false;

            }
        } else if (codep.getText().trim().length() != 4) {
//            erreur = erreur + ("Il faut saisir 8 chiffres dans le numéro de telephone\n");
            //      NomCheckMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\delete_48px.png"));
            return false;
        }

        return true;

    }

    private Boolean testNom() {
        int nbNonChar = 0;
        for (int i = 1; i < nom.getText().trim().length(); i++) {
            char ch = nom.getText().charAt(i);
            if (Character.isDigit(ch)) {
                nbNonChar++;
            }
        }

        if (nbNonChar == 0 && nom.getText().trim().length() >= 3) {
            //     NomCheckMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\checkmark_48px.png"));
            return true;
        } else {
            //       NomCheckMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\delete_48px.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    private Boolean testDesc() {

        if (desc.getText().trim().length() >= 10) {
            //    descMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\checkmark_48px.png"));
            return true;
        } else {
            //    descMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\delete_48px.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    private Boolean testAddresse() {

        if (addresse.getText().trim().length() >= 10) {
            //     adressMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\checkmark_48px.png"));
            return true;
        } else {
            //      adressMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\delete_48px.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    private Boolean testCite() {

        if (cite.getText().trim().length() >= 4) {
            //   cityMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\checkmark_48px.png"));
            return true;
        } else {
            //    cityMark.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\delete_48px.png"));
//                erreur = erreur + ("Pas de caractere permit dans le telephone\n");
            return false;

        }

    }

    private Boolean testTime() {

        if (!heureF.getValue().isBefore(time.getValue())) {
            //          timeCheck.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\checkmark_48px.png"));

            return true;
        } else {
            //       timeCheck.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\delete_48px.png"));

            return false;
        }
    }

    private Boolean testImage() {

        if (path != null) {
            // timeCheck.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\checkmark_48px.png"));

            return true;
        } else {
            // timeCheck.setImage(new Image("file:C:\\wamp64\\www\\zvendelivery\\src\\zvendelivery\\img\\delete_48px.png"));

            return false;
        }
    }

}
