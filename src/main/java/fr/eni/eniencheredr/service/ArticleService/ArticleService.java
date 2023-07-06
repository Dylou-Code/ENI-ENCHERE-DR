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
    void encherirArticle(Articles_Vendus article);

    /*filtre*/
    List<Articles_Vendus> findByName(String nom_article);

    List<Articles_Vendus> findByDateInf(Integer no_utilisateur);

    List<Articles_Vendus> findMyArticles(Integer no_utilisateur);
    List<Articles_Vendus> findMyAuction(Integer no_utilisateur);
}
