/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import entities.Commande;
import static gui.AjouterCouponController.ajoutAlertFailControl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import services.CommandeService;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class AjouterCommandeController implements Initializable {

    @FXML
    private TextField adresse;
    @FXML
    private TextField prix;
    @FXML
    private ComboBox combo;
    @FXML
    private TextField renseignement;

   
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<String> liste =FXCollections.observableArrayList("Livraison à domicile", "Retrait en magasin");
        combo.setItems(liste);
    }    
    
    @FXML
    private void addCommande(ActionEvent event) {
        
         if(adresse.getText().isEmpty()  || combo.getSelectionModel().getSelectedItem().toString().isEmpty()){
            ajoutAlertFailControl();
        } 
         else{
            try{
                CommandeService cp= new CommandeService();
            String strA=adresse.getText();
            String strM=combo.getSelectionModel().getSelectedItem().toString();
            String strP=prix.getText();
            String strR=renseignement.getText();
            Float prix=Float.valueOf(strP);
            Commande cmd = new Commande(strA,strM,prix,strR,0);
            CommandeService c = new CommandeService();
            c.ajouterCommande(cmd);
        }
        
        catch(NumberFormatException ex){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Alert");
            alert.setHeaderText(null);
            alert.setContentText("YOOO !");
            alert.showAndWait();
        }
        }
    }
    
}
