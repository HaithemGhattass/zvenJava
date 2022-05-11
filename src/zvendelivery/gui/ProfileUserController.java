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
import javafx.scene.control.*;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.image.Image;

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
    private Label emaillabel;
    @FXML
    private Label numerolabel;
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
    private Button logoutbtn;
    @FXML
    private Button add;
    @FXML
    private Button supbtn;
    @FXML
    private Button phone;
     @FXML
    private Button emailbtn;
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
                 this.profile = new javafx.scene.image.Image("file:/C:/Users/HP/Desktop/zvendelivery/src/zvendelivery/img/"+SessionManager.getNomImage());
             Image.setImage(profile);
            if(SessionManager.getIs_verified_phone()==1){
            phone.setVisible(false);
            numerolabel.setVisible(true);
          
            }
                if(SessionManager.getIs_verified()==1){
            emailbtn.setVisible(false);
            emaillabel.setVisible(true);
          
            }
           
         

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
                                                               
             int numerotell = Integer.parseInt(numtell);
                  
          Utilisateur r = new Utilisateur(SessionManager.getId(),emaill, nomm, prenomm,pseudoo,numerotell,adresses) ;

            ServiceUser su = new ServiceUser();
               su.modifer(r);
                    SessionManager.setEmail(emaill);
                    SessionManager.setAdresse(adresses);
                    SessionManager.setNom(nomm);
                    SessionManager.setPrenom(prenomm);
                    SessionManager.setNumtel(parseInt(numtell));
                    SessionManager.setUserName(pseudoo);
         

            //// REDIRECTION
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileUser.fxml"));
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
              @FXML
    private void dashboardopen(MouseEvent event) throws IOException {
           ServiceUser rs = new ServiceUser();
     rs.supprimer(SessionManager.getId());
          FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardUser.fxml"));
            Parent root2 = loader.load();
  
            supbtn.getScene().setRoot(root2);

    }
                  @FXML
    private void logout(MouseEvent event) throws IOException {
         SessionManager.setActif(false);
          FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginUser.fxml"));
            Parent root2 = loader.load();
  
            supbtn.getScene().setRoot(root2);

    }
               @FXML
    private void phoneopen(MouseEvent event) throws IOException {
         int min = 10000;
      int max = 99999;
        
      //Generate random int value from 50 to 100 
      int random_phonecode = (int)Math.floor(Math.random()*(max-min+1)+min);
         String codeverif=Integer.toString(random_phonecode);

           ServiceUser rs = new ServiceUser();              
           rs.modiferPhonecode(codeverif,SessionManager.getId());
           SessionManager.setPhoneCode(codeverif);
    rs.sendSMS((int) SessionManager.getNumtel(),"Voici votre code de verification: "+codeverif);
          FXMLLoader loader = new FXMLLoader(getClass().getResource("PhoneUser.fxml"));
            Parent root2 = loader.load();
  
            phone.getScene().setRoot(root2);

    }
     @FXML
    private void emailsend(MouseEvent event) throws IOException {

         String to = "yassine.ghoul@esprit.tn";
        String from = "projetbelevedere@gmail.com";
        String host = "smtp.gmail.com";
        final String username = "projetbelevedere@gmail.com";
        final String password = "belevedere123";
        //setup mail server


        Properties props = System.getProperties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try{

            //create mail
                 int min = 10000;
      int max = 99999;
        
      //Generate random int value from 50 to 100 
      int random_phonecode = (int)Math.floor(Math.random()*(max-min+1)+min);
         String codeverif=Integer.toString(random_phonecode);

           ServiceUser rs = new ServiceUser();              
           rs.modiferPhonecode(codeverif,SessionManager.getId());
           SessionManager.setPhoneCode(codeverif);
            MimeMessage m = new MimeMessage(session);
            m.setFrom(new InternetAddress(from));
            m.addRecipient(MimeMessage.RecipientType.TO, new InternetAddress(to));
            m.setSubject("Verification email");
            m.setText("Voici votre code de verification : "+SessionManager.getPhoneCode());

            //send mail

            Transport.send(m);
           
            System.out.println("Message sent!");

        }   catch (MessagingException e){
            e.printStackTrace();
        }
          FXMLLoader loader = new FXMLLoader(getClass().getResource("EmailUser.fxml"));
            Parent root2 = loader.load();
  
            emailbtn.getScene().setRoot(root2);

    }
}
