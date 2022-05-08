/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import bad_words_filter.ProfanityFilter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.apache.commons.io.FileUtils;
import org.controlsfx.control.Notifications;
import zvendelivery.entites.Reclamation;
import zvendelivery.entites.Reply;
import zvendelivery.entites.SessionManager;
import zvendelivery.services.ServiceReclamation;
import zvendelivery.services.ServiceReply;

/**
 * FXML Controller class
 *
 * @author mtar
 */
public class AjouterReplyController implements Initializable {

    @FXML
    private Label rec;
    @FXML
    private VBox vbox;
    @FXML
    private VBox vboxx;
    @FXML
    private TextField titre;
    @FXML
    private ImageView titreCheckMark;
    @FXML
    private TextArea description;
    @FXML
    private ImageView DescCheckMark;
    @FXML
    private Button add;
     Notifications n;
    String erreur;
    @FXML
    private Slider idReclamation;
    @FXML
    private Button annuler;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void ajouterReclamation(ActionEvent event) {
         try {
            
ProfanityFilter filter = new ProfanityFilter();
filter.buildDictionaryTree("C:\\wamp64\\www\\zvendelivery2\\zv\\src\\zvendelivery\\img\\badwords_en_US.txt");
            /// SAUVEGARDE DANS LA BASE
           
            int residres = (int) idReclamation.getValue();
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
               
               
              String result = filter.filterBadWords(resdescription);
              String resultt = filter.filterBadWords(restitre);
              
               
               
            Reply r = new Reply(SessionManager.getId(), resultt, result,residres);
            Reclamation rr = new Reclamation(residres,"traité",SessionManager.getNom());
            new animatefx.animation.Shake(titre).play();
            ServiceReply sr = new ServiceReply(); 
            ServiceReclamation recc = new ServiceReclamation();
            if(SessionManager.getRole().contains("ROLE_USER"))
            { System.out.print(sr);
             sr.ajouter(r);
             
             FXMLLoader loader = new FXMLLoader(getClass().getResource("ListRestaurant.fxml"));
            Parent root2 = loader.load();
            

            titre.getScene().setRoot(root2);
            }if(SessionManager.getRole().contains("ROLE_ADMIN"))
            {
              sr.ajouter(r);
              recc.modifieretat(rr);
             // System.out.print(sr);
             
             FXMLLoader loader = new FXMLLoader(getClass().getResource("MyRestaurants.fxml"));
            Parent root2 = loader.load();

            titre.getScene().setRoot(root2);
            
            }
            
          
             System.out.println("ajouté avec succes");
            n = Notifications.create()
                    .title("Succes")
                    .text("Reply envoyé avec succes")
                    .graphic(null)
                    .position(Pos.TOP_CENTER)
                    .hideAfter(Duration.seconds(3));
            n.showInformation();
            
            //// REDIRECTION
            
           }

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
     public void setAffichetitre(String valeur) {
        this.titre.setText(valeur);
    }
      public void setAffichedescription(String valeur) {
        this.description.setText(valeur);}
   
            public void setAffichere(int valeur) {
        this.idReclamation.setValue(valeur);
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

// Function takes two parameter
private String censor(String text,String word)
                     
{
 
    // Break down sentence by ' ' spaces
    // and store each individual word in
    // a different list
    String[] word_list = text.split("\\s+");
 
    // A new string to store the result
    String result = "";
 
    // Creating the censor which is an asterisks
    // "*" text of the length of censor word
    String stars = "";
    for (int i = 0; i < word.length(); i++)
        stars += '*';
 
    // Iterating through our list
    // of extracted words
    int index = 0;
    for (String i : word_list)
    {
        if (i.compareTo(word) == 0)
 
            // changing the censored word to
            // created asterisks censor
            word_list[index] = stars;
        index++;
    }
 
    // join the words
    for (String i : word_list)
        result += i + ' ';
 
    return result;
}
 
// Driver code

    @FXML
    private void annuler(ActionEvent event) {
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
