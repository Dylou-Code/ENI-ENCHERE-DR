package fr.eni.eniencheredr.service.ArticleService;

import fr.eni.eniencheredr.bo.Articles_Vendus;
import fr.eni.eniencheredr.bo.Encheres;

import java.util.List;

public interface ArticleService {
    List<Articles_Vendus> findAllArticles();
    Articles_Vendus findArticleById(Integer no_article);
    void saveArticle(Articles_Vendus article);
    void updateArticle(Articles_Vendus article);
    void deleteArticle(Articles_Vendus article);

    List<Articles_Vendus> findByName(String nom_article);
    List<Articles_Vendus> articleByFilter(Integer filtre, boolean encheresOuvertes, boolean encheresEnCours,
                                          boolean encheresRemportees, boolean ventesEnCours, boolean ventesNonDebutees, boolean ventesTerminees, int userId);

    void encherirArticle(Articles_Vendus article);
}
