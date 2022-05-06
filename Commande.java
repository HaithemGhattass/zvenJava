/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author asus
 */
public class Commande {
    
    private int id;
    private String adresse_livraison;
    private String mode_livraison;
    private float total_commande;
    private String renseignement;
    private int status;
    //id utilisateur connecté
  //private int idUser;

    public Commande(int id, String adresse_livraison, String mode_livraison) {
        this.id = id;
        this.adresse_livraison = adresse_livraison;
        this.mode_livraison = mode_livraison;
    }

  

    
    public Commande(int id, String adresse_livraison, String mode_livraison, float total_commande, String renseignement, int status) {
        this.id = id;
        this.adresse_livraison = adresse_livraison;
        this.mode_livraison = mode_livraison;
        this.total_commande = total_commande;
        this.renseignement = renseignement;
        this.status = status;
    }

    public Commande(String adresse_livraison, String mode_livraison, float total_commande, String renseignement, int status) {
        this.adresse_livraison = adresse_livraison;
        this.mode_livraison = mode_livraison;
        this.total_commande = total_commande;
        this.renseignement = renseignement;
        this.status = status;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAdresse_livraison() {
        return adresse_livraison;
    }

    public void setAdresse_livraison(String adresse_livraison) {
        this.adresse_livraison = adresse_livraison;
    }

    public String getMode_livraison() {
        return mode_livraison;
    }

    public void setMode_livraison(String mode_livraison) {
        this.mode_livraison = mode_livraison;
    }

    public float getTotal_commande() {
        return total_commande;
    }

    public void setTotal_commande(float total_commande) {
        this.total_commande = total_commande;
    }

    public String getRenseignement() {
        return renseignement;
    }

    public void setRenseignement(String renseignement) {
        this.renseignement = renseignement;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Commande{" + "id=" + id + ", adresse_livraison=" + adresse_livraison + ", mode_livraison=" + mode_livraison + ", total_commande=" + total_commande + ", renseignement=" + renseignement + ", status=" + status + '}';
    }
    
    
}
