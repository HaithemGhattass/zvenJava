/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zvendelivery.entites;

/**
 *
 * @author mtar
 */
import java.util.Date;

public class Reclamation {
    private int id,foodqulaite,price,service;
    private String titre,description,etat,nom;
    private String image,nomimage;

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    private Utilisateur user;

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }
    private Restaurant restaurant;
    
    private int idres;
    private int ID_user,restaurant_id;

    

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public int getRestaurant_id() {
        return restaurant_id;
    }

    public void setRestaurant_id(int restaurant_id) {
        this.restaurant_id = restaurant_id;
    }
    private Date date;

    public Reclamation(int re,int r,int i, String titre, String description,String nomimage,int foodqulaite, int price, int service) {
        this.id=re;
        this.restaurant_id=r;
        this.ID_user= i;
        this.titre= titre;
        this.description= description;
        this.nomimage=nomimage;
        this.foodqulaite=foodqulaite;
        this.price=price;
        this.service=service;

    }
      public Reclamation(int re,int i, String titre, String description,String nomimage,int foodqulaite, int price, int service) {
        this.restaurant_id=re;
        this.ID_user= i;
        this.titre= titre;
        this.description= description;
        this.nomimage=nomimage;
        this.foodqulaite=foodqulaite;
        this.price=price;
        this.service=service;

    }
       public Reclamation(int re,String etat,String nom) {
        this.id=re;
        this.etat=etat;
        this.nom=nom;
      

    }
      

    public int getID_user() {
        return ID_user;
    }

    public void setID_user(int ID_user) {
        this.ID_user = ID_user;
    }


    public int getIdres() {
        return idres;
    }

    public void setIdres(int idres) {
        this.idres = idres;
    }

    public Reclamation() {

    }

   

    public Reclamation(int id,String titre, String description, String nomimage,Date date,String etat,int foodqulaite, int price, int service) {
       this.id = id;
        this.titre = titre;
        this.description = description;
        this.foodqulaite = foodqulaite;
        this.price = price;
        this.service = service; 
        this.date = date;
        this.etat=etat;
        this.nomimage=nomimage;
    }
    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", user_id=" + ID_user + ", Titre_Reclamation=" + titre+ ", Description_Reclamation=" + description +",Date="+date+",nomimage="+nomimage+  '}';
    }
    public int getFoodqulaite() {
        return foodqulaite;
    }

    public void setFoodqulaite(int foodqulaite) {
        this.foodqulaite = foodqulaite;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getService() {
        return service;
    }

    public void setService(int service) {
        this.service = service;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNomimage() {
        return nomimage;
    }

    public void setNomimage(String nomimage) {
        this.nomimage = nomimage;
    }



}
