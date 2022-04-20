package zvendelivery.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import zvendelivery.entites.Produits;
import zvendelivery.entites.Restaurant;
import zvendelivery.utils.DataSource;
/**
 *
 * @author remo
 */
public class ServiceRestaurant implements IService<Restaurant> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
    public static ServiceRestaurant instance= null;

    public ServiceRestaurant() {
        conn = DataSource.getInstance().getCnx();
    }
 public static ServiceRestaurant getInstance(){
        
        if (instance == null)
            instance = new ServiceRestaurant();
        return instance ;
    }
    @Override
    public void ajouter(Restaurant r)  {
        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());


        String req = " INSERT INTO `restaurant` (`user_id`, `nom_restaurant`, `image_restaurant`, `nom_image`, `categorie_restaurant`, `addresse`, `cite`, `code_postal`, `created_at`, `heure_ouverture`, `heure_fermeture`, `status`, `description`, `gouvernorat`) VALUE ('" + r.getIdUser() + "','" + r.getNom() + "','" + r.getImageRestaurant() + "','" + r.getNomImage() + "','" + r.getCategorieRestaurant() + "','" + r.getAddresse() + "','" + r.getCite() + "','" + r.getCodePostal() + "','" + date_sql+ "','" + r.getHeureOuverture() + "','" + r.getHeureFermeture() + "','" + r.getStatus() + "','" + r.getDescription() + "','" + r.getGouvernorat() + "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Restaurant créée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifer(Restaurant restaurant) {
        String req = "UPDATE `restaurant` SET  nom_restaurant = ?, image_restaurant = ?, nom_image = ?, categorie_restaurant = ?, addresse = ? , cite = ?, code_postal = ? , heure_ouverture = ? , heure_fermeture = ?, description = ? , gouvernorat = ? WHERE id = ? ";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1, restaurant.getNom());
            pste.setString(2, restaurant.getImageRestaurant());
            pste.setString(3, restaurant.getNomImage());
            pste.setString(4, restaurant.getCategorieRestaurant());

            pste.setString(5, restaurant.getAddresse());
            pste.setString(6, restaurant.getCite());
            pste.setInt(7, restaurant.getCodePostal());
            pste.setString(8, restaurant.getHeureOuverture());
            pste.setString(9, restaurant.getHeureFermeture());
            pste.setString(10, restaurant.getDescription());
            pste.setString(11, restaurant.getGouvernorat());



            pste.setInt(12, restaurant.getId());
            System.out.println(restaurant.getId());
            pste.executeUpdate();
            System.out.println("Restaurant modifiée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    @Override
    public void supprimer(int id) {
        String req = "DELETE FROM `restaurant` WHERE id = ?";
        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1, id);
            pste.executeUpdate();
            System.out.println("Restaurant supprimée");
        }    catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public List<Restaurant> afficher() {
        List<Restaurant> restaurants = new ArrayList<>();
        String req = "SELECT * FROM `restaurant`";

        try {
         //   pste = conn.prepareStatement(req);
           // ResultSet rs = pste.executeQuery();

            ste = conn.createStatement();

           ResultSet rs = ste.executeQuery(req);

            while(rs.next()){
                Restaurant r = new Restaurant();
                r.setId(rs.getInt("restaurant.id"));
                r.setIdUser(rs.getInt("user_ID"));
                r.setNom(rs.getString("nom_restaurant"));
                r.setGouvernorat(rs.getString("gouvernorat"));

                r.setDescription(rs.getString("description"));
                r.setNomImage(rs.getString("nom_image"));
                r.setCategorieRestaurant(rs.getString("categorie_restaurant"));
                r.setAddresse(rs.getString("addresse"));
                r.setCite(rs.getString("cite"));
                r.setCodePostal(rs.getInt("code_postal"));
                r.setHeureOuverture(rs.getString("heure_ouverture"));
                r.setHeureFermeture(rs.getString("heure_fermeture"));
                r.setStatus(rs.getString("status"));
                r.setCreatedAt(rs.getString("created_at"));
              //  r.setImageRestaurant("file:C:\\wamp64\\www\\WeDev_Zvenn\\public\\uploads" + rs.getString(14));
                  r.setImageRestaurant("file:C:\\wamp64\\www\\WeDev_Zvenn\\public\\uploads\\" + rs.getString(5) );


       //    System.out.println("mmm");


                restaurants.add(r);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }

        return restaurants;
    }
    @Override
    public List<Produits> afficherbyrestaurant(Restaurant r) {
        List<Produits> produits = new ArrayList<>();
        String req = "SELECT * FROM `produits` WHERE restaurant_id = "+r.getId()+"";

        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();

            ste = conn.createStatement();
            ResultSet rs = ste.executeQuery(req);

            while(rs.next()){
                Produits p = new Produits();
                //   p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom_produit"));
                p.setDescriptionProduit(rs.getString("description_produit"));
                p.setPrix(rs.getFloat("prix_produit"));

                produits.add(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceProduit.class.getName()).log(Level.SEVERE, null, ex);
        }

        return produits;
    }



}
