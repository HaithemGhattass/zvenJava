/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zvendelivery.entites;
import java.util.*;
import java.util.prefs.Preferences;





/**
 *
 * @author Lenovo
 */
public class SessionManager {




    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login
    private static boolean actif;

    static {
        actif = false;
    }

 
    private static int id ,is_verified_phone,is_verified ,etat;

    public static int getIs_verified() {
        return is_verified;
    }

    public static void setIs_verified(int is_verified) {
        SessionManager.is_verified = is_verified;
    }
    private static String userName ;
    private static String email;
    private static String passowrd ;
    private static String photo;
    private static long numtel;
    private static String nomImage,prenom,adresse,nom ;
    private static String dateNaissance,PhoneCode;
    private static Utilisateur user ;
    private static ArrayList role;
  
    // private static String nomprod[];
    private static ArrayList<String> ar = new ArrayList<String>();

    public static int getIs_verified_phone() {
        return is_verified_phone;
    }

    public static void setIs_verified_phone(int is_verified_phone) {
        SessionManager.is_verified_phone = is_verified_phone;
    }
   public static int getEtat() {
        return etat;
    }

    public static void setEtat(int etat) {
        SessionManager.etat = etat;
    }

  



    public static ArrayList<String> getAr() {
        return ar;
    }

    public static void ajouter(String nom) {
        SessionManager.ar.add(nom);
    }

    public static boolean isActif() {
        return actif;
    }

    public static String getPhoneCode() {
        return PhoneCode;
    }

    public static void setPhoneCode(String PhoneCode) {
        SessionManager.PhoneCode = PhoneCode;
    }

    public static void setActif(boolean actif) {
        SessionManager.actif = actif;
    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        SessionManager.id = id;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        SessionManager.userName = userName;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        SessionManager.email = email;
    }

    public static String getPassowrd() {
        return passowrd;
    }

    public static void setPassowrd(String passowrd) {
        SessionManager.passowrd = passowrd;
    }

    public static String getPhoto() {
        return photo;
    }

    public static void setPhoto(String photo) {
        SessionManager.photo = photo;
    }

    public static long getNumtel() {
        return numtel;
    }

    public static void setNumtel(long numtel) {
        SessionManager.numtel = numtel;
    }

    public static String getNomImage() {
        return nomImage;
    }

    public static void setNomImage(String nomImage) {
        SessionManager.nomImage = nomImage;
    }

    public static String getPrenom() {
        return prenom;
    }

    public static void setPrenom(String prenom) {
        SessionManager.prenom = prenom;
    }

    public static String getAdresse() {
        return adresse;
    }

    public static void setAdresse(String adresse) {
        SessionManager.adresse = adresse;
    }

    public static String getNom() {
        return nom;
    }

    public static void setNom(String nom) {
        SessionManager.nom = nom;
    }

    public static String getDateNaissance() {
        return dateNaissance;
    }

    public static void setDateNaissance(String dateNaissance) {
        SessionManager.dateNaissance = dateNaissance;
    }

    public static Utilisateur getUser() {
        return user;
    }

    public static void setUser(Utilisateur user) {
        SessionManager.user = user;
    }

    public static ArrayList getRole() {
        return role;
    }

    public static void setRole(ArrayList role) {
        SessionManager.role = role;
    }

  



}
