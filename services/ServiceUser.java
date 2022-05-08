package zvendelivery.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zvendelivery.entites.Message;
import zvendelivery.entites.Produits;
import zvendelivery.entites.SessionManager;


import zvendelivery.entites.Utilisateur;
import zvendelivery.utils.DataSource;
/**
 *
 * @author remo
 */
public class ServiceUser implements IService<Utilisateur> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
        private ResultSet rs;

    public static ServiceUser instance= null;
    public ServiceUser() {
        conn = DataSource.getInstance().getCnx();
    }
 public static ServiceUser getInstance(){
        
        if (instance == null)
            instance = new ServiceUser();
        return instance ;
    }
    @Override
    public void ajouter(Utilisateur t) {
       java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());


        String req = " INSERT INTO `user` (`email`, `roles`, `password`, `nom`, `prenom`, `date_creation`, `image`, `nom_image`, `adresse`, `num_tel`, `pseudo`, `date_naissance`, `sexe`, `etat`, `is_verified`, `phonecode`, `is_verified_phone`) VALUE "
                + "('" + t.getEmail() + "','" + t.getRole() + "','" + t.getPassowrd() + "','" + t.getNom() + "','" + t.getPrenom() + "','" + date_sql + "','" + t.getImage() + "','" + t.getNomimage() + "','" +t.getAdresse() + "','" + t.getNumtel() + "','" + t.getPseudo() + "','" + t.getDateNaissance() + "','" + t.getSexe() + "','" + t.getEtat() + "','" + t.getIsVerified() + "','" + t.getPhonecode() + "','" + t.getEtat() + "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Restaurant créée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifer(Utilisateur t) {

 String req;
        req = "UPDATE `user` SET email = ?, pseudo = ?, nom = ?, prenom = ?, adresse = ? , num_tel = ? WHERE id = ? ";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1, t.getEmail());
            pste.setString(2, t.getPseudo());
            pste.setString(3, t.getNom());
             pste.setString(4, t.getPrenom());
             pste.setString(5, t.getAdresse());
            pste.setInt(6, t.getNumtel());
            pste.setInt(7, t.getId());
          
            pste.executeUpdate();
            System.out.println("User modifiée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     public void modiferpassword(String mdp,int id) {

 String req;
        req = "UPDATE `user` SET password = ? WHERE id = ? ";
        try {
            pste = conn.prepareStatement(req);
            pste.setString(1, mdp);
          
            pste.setInt(2,id);
          
            pste.executeUpdate();
            System.out.println("User modifiée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 public Utilisateur login(String email,String password) throws SQLException {

String reqq = "SELECT * FROM `user` WHERE email ='"+email+"' AND password = '"+password+"'";
  Utilisateur p = new Utilisateur();
          try {
            ste = conn.createStatement();
            rs = ste.executeQuery(reqq);
            while (rs.next()) {
                  p = new Utilisateur(rs.getString("email"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("id"),rs.getString("adresse"),rs.getInt("num_tel"),rs.getString("password"),rs.getString("pseudo"),rs.getString("roles"));
            }          

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return p;


 }
    @Override
    public void supprimer(int id) {
      String req1 = "DELETE FROM `messages` WHERE sender_id = ?";
        try {
            pste = conn.prepareStatement(req1);
            pste.setInt(1, id);
            pste.executeUpdate();
            System.out.println("Message supprimée");
        }    catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }   
         String req2 = "DELETE FROM `messages` WHERE recipient_id = ?";
        try {
            pste = conn.prepareStatement(req2);
            pste.setInt(1, id);
            pste.executeUpdate();
            System.out.println("Message supprimée");
        }    catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
  String req = "DELETE FROM `user` WHERE id = ?";
        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1, id);
            pste.executeUpdate();
            System.out.println("User supprimée");
        }    catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Utilisateur> afficher() {

List<Utilisateur> utilisateurs = new ArrayList<>();

        String req = "SELECT * FROM `user`";

        try {
         //   pste = conn.prepareStatement(req);
           // ResultSet rs = pste.executeQuery();

            ste = conn.createStatement();

           ResultSet rs = ste.executeQuery(req);

            while(rs.next()){
                Utilisateur r = new Utilisateur();
                r.setId(rs.getInt("id"));
                r.setPseudo(rs.getString("pseudo"));
                r.setNomimage("file:C:\\wamp64\\www\\denyaberda\\zvenproject\\public\\uploads\\" + rs.getString(9));
                r.setEmail("email");

              utilisateurs.add(r);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }

        return utilisateurs;
    }

    @Override
    public List<Produits> afficherbyrestaurant(Utilisateur t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
