/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package zvendelivery.entites;

import java.util.Date;

/**
 *
 * @author mtar
 */
public class Reply {
    private String titre,description;
    private int id,idrec,user_id,reclamation_id;
    private Date date;
    private Reclamation reclamation;
    private Utilisateur user;

    public Utilisateur getUser() {
        return user;
    }

    public void setUser(Utilisateur user) {
        this.user = user;
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }
    

    public int getIdrec() {
        return idrec;
    }

    public void setIdrec(int idrec) {
        this.idrec = idrec;
    }

    public int getReclamation_id() {
        return reclamation_id;
    }

    public void setReclamation_id(int reclamation_id) {
        this.reclamation_id = reclamation_id;
    }




    public Reply(int user_id,String titre, String description,  Date date,int reclamation_id) {
        this.titre = titre;
        this.user_id = user_id;
        this.reclamation_id = reclamation_id;
        this.description = description;
        this.date = date;
    }
     public Reply(int user_id,String titre, String description,int reclamation_id) {
        this.titre = titre;
        this.user_id = user_id;
        this.reclamation_id = reclamation_id;
        this.description = description;

        
    }
 @Override
    public String toString() {
        return "Replies{" + "id=" + id + ", user_id=" + user_id + ", Titre_Reply=" + titre+ ", Reply=" + description +",Date="+date+  '}';
    }
    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }



    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Reply() {
        this.titre = titre;
        this.description = description;
        this.date = date;
    }
}