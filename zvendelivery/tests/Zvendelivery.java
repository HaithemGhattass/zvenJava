package zvendelivery.tests;

import zvendelivery.entites.Produits;
import zvendelivery.entites.Restaurant;
import zvendelivery.services.ServiceProduit;
import zvendelivery.services.ServiceRestaurant;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Zvendelivery {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        // TODO code application logic here
        ServiceRestaurant rs = new ServiceRestaurant();
        ServiceProduit p = new ServiceProduit();

        java.sql.Timestamp date_sql = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());

        Restaurant r = new Restaurant(31,"bbb","a jkjk","ariana","cite wahat","12:00","fast food","21:00","tunis",2045,"","imagekfc","imagekfc.jpg","verified");
        Restaurant restaurant = new Restaurant(31,155,"wtf","BOOdM","TUNIS","WAHHAAAT","13:00","fast ","21:00","tunis",2055,"2022-03-24 14:46:03","imagekfc","imagekfc.jpg","verified");

        Produits pr = new Produits(155,3,"sandwich","sandiwch thon","","sandwichimage","ss");

        Produits pr1 = new Produits(60,restaurant,8,"pizzaaa","pizza 4 fromages","","pizzaimage","pizzaimage.jpg");



        //RESTAUUUUURANT
       //   rs.ajouter(restaurant);
       //  rs.modifer(restaurant);
      //  System.out.println(rs.afficher());

        // rs.supprimer(restaurant.getId());

        //PRODUIIIT
         p.ajouter(pr);
       // p.modifer( pr1);
       // p.supprimer(57);
      //  System.out.println(p.afficher());
      //  System.out.println(rs.afficherbyrestaurant(restaurant));


    }
}
