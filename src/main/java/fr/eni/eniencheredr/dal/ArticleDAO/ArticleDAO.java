package fr.eni.eniencheredr.dal.ArticleDAO;

import fr.eni.eniencheredr.bo.Articles_Vendus;
import fr.eni.eniencheredr.bo.Categories;

import java.util.List;

public interface ArticleDAO {
    List<Articles_Vendus> findAllArticles();

    Articles_Vendus findArticleById(Integer no_article);

    Articles_Vendus findArticleByLibelle(String libelle);

    void saveArticle(Articles_Vendus article);

    void updateArticle(Articles_Vendus article);

    void deleteArticle(Articles_Vendus article);
}
