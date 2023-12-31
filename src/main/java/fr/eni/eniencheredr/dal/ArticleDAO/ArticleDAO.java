package fr.eni.eniencheredr.dal.ArticleDAO;

import fr.eni.eniencheredr.bo.Articles_Vendus;
import fr.eni.eniencheredr.bo.Categories;

import java.util.List;

public interface ArticleDAO {
    List<Articles_Vendus> findAllArticles();

    Articles_Vendus findArticleById(Integer no_article);

    List<Articles_Vendus> findArticleByName(String nom_article);

    List<Articles_Vendus> articleByUserDateEnchereInf(Integer no_utilisateur);

    List<Articles_Vendus> findMyArticles(Integer no_utilisateur);

    List<Articles_Vendus> findMyAuction(Integer no_utilisateur);





    void saveArticle(Articles_Vendus article);

    void updateArticle(Articles_Vendus article);

    void deleteArticle(Articles_Vendus article);

    void encherirArticle(Articles_Vendus article);
}
