package fr.eni.eniencheredr.bo;

import java.sql.Date;

public class Articles_Vendus {
    private Integer no_article;
    private String nom_article;
    private String description;
    private Date date_debut_encheres;
    private Date date_fin_encheres;
    private Integer prix_initial;
    private Integer prix_vente;
    private Utilisateurs utilisateurs;
    private Categories categories;

    private String image;

    public Articles_Vendus() {}

    public Articles_Vendus(Integer no_article, String nom_article, String description, Date date_debut_encheres, Date date_fin_encheres,Integer prix_initial , Integer prix_vente) {
        this.no_article = no_article;
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.date_fin_encheres = date_fin_encheres;
        this.prix_initial = prix_initial;
        this.prix_vente = prix_vente;
    }

    public Articles_Vendus(Integer no_article, String nom_article, String description, Date date_debut_encheres, Date date_fin_encheres, Integer prix_vente, Integer prix_initial,Utilisateurs utilisateurs, Categories categories) {
        this.no_article = no_article;
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.date_fin_encheres = date_fin_encheres;
        this.prix_initial = prix_initial;
        this.prix_vente = prix_vente;
        this.utilisateurs = utilisateurs;
        this.categories = categories;

    }

    public Articles_Vendus(Integer no_article, String nom_article, String description, Date date_debut_encheres, Date date_fin_encheres, Integer prix_vente, Integer prix_initial,  String image ,Utilisateurs utilisateurs, Categories categories) {
        this.no_article = no_article;
        this.nom_article = nom_article;
        this.description = description;
        this.date_debut_encheres = date_debut_encheres;
        this.date_fin_encheres = date_fin_encheres;
        this.prix_initial = prix_initial;
        this.prix_vente = prix_vente;
        this.image = image;
        this.utilisateurs = utilisateurs;
        this.categories = categories;

    }



    public Integer getNo_article() {
        return no_article;
    }

    public void setNo_article(Integer no_article) {
        this.no_article = no_article;
    }

    public String getNom_article() {
        return nom_article;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate_debut_encheres() {
        return date_debut_encheres;
    }

    public void setDate_debut_encheres(Date date_debut_encheres) {
        this.date_debut_encheres = date_debut_encheres;
    }

    public Date getDate_fin_encheres() {
        return date_fin_encheres;
    }

    public void setDate_fin_encheres(Date date_fin_encheres) {
        this.date_fin_encheres = date_fin_encheres;
    }

    public Integer getPrix_initial() {
        return prix_initial;
    }

    public void setPrix_initial(Integer prix_initial) {
        this.prix_initial = prix_initial;
    }

    public Integer getPrix_vente() {
        return prix_vente;
    }



    public void setPrix_vente(Integer prix_vente) {
        this.prix_vente = prix_vente;
    }

    public Utilisateurs getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(Utilisateurs utilisateurs) {
        this.utilisateurs = utilisateurs;
    }


    public Categories getCategories() {
        return categories;
    }

    public void setCategories(Categories categories) {
        this.categories = categories;
    }

    /* public Utilisateurs getNo_utilisateur() {
        return no_utilisateur;
    }

    public void setNo_utilisateur(Utilisateurs no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }

    public Categories getNo_categorie() {
        return no_categorie;
    }

    public void setNo_categorie(Categories no_categorie) {
        this.no_categorie = no_categorie;
    }*/


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "Articles_Vendus{" +
                "no_article=" + no_article +
                ", nom_article='" + nom_article + '\'' +
                ", description='" + description + '\'' +
                ", date_debut_encheres=" + date_debut_encheres +
                ", date_fin_encheres=" + date_fin_encheres +
                ", prix_initial=" + prix_initial +
                ", prix_vente=" + prix_vente +
                ", utilisateurs=" + utilisateurs +
                ", categories=" + categories +
                ", image='" + image + '\'' +
                '}';
    }
}
