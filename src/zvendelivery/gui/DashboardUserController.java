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
import javafx.scene.image.Image;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Observable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import jdk.nashorn.internal.objects.annotations.Property;
import zvendelivery.utils.DataSource;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class DashboardUserController implements Initializable {
  @FXML
    private Button button;
    @FXML
    private Label nomuser;
    @FXML
    private TableView<Utilisateur> usertable;
    @FXML
    private TableColumn<Utilisateur, Integer> idCol;
    @FXML
    private TableColumn<Utilisateur, String> nomCol;
    @FXML
    private TableColumn<Utilisateur, String> prenomCol;
    @FXML
    private TableColumn<Utilisateur, String> pseudoCol;
    @FXML
    private TableColumn<Utilisateur, String> emailCol;
    @FXML
    private TableColumn<Utilisateur, String> editCol;
     @FXML
    private Button profilebtn;
     @FXML
    private ImageView Image;
    javafx.scene.image.Image profile;
            
    String query = null;
    Connection connection = null ;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Utilisateur user = null ;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    nomuser.setText("Hello "+SessionManager.getPrenom()+" "+SessionManager.getNom());
  this.profile = new javafx.scene.image.Image("file:/C:/Users/HP/Desktop/zvendelivery/src/zvendelivery/img/"+SessionManager.getNomImage());
         Image.setImage(profile);
  // TODO
    idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomCol.setCellValueFactory(new PropertyValueFactory<>("nom"));
        prenomCol.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        pseudoCol.setCellValueFactory(new PropertyValueFactory<>("pseudo"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
System.out.println();
         Callback<TableColumn<Utilisateur, String>, TableCell<Utilisateur, String>> cellFoctory;
        cellFoctory = (TableColumn<Utilisateur, String> param) -> {
            // make cell containing buttons
            final TableCell<Utilisateur, String> cell = new TableCell<Utilisateur, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if ( empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        Utilisateur auxPerson = getTableView().getItems().get(getIndex());
                   
                       
                            FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                            deleteIcon.setStyle(
                                    " -fx-cursor: hand ;"
                                            + "-glyph-size:28px;"
                                            + "-fx-fill:#ff1744;"
                            );
                                  editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                                  
                    editIcon.setOnMouseClicked((MouseEvent event) -> {
                              try {
                                    user= usertable.getSelectionModel().getSelectedItem();
                                    ServiceUser su = new ServiceUser();
                                    su.verifieruser(user.getId());
                                    
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardUser.fxml"));
                                    Parent root2 = loader.load();
                                    
                                    button.getScene().setRoot(root2);
                                } catch (IOException ex) {
                                    Logger.getLogger(DashboardUserController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                        });
                                  
                           
                            deleteIcon.setOnMouseClicked((MouseEvent event) -> {               
                                try {
                                    user= usertable.getSelectionModel().getSelectedItem();
                                    ServiceUser su = new ServiceUser();
                                    su.bannir(user.getId());
                                    
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("DashboardUser.fxml"));
                                    Parent root2 = loader.load();
                                    
                                    button.getScene().setRoot(root2);
                                } catch (IOException ex) {
                                    Logger.getLogger(DashboardUserController.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                
                               
                            });

                                 if (auxPerson.getEtat()==0) {
                    
                            HBox managebtn = new HBox(deleteIcon);
                            managebtn.setStyle("-fx-alignment:center");
                            HBox.setMargin(deleteIcon, new Insets(2, 2, 2, 3));
                                setGraphic(managebtn);
                                                       
                            
                            setText(null);
                                 }
                                 else{
                                         HBox managebtn = new HBox(editIcon);
                        managebtn.setStyle("-fx-alignment:center");
                                                    HBox.setMargin(editIcon, new Insets(2, 2, 2, 3));
                           setGraphic(managebtn);
                                                   
                            setText(null);
                               }  
                        }}
                

            };

            return cell;
        };
        
        
     ServiceUser rs = new ServiceUser();
        ObservableList<Utilisateur>  UserList  =rs.afficher();
         editCol.setCellFactory(cellFoctory);

        usertable.setItems(UserList);      
    
    }    
    
        @FXML
    private void profileopen(MouseEvent event) throws IOException {
          FXMLLoader loader = new FXMLLoader(getClass().getResource("ProfileUser.fxml"));
            Parent root2 = loader.load();

            profilebtn.getScene().setRoot(root2);

    }
    
    
    





}
