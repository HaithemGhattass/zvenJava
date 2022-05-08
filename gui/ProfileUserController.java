/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;
import java.util.regex.*; 
import java.io.File;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javax.swing.JOptionPane;
import zvendelivery.entites.SessionManager;
import zvendelivery.entites.Utilisateur;
import zvendelivery.services.ServiceUser;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class ProfileUserController implements Initializable {

    private String path;
    File selectedFile;


    @FXML
    private TextField nom;
    @FXML
    private Label nomuser;
    @FXML
    private TextField prenom;
    @FXML
    private TextField pseudo;
 

    @FXML
    private TextField addresse;
    @FXML
    private TextField numtel;
 
    @FXML
    private TextField email;
    

 @FXML
    private Button photobtn;
  @FXML
    private Button mdpbtn;
    @FXML
    private Button messagebtn;
    @FXML
    private Button add;
    @FXML
    private Button supbtn;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxx;
    @FXML
    private ImageView Image;
    javafx.scene.image.Image profile;

 


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
           nom.setText(SessionManager.getNom());
            prenom.setText(SessionManager.getPrenom());
            pseudo.setText(SessionManager.getUserName());
            addresse.setText(SessionManager.getAdresse());
             numtel.setText(Double.toString(SessionManager.getNumtel()));
            email.setText(SessionManager.getEmail());
            nomuser.setText("Hello "+SessionManager.getPrenom()+" "+SessionManager.getNom());
         

      // Dropping over surface
   
    }

    @FXML
    private void modifuser(ActionEvent event) {
        try {

            /// SAUVEGARDE DANS LA BASE
            String nomm = nom.getText();
            String prenomm= prenom.getText();
            String adresses = addresse.getText();
            String emaill = email.getText();
            String numtell = numtel.getText();
        
    this.profile = new javafx.scene.image.Image("C:\\Users\\HP\\Desktop\\zvendelivery\\src\\zvendelivery\\gui\\avatar4.jpg");
             Image.setImage(profile);
            String pseudoo = pseudo.getText();
            String a="";
            String masque = "^[a-zA-Z]+[a-zA-Z0-9\\._-]*[a-zA-Z0-9]@[a-zA-Z]+"
                        + "[a-zA-Z0-9\\._-]*[a-zA-Z0-9]+\\.[a-zA-Z]{2,4}$";
            Pattern pattern = Pattern.compile(masque);
Matcher controler = pattern.matcher(emaill);




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
                     
                                    if(adresses.equals("")){
                JOptionPane.showMessageDialog(null,"veuillez remplir le champs d'adresse","Message",JOptionPane.WARNING_MESSAGE);
            } else {
                                                               
                                  
          Utilisateur r = new Utilisateur(24,0, parseInt(numtell),0,a,"32222","2022-03-24","2022-03-24","image.jpg","image.jpg",emaill,"aaaa",nomm,prenomm,adresses,"femme",pseudoo);

            ServiceUser su = new ServiceUser();
               su.modifer(r);
                    SessionManager.setEmail(emaill);
                    SessionManager.setAdresse(adresses);
                    SessionManager.setNom(nomm);
                    SessionManager.setPrenom(prenomm);
                    SessionManager.setNumtel(parseInt(numtell));
                    SessionManager.setUserName(pseudoo);
         

            //// REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass().getResource("myRestaurants.fxml"));
            Parent root2 = loader.load();

            nom.getScene().setRoot(root2);
            
}}}}}
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void setAfficheId(String valeur) {
        this.nom.setText(valeur);
    }
 @FXML
    private void photoopen(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("ImageUser.fxml"));
            Parent root2 = loader.load();

           photobtn.getScene().setRoot(root2);

    }
     @FXML
    private void mdpopen(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("PasswordUser.fxml"));
            Parent root2 = loader.load();

            mdpbtn.getScene().setRoot(root2);

    }
         @FXML
    private void messageopen(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("Message.fxml"));
            Parent root2 = loader.load();

            messagebtn.getScene().setRoot(root2);

    }
             @FXML
    private void supopen(MouseEvent event) throws IOException {
           ServiceUser rs = new ServiceUser();
     rs.supprimer(SessionManager.getId());
          FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
            Parent root2 = loader.load();
  
            supbtn.getScene().setRoot(root2);

    }
}
