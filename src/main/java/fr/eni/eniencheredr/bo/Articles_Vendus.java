package fr.eni.eniencheredr.bo;

import java.time.LocalDateTime;

public class Articles_Vendus {
    private Integer no_article;
    private String nom_article;
    private String description;
    private LocalDateTime date_debut_encheres;
    private LocalDateTime date_fin_encheres;
    private Integer prix_vente;
    private Utilisateurs no_utilisateur;
    private Categories no_categorie;
}
