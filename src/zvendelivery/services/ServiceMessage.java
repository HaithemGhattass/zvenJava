package zvendelivery.services;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import zvendelivery.entites.Message;
import zvendelivery.entites.Produits;
import zvendelivery.entites.Restaurant;
import zvendelivery.entites.SessionManager;


import zvendelivery.entites.Utilisateur;
import zvendelivery.utils.DataSource;
/**
 *
 * @author remo
 */
public class ServiceMessage implements IService<Message> {
    private Connection conn;
    private Statement ste;
    private PreparedStatement pste;
        private ResultSet rs;

    public static ServiceMessage instance= null;
    public ServiceMessage() {
        conn = DataSource.getInstance().getCnx();
    }
 public static ServiceMessage getInstance(){
        
        if (instance == null)
            instance = new ServiceMessage();
        return instance ;
    }
    @Override
    public void ajouter(Message m) {
       java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());


        String req = " INSERT INTO `messageS` (`sender_id`, `recipient_id`, `message`, `created_at`, `is_read`) VALUE "
                + "('" + m.getSender_id() + "','"+ m.getRecipient_id() + "','"+ m.getMessage() + "','"+ date_sql + "','"+ 0 +  "')";

        try {
            ste = conn.createStatement();
            ste.executeUpdate(req);
            System.out.println("MESSAGE créée");
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifer(Message t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void supprimer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Message> afficher() {
        
List<Message> messages = new ArrayList<>();

        String req = "SELECT * FROM `messages` WHERE sender_id = "+SessionManager.getId()+"";

        try {
         //   pste = conn.prepareStatement(req);
           // ResultSet rs = pste.executeQuery();

            ste = conn.createStatement();

           ResultSet rs = ste.executeQuery(req);

            while(rs.next()){
                Message r = new Message();
                r.setSender_id(rs.getInt("sender_id"));
                 r.setRecipient_id(rs.getInt("recipient_id"));
                r.setMessage(rs.getString("message"));
                r.setCreated_at(rs.getString("created_at"));






              messages.add(r);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }

        return messages;


    }
    
     @FXML
      public List<Message> afficher2() {
        
List<Message> messages = new ArrayList<>();

        String req = "SELECT * FROM `messages` WHERE recipient_id = "+SessionManager.getId()+"";

        try {
         //   pste = conn.prepareStatement(req);
           // ResultSet rs = pste.executeQuery();

            ste = conn.createStatement();

           ResultSet rs = ste.executeQuery(req);

            while(rs.next()){
                Message r = new Message();
                r.setSender_id(rs.getInt("sender_id"));
                 r.setRecipient_id(rs.getInt("recipient_id"));
                r.setMessage(rs.getString("message"));
                r.setCreated_at(rs.getString("created_at"));






              messages.add(r);

            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceRestaurant.class.getName()).log(Level.SEVERE, null, ex);
        }

        return messages;


    }

    @Override
    public List<Produits> afficherbyrestaurant(Message t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    

 





}
