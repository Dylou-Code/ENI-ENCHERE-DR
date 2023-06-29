package fr.eni.eniencheredr.service.ArticleService;

import fr.eni.eniencheredr.bo.Articles_Vendus;

import java.util.List;

public interface ArticleService {
    List<Articles_Vendus> findAllArticles();
    Articles_Vendus findArticleById(Integer no_article);
    void saveArticle(Articles_Vendus article);
  /*  void updateArticle(Articles_Vendus article);*/
    void deleteArticle(Articles_Vendus article);
}
