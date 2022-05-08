package zvendelivery.entites;

public class Utilisateur {
    private int id, etat,numtel,isVerified;
      private String role,phonecode,createdAt,dateNaissance,image,nomimage,email,passowrd,nom,prenom,adresse,sexe,pseudo;

    public Utilisateur() {
    }

    public Utilisateur(String email, String nom, String prenom,int id,String adresse,int numtel,String password,String pseudo) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
         this.id = id;
            this.numtel= numtel;
         this.adresse = adresse;
            this.passowrd = password;
       
           this.pseudo= pseudo;
    }
     public Utilisateur(String email, String nom, String prenom,int id,String adresse,int numtel,String password,String pseudo,String role) {
        this.email = email;
        this.nom = nom;
        this.prenom = prenom;
         this.id = id;
            this.numtel= numtel;
         this.adresse = adresse;
            this.passowrd = password;
       
           this.pseudo= pseudo;
           this.role=role;
    }
    public Utilisateur(String image ,String nomImage) {
        this.image = image;
        this.nomimage = nomImage;
       
    }

   

 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getNumtel() {
        return numtel;
    }

    public void setNumtel(int numtel) {
        this.numtel = numtel;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public void setIsVerified(int isVerified) {
        this.isVerified = isVerified;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhonecode() {
        return phonecode;
    }

    public void setPhonecode(String phonecode) {
        this.phonecode = phonecode;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", etat=" + etat + ", numtel=" + numtel + ", isVerified=" + isVerified + ", role=" + role + ", phonecode=" + phonecode + ", createdAt=" + createdAt + ", dateNaissance=" + dateNaissance + ", image=" + image + ", nomimage=" + nomimage + ", email=" + email + ", passowrd=" + passowrd + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", sexe=" + sexe + ", pseudo=" + pseudo + '}';
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassowrd() {
        return passowrd;
    }

    public void setPassowrd(String passowrd) {
        this.passowrd = passowrd;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Utilisateur(int etat, int numtel, int isVerified, String role, String phonecode, String createdAt, String dateNaissance, String image, String nomimage, String email, String passowrd, String nom, String prenom, String adresse, String sexe,String pseudo) {
        this.etat = etat;
        this.numtel = numtel;
        this.isVerified = isVerified;
        this.role = role;
        this.phonecode = phonecode;
        this.createdAt = createdAt;
        this.dateNaissance = dateNaissance;
        this.image = image;
        this.nomimage = nomimage;
        this.email = email;
        this.passowrd = passowrd;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.sexe = sexe;
        this.pseudo = pseudo;
    }

    public Utilisateur(int id, int etat, int numtel, int isVerified, String role, String phonecode, String createdAt, String dateNaissance, String image, String nomimage, String email, String passowrd, String nom, String prenom, String adresse, String sexe,String pseudo) {
        this.id = id;
        this.etat = etat;
        this.numtel = numtel;
        this.isVerified = isVerified;
        this.role = role;
        this.phonecode = phonecode;
        this.createdAt = createdAt;
        this.dateNaissance = dateNaissance;
        this.image = image;
        this.nomimage = nomimage;
        this.email = email;
        this.passowrd = passowrd;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.sexe = sexe;
        this.pseudo = pseudo;
    }

   



}
