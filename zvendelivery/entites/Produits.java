package zvendelivery.entites;

public class Produits {
    private int id;
    private int id_restaurant;
    private Restaurant restaurant;
    private float prix;
    private String nom;
    private String descriptionProduit;
    private String createdAt;
    private String imageProduit;
    private String nomImage;
    private String nomRestaurant;


    public Produits(int id, Restaurant restaurant, float prix, String nom, String descriptionProduit, String createdAt, String imageProduit, String nomImage) {
        this.id = id;
        this.restaurant = restaurant;
        this.prix = prix;
        this.nom = nom;
        this.descriptionProduit = descriptionProduit;
        this.createdAt = createdAt;
        this.imageProduit = imageProduit;
        this.nomImage = nomImage;
    }

    public Produits(int id, float prix, String nom, String descriptionProduit, String createdAt, String imageProduit, String nomImage) {
        this.id = id;
        this.prix = prix;
        this.nom = nom;
        this.descriptionProduit = descriptionProduit;
        this.createdAt = createdAt;
        this.imageProduit = imageProduit;
        this.nomImage = nomImage;
    }


    public Produits(float prix, String nom, String descriptionProduit, String createdAt, String imageProduit, String nomImage) {
        this.prix = prix;
        this.nom = nom;
        this.descriptionProduit = descriptionProduit;
        this.createdAt = createdAt;
        this.imageProduit = imageProduit;
        this.nomImage = nomImage;
    }


    public Produits(Restaurant restaurant,float prix, String nom, String descriptionProduit, String createdAt, String imageProduit, String nomImage) {
        this.prix = prix;
        this.nom = nom;
        this.descriptionProduit = descriptionProduit;
        this.createdAt = createdAt;
        this.imageProduit = imageProduit;
        this.nomImage = nomImage;
        this.restaurant = restaurant;
    }
    
    public Produits() {
    }

    public zvendelivery.entites.Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(zvendelivery.entites.Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public String getNomRestaurant() {
        return nomRestaurant;
    }

    public void setNomRestaurant(String nomRestaurant) {
        this.nomRestaurant = nomRestaurant;
    }

    public int getId_restaurant() {
        return id_restaurant;
    }

    public void setId_restaurant(int id_restaurant) {
        this.id_restaurant = id_restaurant;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescriptionProduit() {
        return descriptionProduit;
    }

    public void setDescriptionProduit(String descriptionProduit) {
        this.descriptionProduit = descriptionProduit;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getImageProduit() {
        return imageProduit;
    }

    public void setImageProduit(String imageProduit) {
        this.imageProduit = imageProduit;
    }

    public String getNomImage() {
        return nomImage;
    }

    public void setNomImage(String nomImage) {
        this.nomImage = nomImage;
    }
    @Override
    public String toString() {
        return "produit{ nom =" + nom + ", description =" + descriptionProduit + ", prix =" + prix +", restaurant =" + nomRestaurant + '}';
    }
}
