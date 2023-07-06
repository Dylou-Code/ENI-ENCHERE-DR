package fr.eni.eniencheredr.service.ArticleService;

import fr.eni.eniencheredr.bo.Articles_Vendus;
import fr.eni.eniencheredr.bo.Encheres;
import fr.eni.eniencheredr.dal.ArticleDAO.ArticleDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService{

    private final ArticleDAO articleDAO;

    public ArticleServiceImpl(ArticleDAO articleDAO) {
        this.articleDAO = articleDAO;
    }

    @Override
    public List<Articles_Vendus> findAllArticles() {
        return articleDAO.findAllArticles();
    }

    @Override
    public Articles_Vendus findArticleById(Integer no_article) {
        return articleDAO.findArticleById(no_article);
    }

    @Override
    public void saveArticle(Articles_Vendus article) {
        articleDAO.saveArticle(article);
    }

   @Override
    public void updateArticle(Articles_Vendus article) {
        articleDAO.updateArticle(article);
    }

    @Override
    public void deleteArticle(Articles_Vendus article) {
        articleDAO.deleteArticle(article);
    }

    @Override
    public List<Articles_Vendus> findByName(String nom_article) {

        return articleDAO.findArticleByName(nom_article);
    }

    @Override
    public List<Articles_Vendus> findByDateInf(Integer no_utilisateur) {
        return articleDAO.articleByUserDateEnchereInf(no_utilisateur);
    }

    @Override
    public List<Articles_Vendus> findMyArticles(Integer no_utilisateur) {
        return  articleDAO.findMyArticles(no_utilisateur);
    }

    @Override
    public List<Articles_Vendus> findMyAuction(Integer no_utilisateur) {
        return articleDAO.findMyAuction(no_utilisateur);
    }


    @Override
    public void encherirArticle(Articles_Vendus article) {
        articleDAO.encherirArticle(article);
    }
}
