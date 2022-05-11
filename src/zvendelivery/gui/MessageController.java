/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import zvendelivery.entites.Message;
import zvendelivery.entites.Produits;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.MyListener;
import zvendelivery.services.ServiceMessage;
import zvendelivery.services.ServiceProduit;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class MessageController implements Initializable {

      @FXML
    private GridPane grid;
   
    @FXML
    private VBox vbox;
    private Image image;
    private MyListener myListener;
    private Label home;
    @FXML
    private Button envbtn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                ArrayList<Message> lists = (ArrayList<Message>)ServiceMessage.getInstance().afficher();
          int column = 0;
        int row = 1;
       try {
        for (Message p : lists) {

                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("MessageItem.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

               MessageItemController DetailController = fxmlLoader.getController();
                DetailController.setData(p);

                if (column == 2) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));    
         }} catch (IOException e) {
            e.printStackTrace();
        }
    }    

 

 
     
     @FXML
        private void envopen(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("AjoutMessage.fxml"));
            Parent root2 = loader.load();

            envbtn.getScene().setRoot(root2);

    }
}

    

