package fr.eni.eniencheredr.bo;

public class Utilisateurs {
    private Integer no_utilisateur;
    private String pseudo;
    private String nom;
    private String prenom;
    private String email;
    private String mot_de_passe;
    private String rue;
    private String telephone;
    private String ville;
    private String code_postal;
    private Integer credit;
    private boolean administrateur = false;

    public Utilisateurs(){

    }

    public Utilisateurs(Integer no_utilisateur, String pseudo, String nom, String prenom, String email, String mot_de_passe, String rue, String telephone, String ville, String code_postal, Integer credit, boolean administrateur) {
        this.no_utilisateur = no_utilisateur;
        this.pseudo = pseudo;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.mot_de_passe = mot_de_passe;
        this.rue = rue;
        this.telephone = telephone;
        this.ville = ville;
        this.code_postal = code_postal;
        this.credit = credit;
        this.administrateur = administrateur;
    }

    public Integer getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(Integer no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getRue() {
        return rue;
    }

    public void setRue(String rue) {
        this.rue = rue;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getCode_postal() {
        return code_postal;
    }

    public void setCode_postal(String code_postal) {
        this.code_postal = code_postal;
    }

    public Integer getCredit() {
        return credit;
    }

    public void setCredit(Integer credit) {
        this.credit = credit;
    }

    public boolean isAdministrateur() {
        return administrateur;
    }

    public void setAdministrateur(boolean administrateur) {
        this.administrateur = administrateur;
    }

    @Override
    public String toString() {
        return "Utilisateurs{" +
                "no_utilisateur=" + no_utilisateur +
                ", pseudo='" + pseudo + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", email='" + email + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", rue='" + rue + '\'' +
                ", telephone='" + telephone + '\'' +
                ", ville='" + ville + '\'' +
                ", code_postal='" + code_postal + '\'' +
                ", credit=" + credit +
                ", administrateur=" + administrateur +
                '}';
    }
}
