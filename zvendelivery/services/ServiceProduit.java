package zvendelivery.services;

import zvendelivery.entites.Produits;
import zvendelivery.entites.Restaurant;
import zvendelivery.utils.DataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static zvendelivery.services.ServiceRestaurant.instance;

public class ServiceProduit implements IService<Produits> {

    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
 public static ServiceProduit instance= null;


    public ServiceProduit() {
        conn = DataSource.getInstance().getCnx();
    }
     public static ServiceProduit getInstance(){
        
        if (instance == null)
            instance = new ServiceProduit();
        return instance ;
    }




    @Override
    public void ajouter(Produits p) {
        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        String req = " INSERT INTO `produits` (`restaurant_id`, `nom_produit`, `description_produit`, `image_produit`, `created_at`, `prix_produit`, `nomimage_produit`) VALUE ('" + p.getId() + "','" + p.getNom() + "','" + p.getDescriptionProduit() + "','" + p.getImageProduit() + "','" + date_sql.toString() + "','" +  p.getPrix() + "','" +  p.getNomImage()  + "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Produit ajouté");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifer(Produits p) {
        String req = "UPDATE `produits` SET  nom_produit = ?, description_produit = ?, prix_produit = ?, nomimage_produit = ? WHERE id = ?";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1, p.getNom());
            pste.setString(2, p.getDescriptionProduit());
            pste.setFloat(3, p.getPrix());
            pste.setString(4, p.getNomImage());
            pste.setInt(5, p.getId());
            pste.executeUpdate();
            System.out.println("Produit modifiée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM `produits` WHERE id = ?";
        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1, id);
            pste.executeUpdate();
            System.out.println("Produit supprimée");
        }    catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public List<Produits> afficher() {
        List<Produits> Produits = new ArrayList<>();
        List<Restaurant> Restaurant = new ArrayList<>();
        String req = "SELECT * FROM `restaurant`,`produits` where restaurant.id = produits.restaurant_id";

        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);


            while(rs.next()){
                Produits p = new Produits();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_produit"));
                p.setDescriptionProduit(rs.getString("description_produit"));
                p.setPrix(rs.getFloat("prix_produit"));
                p.setNomRestaurant(rs.getString("nom_restaurant"));
                Restaurant r = new Restaurant();
                r.setId(rs.getInt("id"));
              //  r.setNom(rs.getString("nomRestaurant"));
               // r.setDescription(rs.getString("descirption"));




                Produits.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Produits;
    }

    public List<Produits> afficherbyrestaurants(Restaurant r) {
         List<Produits> Produits = new ArrayList<>();
        //List<Restaurant> Restaurant = new ArrayList<>();
        String req = "SELECT * FROM `produits` WHERE produits.restaurant_id ="+r.getId();

        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);


            while(rs.next()){
                Produits p = new Produits();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_produit"));
                p.setDescriptionProduit(rs.getString("description_produit"));
                p.setPrix(rs.getFloat("prix_produit"));
                p.setCreatedAt(rs.getString("produits.created_at"));
                p.setRestaurant(r);
                   System.out.print(p.getRestaurant());
               // p.setNomRestaurant(rs.getString("nom_restaurant"));
       //         r.setId(rs.getInt("produits.restaurant_id"));
              //  r.setNom(rs.getString("nomRestaurant"));
               // r.setDescription(rs.getString("descirption"));
            p.setImageProduit("file:C:\\wamp64\\www\\WeDev_Zvenn\\public\\uploads\\" + rs.getString(8) );




                Produits.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

        return Produits;
    }

    @Override
    public List<Produits> afficherbyrestaurant(Produits t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


}
