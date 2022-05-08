/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package zvendelivery.gui;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import zvendelivery.entites.Reclamation;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.ServiceReclamation;
import zvendelivery.services.ServiceRestaurant;

/**
 * FXML Controller class
 *
 * @author haith
 */
public class DashboardController implements Initializable {

    @FXML
    private BorderPane root;
    @FXML
    private Button home;
    @FXML
    private HBox restaurants;
    @FXML
    private HBox users;
    @FXML
    private HBox reclamations;
    @FXML
    private HBox events;
    @FXML
    private HBox products;
    @FXML
    private HBox commands;
    @FXML
    private VBox restaurantshandler;
    @FXML
    private TableView<Reclamation> TabRest;
    @FXML
    private TextField RechercheTextField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
                    ObservableList<Reclamation> Reclamation = FXCollections.observableArrayList();

                 //   ArrayList<Restaurant> list = (ArrayList<Restaurant>) ServiceRestaurant.getInstance().affichermyrestaurants();

        // TODO
        TableColumn<Reclamation, String> vendeurColumn = new TableColumn<>("User");
//        objetColumn.setMinWidth(100);
        vendeurColumn.setCellValueFactory(entry -> new SimpleObjectProperty<>(entry.getValue().getUser().getNom()));
      vendeurColumn.setStyle("-fx-alignment: CENTER;");
        
        
        TableColumn<Reclamation, Integer> NomColumn = new TableColumn<>("objet");
//        idColumn.setMinWidth(100);
        NomColumn.setCellValueFactory(new PropertyValueFactory<>("titre"));
        NomColumn.setStyle("-fx-alignment: CENTER;");

        
        TableColumn<Reclamation, String> descColumn = new TableColumn<>("description");
//        prenomColumn.setMinWidth(100);
        descColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        descColumn.setStyle("-fx-alignment: CENTER;");
        
   
       

     

       

        TableColumn<Reclamation, String> dateCreaColumn = new TableColumn<>("DateCreation");
//        statusColumn.setMinWidth(110);
        dateCreaColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        dateCreaColumn.setStyle("-fx-alignment: CENTER;");

        TableColumn<Reclamation, String> statusColumn = new TableColumn<>("Status");
//        objetColumn.setMinWidth(100);
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("etat"));
        statusColumn.setStyle("-fx-alignment: CENTER;");
        
        

       TableColumn actionCol = new TableColumn("Action");
       TableColumn repondre = new TableColumn("Action");
        actionCol.setCellValueFactory(new PropertyValueFactory<>("DUMMY"));

        Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactory
                = //
                new Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>>() {
            @Override
            public TableCell call(final TableColumn<Reclamation, String> param) {
                final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {

                    final Button btn = new Button("Supprimer");
                    final Button btnn = new Button("modifier");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction(event -> {

//                                Reclamation person = getTableView().getItems().get(getIndex());
////                                System.out.println(person.getId()
//                                        + "   " + person.getNom());
                                if (getTableView().getItems().get(getIndex()) == null) {
                                    Notifications n = Notifications.create()
                                            .title("Erreur")
                                            .text("Choix invalide")
                                            .graphic(null)
                                            .position(Pos.TOP_CENTER)
                                            .hideAfter(Duration.seconds(5));
                                    n.showWarning();
                                } else {
                                    List<Reclamation> listRestaurant = TabRest.getSelectionModel().getSelectedItems();

                                    Reclamation RestaurantSelectione = getTableView().getItems().get(getIndex());
                                    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                                    alert.setTitle("Confirmation Dialog");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Vous voulez vraiment supprimer cette Reclamation");
                                    Optional<ButtonType> action = alert.showAndWait();
                                    if (action.get() == ButtonType.OK) {
                                        new ServiceReclamation().supprimer(RestaurantSelectione.getId());
                                          TabRest.getItems().remove(getTableView().getItems().get(getIndex()) );

//                                        System.out.println("Reclamation supprimée" + reclamationSelectione.getId());

                                    }
                                }
                             
    
   TabRest.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
        TabRest.getSelectionModel().clearSelection();
    }
});

                                

                            });
//                            btn.getStyleClass().removeAll("addBobOk, focus"); 
//                            btn.getStyleClass().add("addBobOk");
//                             btn.setStyle("-fx-background-color: #00ff00");
                            setGraphic(btn);
                            
                            setText(null);
//                            //  System.out.println(item);
                        }
                        btnn.setOnAction(event -> {
                              try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjouterReply.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        
                        
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                        

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                              setGraphic(btnn);

                            });
                    }
                };
                return cell;
            }
        };
         Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>> cellFactory2
                = //
                new Callback<TableColumn<Reclamation, String>, TableCell<Reclamation, String>>() {
            @Override
            public TableCell call(final TableColumn<Reclamation, String> param) {
                final TableCell<Reclamation, String> cell = new TableCell<Reclamation, String>() {

                    final Button btn = new Button("Supprimer");
                    final Button btnn = new Button("modifier");

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                             btnn.setOnAction(event -> {
                              try {
                        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AjouterReply.fxml"));
                        Scene scene = new Scene(fxmlLoader.load());
                        
                        
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(scene);
                        stage.show();

                        

                    } catch (IOException ex) {
                        System.out.println(ex.getMessage());
                    }
                              setGraphic(btnn);
                              setText(null);

                            });
                        }
                       
                    }
                };
                return cell;
            }
        };

        actionCol.setCellFactory(cellFactory);
        repondre.setCellFactory(cellFactory2);
        
        
        ServiceReclamation ServiceReclamation = new ServiceReclamation();
        ArrayList arrayList;

        
        arrayList = (ArrayList) ServiceReclamation.afficher();

        ObservableList observableList = FXCollections.observableArrayList(arrayList);

        TabRest.setItems(observableList);
        TabRest.getColumns().addAll(vendeurColumn ,NomColumn, descColumn, dateCreaColumn, statusColumn,repondre, actionCol);

       
        
        ObservableList data =  TabRest.getItems();
RechercheTextField.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            if (oldValue != null && (newValue.length() < oldValue.length())) {
                TabRest.setItems(data);
            }
            String value = newValue.toLowerCase();
           // ObservableList<Restaurant> Restaurant = FXCollections.observableArrayList();

            long count = TabRest.getColumns().stream().count();
            for (int i = 0; i < TabRest.getItems().size(); i++) {
                for (int j = 0; j < count; j++) {
                    String entry = "" + TabRest.getColumns().get(j).getCellData(i);
                    if (entry.toLowerCase().contains(value)) {
                        Reclamation.add(TabRest.getItems().get(i));
                        break;
                    }
                }
            }
            TabRest.setItems(Reclamation);
        });

    }    
    
}
