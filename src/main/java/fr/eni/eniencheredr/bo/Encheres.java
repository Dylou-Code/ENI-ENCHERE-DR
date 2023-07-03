package fr.eni.eniencheredr.bo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Encheres {
    private Utilisateurs no_utilisateur;
    private Articles_Vendus no_article;
    private Date date_enchere;
    private Integer montant_enchere;

    public Encheres() {
    }
    public Encheres(Date date_enchere, Integer montant_enchere) {
        this.date_enchere = date_enchere;
        this.montant_enchere = montant_enchere;
    }
    public Utilisateurs getNo_utilisateur() {
        return no_utilisateur;
    }
    public void setNo_utilisateur(Utilisateurs no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }
    public Articles_Vendus getNo_article() {
        return no_article;
    }
    public void setNo_article(Articles_Vendus no_article) {
        this.no_article = no_article;
    }
    public Date getDate_enchere() {
        return date_enchere;
    }

    public void setDate_enchere(Date date_enchere) {
        this.date_enchere = date_enchere;
    }

    public Integer getMontant_enchere() {
        return montant_enchere;
    }

    public void setMontant_enchere(Integer montant_enchere) {
        this.montant_enchere = montant_enchere;
    }

    @Override
    public String toString() {
        return "Encheres{" +
                "date_enchere=" + date_enchere +
                ", montant_enchere=" + montant_enchere +
                '}';
    }
}
