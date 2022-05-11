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
import zvendelivery.entites.Utilisateur;
import zvendelivery.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class AjoutUserController implements Initializable {

    private String path;
    File selectedFile;
    ObservableList<String> sexxe = FXCollections.observableArrayList("femme", "homme");
    ObservableList<String> roles = FXCollections.observableArrayList("Client", "Vendeur");

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField pseudo;
    @FXML
    private JFXComboBox role;
      @FXML
    private JFXComboBox sexe;
    @FXML
    private TextField addresse;
    @FXML
    private TextField numtel;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXPasswordField password2;
    @FXML
    private TextField email;
    @FXML
    private JFXDatePicker date;

    @FXML
    private Button add;
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
        role.setItems(roles);
             sexe.setItems(sexxe);
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
    private void addUser(ActionEvent event) throws NoSuchAlgorithmException {
        try {

            /// SAUVEGARDE DANS LA BASE
            String nomm = nom.getText();
            String prenomm= prenom.getText();
            String rolee = (String) role.getValue();
            String adresses = addresse.getText();
            String emaill = email.getText();
            String numtell = numtel.getText();
            String passwordd = password.getText();
          String passwordd2 = password2.getText();

            String sexee = (String) sexe.getValue();
            String pseudoo = pseudo.getText();
            String datee = (date.getValue() != null ? date.getValue().toString() : "");
            String a="";
            String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(emaill);


if(rolee=="Vendeur")
  a =  "[\"ROLE_VENDEUR\"]";

if(rolee=="Client")
  a =  "[\"ROLE_USER\"]";

      if(nomm.equals("")){
                JOptionPane.showMessageDialog(null,"veuillez remplir le champs du nom","Message",JOptionPane.WARNING_MESSAGE);
            } else {
          if(prenomm.equals("")){
                JOptionPane.showMessageDialog(null,"veuillez remplir le champs de prenom","Message",JOptionPane.WARNING_MESSAGE);
            } else {
              if(numtell.length()!=8){
                JOptionPane.showMessageDialog(null,"le numero doit contenir 8 chiffre","Message",JOptionPane.WARNING_MESSAGE);
            } else {
                 try{ int numerotel = Integer.parseInt(numtell); 
                 }catch(NumberFormatException ex){
                                  JOptionPane.showMessageDialog(null, "le numero de telephone doit contenir seulement des chifftres","Message",JOptionPane.WARNING_MESSAGE);
                 }
                  if(!controler.matches()){
                JOptionPane.showMessageDialog(null,"email invalid","Message",JOptionPane.WARNING_MESSAGE);
            } else {
                      if(passwordd.length()<8){
                JOptionPane.showMessageDialog(null,"le mot de passe doit contenir un minimum de 8 chiffre","Message",JOptionPane.WARNING_MESSAGE);
            } else {
                              if(!passwordd.equals(passwordd2)){
                JOptionPane.showMessageDialog(null,"Les mot de passe n'est sont pas identiques","Message",JOptionPane.WARNING_MESSAGE);
            } else {
                                    if(adresses.equals("")){
                JOptionPane.showMessageDialog(null,"veuillez remplir le champs d'adresse","Message",JOptionPane.WARNING_MESSAGE);
            } else {
                                                                if(datee.equals("")){
                JOptionPane.showMessageDialog(null,"veuillez indiquer votre date de naissance","Message",JOptionPane.WARNING_MESSAGE);
            } else {
      MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(passwordd.getBytes());
    byte[] digest = md.digest();
    String myHash = DatatypeConverter
      .printHexBinary(digest).toUpperCase();
          Utilisateur r = new Utilisateur(0, parseInt(numtell),0,a,"32222","2022-03-24",datee,path,path,emaill,myHash,nomm,prenomm,adresses,sexee,pseudoo);

            ServiceUser su = new ServiceUser();
               su.ajouter(r);
            if (selectedFile == null) {
                System.out.print("walah lani khadem :// ");
            }

            if (selectedFile != null) {
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
            Parent root2 = loader.load();

            nom.getScene().setRoot(root2);
            
}}}}}}}}
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void setAfficheId(String valeur) {
        this.nom.setText(valeur);
    }

}
