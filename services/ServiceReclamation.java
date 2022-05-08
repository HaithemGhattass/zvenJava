/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zvendelivery.services;

/**
 *
 * @author mtar
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import zvendelivery.entites.Reclamation;
import zvendelivery.entites.Restaurant;
import zvendelivery.entites.SessionManager;
import zvendelivery.entites.Utilisateur;
import zvendelivery.utils.DataSource;

public class ServiceReclamation implements IServiceReclamation {

    public static ServiceReclamation instance = null;

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }

    private Statement ste;
    private PreparedStatement pste;
    private ObservableList data;

    private Connection conn = DataSource.getInstance().getCnx();

    private String etat = "non traité";

    @Override
    public void ajouter(Reclamation r) {
        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        String req = "INSERT INTO `reclamation` (`restaurant_id`,`user_id`,`titre`,`Description`,`nomimage`,`daterec`,`etat`,`foodqulaite`,`service`,`price`) VALUE ('" + r.getRestaurant_id() + "','" + r.getID_user() + "','" + r.getTitre() + "','" + r.getDescription() + "','" + r.getNomimage() + "','" + date_sql + "','" + etat + "','" + r.getFoodqulaite() + "','" + r.getService() + "','" + r.getPrice() + "')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Reclamation créée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void modifier(Reclamation reclamation) {
        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        String req = "UPDATE `reclamation` SET  titre = ?, description = ?, nomimage = ?, daterec = ?, etat = ? , foodqulaite = ?, service = ? , price = ? WHERE id = ? ";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1, reclamation.getTitre());
            pste.setString(2, reclamation.getDescription());
            pste.setString(3, reclamation.getNomimage());
            pste.setTimestamp(4, date_sql);

            pste.setString(5, "non traité");
            pste.setInt(6, reclamation.getFoodqulaite());
            pste.setInt(7, reclamation.getService());
            pste.setInt(8, reclamation.getPrice());

            pste.setInt(9, reclamation.getId());
            System.out.println(reclamation.getId());
            pste.executeUpdate();
            System.out.println("Reclamation modifiée");
            System.out.println(reclamation.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void supprimer(int ID_Reclamation) {
        String req = "DELETE FROM `reclamation` WHERE id = ?";
        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1, ID_Reclamation);
            pste.executeUpdate();
            System.out.println("Reclamation supprimée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Reclamation> affichers(int rr) {
        List<Reclamation> reclamations = new ArrayList<>();
  //      String req = "SELECT * FROM `reclamation` where  reclamation.restaurant_id=" + rr + ",AND reclamation.user_id=";

              String req ="SELECT * FROM `reclamation` r , `user` u where r.user_id=u.id AND r.restaurant_id="+rr;
        try {
            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();

            ste = conn.createStatement();

            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setID_user(rs.getInt("user_id"));
                r.setTitre(rs.getString("titre"));
                r.setDescription(rs.getString("description"));
                r.setDate(rs.getTimestamp("daterec"));
                r.setNomimage("file:C:\\wamp64\\www\\denyaberda\\zvenproject\\public\\uploads\\" + rs.getString(7));
                r.setFoodqulaite(rs.getInt("foodqulaite"));
                r.setService(rs.getInt("service"));
                r.setPrice(rs.getInt("price"));
                r.setRestaurant_id(rs.getInt("restaurant_id"));
                Utilisateur u = new Utilisateur();
                r.setID_user(rs.getInt("user_id"));
                u.setId(rs.getInt("u.id"));
                u.setNom(rs.getString("u.nom"));
                u.setPseudo(rs.getString("u.pseudo"));
                u.setNomimage("file:C:\\wamp64\\www\\denyaberda\\zvenproject\\public\\uploads\\"+rs.getString("u.nom_image"));
                r.setUser(u);
                System.out.println(r.getUser());
                // rr.setId(rs.getInt("reclamation.restaurant_id"));
                reclamations.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reclamations;
    }

    public List<Reclamation> recherche(int ID_Reclamation) {
        List<Reclamation> reclamations = new ArrayList<>();
        String req = "SELECT * FROM `reclamation` WHERE ID_Reclamation = ?";

        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1, ID_Reclamation);
            ResultSet rs = pste.executeQuery();

            //  ste = conn.createStatement();
            //ResultSet rs = ste.executeQuery(req);
            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("ID_Reclamation"));
                r.setID_user(rs.getInt("ID_User"));
                r.setTitre(rs.getString("Titre_Reclamation"));
                r.setDescription(rs.getString("Description_Reclamation"));

                reclamations.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reclamations;
    }

    @Override
    public List<Reclamation> afficher() {
 List<Reclamation> reclamations = new ArrayList<>();
  //      String req = "SELECT * FROM `reclamation` where  reclamation.restaurant_id=" + rr + ",AND reclamation.user_id=";

              String req ="SELECT * FROM `reclamation` r , `user` u where r.user_id=u.id  ";
        try {
            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();

            ste = conn.createStatement();

            ResultSet rs = ste.executeQuery(req);

            while (rs.next()) {
                Reclamation r = new Reclamation();
                r.setId(rs.getInt("id"));
                r.setID_user(rs.getInt("user_id"));
                r.setTitre(rs.getString("titre"));
                r.setEtat(rs.getString("etat"));
                r.setDescription(rs.getString("description"));
                r.setNom(rs.getString("traitepar"));
                r.setDate(rs.getTimestamp("daterec"));
                r.setNomimage("file:C:\\wamp64\\www\\denyaberda\\zvenproject\\public\\uploads\\" + rs.getString(7));
                r.setFoodqulaite(rs.getInt("foodqulaite"));
                r.setService(rs.getInt("service"));
                r.setPrice(rs.getInt("price"));
                r.setRestaurant_id(rs.getInt("restaurant_id"));
                Utilisateur u = new Utilisateur();
                r.setID_user(rs.getInt("user_id"));
                u.setId(rs.getInt("u.id"));
                u.setNom(rs.getString("u.nom"));
                u.setNomimage("file:C:\\wamp64\\www\\denyaberda\\zvenproject\\public\\uploads\\"+rs.getString("u.nom_image"));
                r.setUser(u);
                System.out.println(r.getUser());
                // rr.setId(rs.getInt("reclamation.restaurant_id"));
                reclamations.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }

        return reclamations;    }

    public void modifieretat(Reclamation reclamation) {
        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        String req = "UPDATE `reclamation` SET   etat = ?, traitepar = ?  WHERE id = ? ";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1, reclamation.getEtat());
            pste.setString(2, SessionManager.getNom());
            pste.setInt(3, reclamation.getId());
            System.out.println(reclamation.getId());
            pste.executeUpdate();
            System.out.println("Reclamation modifiée");
            //System.out.println(reclamation.getId());
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReclamation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void buildData(){
          Connection c ;
          data = FXCollections.observableArrayList();
          try{
          ;
            //SQL FOR SELECTING NATIONALITY OF CUSTOMER
            String SQL = "SELECT AVG(service) from reclamation";
 
            ResultSet rs = conn.createStatement().executeQuery(SQL);
            while(rs.next()){
                //adding data on piechart data
                data.add(new PieChart.Data(rs.getString(2),rs.getDouble(1)));
            }
          }catch(Exception e){
              System.out.println("Error on DB connection");
              return;
          }
    }
 
      

}
