package zvendelivery.tests;

import static java.lang.Integer.parseInt;
import zvendelivery.entites.Produits;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.ServiceProduit;
import zvendelivery.services.ServiceRestaurant;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import zvendelivery.entites.Message;
import zvendelivery.entites.Utilisateur;
import zvendelivery.services.ServiceMessage;
import zvendelivery.services.ServiceUser;

public class Zvendelivery {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException  {
        // TODO code application logic here
        ServiceUser rs = new ServiceUser();
        ServiceProduit p = new ServiceProduit();

        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        Restaurant r = new Restaurant(31,"xx","a jkjk","ariana","cite wahat","12:00","fast food","21:00","tunis",2045,"","imagekfc","imagekfc.jpg","verified");
        Restaurant restaurant = new Restaurant(31,155,"bdssdddd","BOOdM","TUNIS","WAHHAAAT","13:00","fast ","21:00","tunis",2055,"2022-03-24 14:46:03","imagekfc","imagekfc.jpg","verified");

        Produits pr = new Produits(155,3,"sandwich","sandiwch thon","","sandwichimage","ss");

        Produits pr1 = new Produits(60,restaurant,8,"pizzaaa","pizza 4 fromages","","pizzaimage","pizzaimage.jpg");
        Utilisateur k = new Utilisateur(19,0,22222222,0,"[\"ROLE_USER\"]","32222","2022-03-24 14:46:03","2022-03-24","image.jpg","image.jpg","aaa@esprit.tn","aaaaa","ghoul","prenomm","adresses","sexee","pseudoo");
        Message m = new Message(24,25,0,"ASLEMA WKHAY","aa");

        //RESTAUUUUURANT
         // rs.ajouter(r);
        // rs.modifer(restaurant);
      //  System.out.println(rs.afficher());

        // rs.supprimer(restaurant.getId());
         
     rs.supprimer(25);
          ArrayList<Message> lists = (ArrayList<Message>)ServiceMessage.getInstance().afficher();
      System.out.println(lists); 
    
             
        //PRODUIIIT
  


    }
}
