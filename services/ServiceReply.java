/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zvendelivery.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import zvendelivery.entites.Reclamation;
import zvendelivery.entites.Reply;
import zvendelivery.entites.Utilisateur;
import zvendelivery.utils.DataSource;

/**
 *
 * @author mtar
 */
public class ServiceReply {
public static ServiceReply instance= null;
      public static ServiceReply getInstance() {
if (instance == null)
            instance = new ServiceReply();
        return instance ; 
      }
      private Statement ste;
    private PreparedStatement pste;


    private Connection conn = DataSource.getInstance().getCnx();
     public void ajouter(Reply r) {
        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
        String req = "INSERT INTO `reply` (`user_id`,`titre`,`Description`,`datereply`,`reclamation_id`) VALUE ('" + r.getUser_id() + "','" + r.getTitre() + "','" + r.getDescription() + "','" + date_sql+ "','" + r.getReclamation_id() +"')";
        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("Reclamation créée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReply.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public void modifier(int ID_Reclamation, Reclamation r) {
        String req = "UPDATE `reclamation` SET user_id = ?, titre = ?, description = ? WHERE id = ?";
        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1, r.getID_user());
            pste.setString(2, r.getTitre());
            pste.setString(3, r.getDescription());
            pste.setInt(4, ID_Reclamation);
            pste.executeUpdate();
            System.out.println("Reclamation modifiée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReply.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void supprimer(int ID_Reclamation) {
        String req = "DELETE FROM `reply` WHERE id = ?";
        try {
            pste = conn.prepareStatement(req);
            pste.setInt(1, ID_Reclamation);
            pste.executeUpdate();
            System.out.println("Reponse supprimée");
        }    catch (SQLException ex) {
            Logger.getLogger(ServiceReply.class.getName()).log(Level.SEVERE, null, ex);
        }
    }




    public List<Reply> afficher(int rr) {
        List<Reply> replies = new ArrayList<>();
        String req = "SELECT * FROM `reply` r ,`user` u where r.user_id=u.id AND r.reclamation_id="+rr;

        try {
//            pste = conn.prepareStatement(req);
//            ResultSet rs = pste.executeQuery();

            ste = conn.createStatement();

            ResultSet rs = ste.executeQuery(req);
            

            while(rs.next()){
                Reply r = new Reply();
                r.setId(rs.getInt("id"));
                r.setUser_id(rs.getInt("user_id"));
                r.setTitre(rs.getString("titre"));
                r.setDescription(rs.getString("description"));                
                r.setDate(rs.getTimestamp("datereply"));
                Utilisateur u = new Utilisateur();
                u.setId(rs.getInt("u.id"));
                u.setNom(rs.getString("u.nom"));
                u.setPseudo(rs.getString("u.pseudo"));
                u.setEmail("u.email");
                r.setUser(u);
                System.out.println(r.getUser());
                replies.add(r);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceReply.class.getName()).log(Level.SEVERE, null, ex);
        }

        return replies;
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

            while(rs.next()){
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
}