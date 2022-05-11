/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import zvendelivery.entites.Produits;
import zvendelivery.services.MyListener;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class ItemController implements Initializable {

    @FXML
    private Label nameLabel;
    @FXML
    private Label priceLable;
    @FXML
    private ImageView img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void click(MouseEvent event) {
                myListener.onClickListener(produits);

    }
      private Produits produits;
    private MyListener myListener;

    public void setData(Produits produits, MyListener myListener) {
        this.produits = produits;
        this.myListener = myListener;
        nameLabel.setText(produits.getNom());
        priceLable.setText(produits.getPrix()+" dt");
        Image image = new Image(produits.getImageProduit());
        img.setImage(image);
    }
    
}
