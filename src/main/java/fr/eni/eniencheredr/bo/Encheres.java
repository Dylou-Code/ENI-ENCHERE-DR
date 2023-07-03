package fr.eni.eniencheredr.bo;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Encheres {
    private Integer no_utilisateur;
    private Integer no_article;
    private Date date_enchere;
    private Integer montant_enchere;

    public Encheres() {
    }
    public Encheres(Integer no_utilisateur, Integer no_article, Date date_enchere, Integer montant_enchere) {
        this.no_utilisateur = no_utilisateur;
        this.no_article = no_article;
        this.date_enchere = date_enchere;
        this.montant_enchere = montant_enchere;
    }
    public Integer getNo_utilisateur() {
        return no_utilisateur;
    }
    public void setNo_utilisateur(Integer no_utilisateur) {
        this.no_utilisateur = no_utilisateur;
    }
    public Integer getNo_article() {
        return no_article;
    }
    public void setNo_article(Integer no_article) {
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
