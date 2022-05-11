/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;


import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import javax.xml.bind.DatatypeConverter;
import zvendelivery.entites.SessionManager;
import zvendelivery.entites.Utilisateur;
import zvendelivery.services.ServiceUser;
import t2s.son.LecteurTexte;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class LoginUserController implements Initializable {

 


    @FXML
    private JFXPasswordField password2;
    @FXML
    private TextField email2;
    @FXML
    private TextField em;
    @FXML
    private TextField num;
    @FXML
    private Button add;
      @FXML
    private Button recuperer;
      @FXML
    private Button google;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxx;
     @FXML
    private VBox numtel;
  
    @Override
    public void initialize(URL location, ResourceBundle resources) {
               LecteurTexte lecteur = new LecteurTexte("bonjour");
        lecteur.playAll();
     
    }

    @FXML
    private void login(ActionEvent event) throws SQLException, IOException, NoSuchAlgorithmException {
         
        String emaill = email2.getText();
            String passwordd = password2.getText();
         
                ServiceUser su = new ServiceUser();
                      MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(passwordd.getBytes());
    byte[] digest = md.digest();
    String myHash = DatatypeConverter
      .printHexBinary(digest).toUpperCase();
                Utilisateur a=  su.login(emaill,myHash );
                if(emaill.equals("")){
                JOptionPane.showMessageDialog(null,"veuillez remplir le champs de email","Message",JOptionPane.WARNING_MESSAGE);
            }else{
             if(passwordd.equals("")){
                JOptionPane.showMessageDialog(null,"veuillez remplir le champs de mot de passe","Message",JOptionPane.WARNING_MESSAGE);
            } else {
                if(a.getId()==0)
                JOptionPane.showMessageDialog(null,"nexiste pas","Message",JOptionPane.WARNING_MESSAGE);
                else{
                       if(a.getEtat()==1)
                JOptionPane.showMessageDialog(null,"Vous etes banni","Message",JOptionPane.WARNING_MESSAGE);
                else{
                     JOptionPane.showMessageDialog(null,"existe ","Message",JOptionPane.WARNING_MESSAGE);
                    SessionManager.setActif(true);
                    SessionManager.setEmail(emaill);
                    SessionManager.setId(a.getId());
                    SessionManager.setAdresse(a.getAdresse());
                    SessionManager.setNom(a.getNom());
                    SessionManager.setPrenom(a.getPrenom());
                    SessionManager.setNumtel(a.getNumtel());
                    SessionManager.setUserName(a.getPseudo());
                    SessionManager.setNomImage(a.getNomimage());
                    SessionManager.setPhoneCode(a.getPhonecode());
                    SessionManager.setIs_verified_phone(a.getIsVerified());
                     SessionManager.setIs_verified(a.getIsVerifiedemail());
                              
               LecteurTexte lecteur = new LecteurTexte("Bienvenue"+a.getNom()+" "+a.getPrenom());
        lecteur.playAll();
  
                   FXMLLoader loader = new FXMLLoader(getClass().getResource("MyRestaurants.fxml"));
            Parent root2 = loader.load();
            email2.getScene().setRoot(root2);
            

                }}}}
 
    }


    /**
     * Initializes the controller class.
     */


      @FXML
    private void newview(MouseEvent event) throws IOException {
        try{  FXMLLoader loader = new FXMLLoader(getClass().getResource("WebView.fxml"));
            Parent root2 = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Google");
            stage.setScene(new Scene(root2));
            stage.show();
        }
        catch (Exception e){
            System.out.println("error");
        }

    }
     @FXML
    private void phoneopen(MouseEvent event) throws IOException {
        try{ 
           if(numtel.isVisible())
           numtel.setVisible(false);
           else
             numtel.setVisible(true);  
        }
        catch (Exception e){
            System.out.println("error");
        }

    }
    
    @FXML
    private void resetpassword(ActionEvent event) throws SQLException, IOException, NoSuchAlgorithmException {
         
        String emaill = em.getText();
            String numeroo = num.getText();
         
                ServiceUser su = new ServiceUser();
    
                Utilisateur a=  su.resetpassword(emaill,numeroo);
                if(emaill.equals("")){
                JOptionPane.showMessageDialog(null,"veuillez remplir le champs de email","Message",JOptionPane.WARNING_MESSAGE);
            }else{
             if(numeroo.equals("")){
                JOptionPane.showMessageDialog(null,"veuillez remplir le champs de numero","Message",JOptionPane.WARNING_MESSAGE);
            } else {
                if(a.getId()==0)
                JOptionPane.showMessageDialog(null,"nexiste pas","Message",JOptionPane.WARNING_MESSAGE);
                else{
                     int min = 10000000;
      int max = 99999999;
        
      //Generate random int value from 50 to 100 
      int random_password = (int)Math.floor(Math.random()*(max-min+1)+min);
         String passwordd=Integer.toString(random_password);
                     MessageDigest md = MessageDigest.getInstance("MD5");
    md.update(passwordd.getBytes());
    byte[] digest = md.digest();
    String myHash = DatatypeConverter
      .printHexBinary(digest).toUpperCase();
                    su.modiferpassword(myHash, a.getId());
                    su.sendSMS(Integer.parseInt(numeroo) ,"Votre nv mdp est : "+ passwordd);
                     JOptionPane.showMessageDialog(null,"un sms a ete envoyer vers votre telephone contenant un nv mdp ","Message",JOptionPane.WARNING_MESSAGE);
               
                              
                 
               
            

                }}}

 
    }
}
