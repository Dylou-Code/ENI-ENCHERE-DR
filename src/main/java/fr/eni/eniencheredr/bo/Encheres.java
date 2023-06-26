package fr.eni.eniencheredr.bo;

import java.time.LocalDateTime;

public class Encheres {
    private Utilisateurs no_utilisateur;
    private Articles_Vendus no_article;
    private LocalDateTime date_enchere;
    private Integer montant_enchere;

    public Encheres() {
    }
    public Encheres(LocalDateTime date_enchere, Integer montant_enchere) {
        this.date_enchere = date_enchere;
        this.montant_enchere = montant_enchere;
    }

    public LocalDateTime getDate_enchere() {
        return date_enchere;
    }

    public void setDate_enchere(LocalDateTime date_enchere) {
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
