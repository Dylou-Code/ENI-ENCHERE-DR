package fr.eni.eniencheredr.dal.ArticleDAO;

import fr.eni.eniencheredr.bo.Articles_Vendus;
import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.dal.CategorieDAO.CategorieDAO;
import fr.eni.eniencheredr.dal.UtilisateurDAO.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
    private final static String SELECT_ALL = "select nom_article, description, date_debut_encheres, date_fin_encheres, \n" +
            "\t\tprix_initial, prix_vente, UTILISATEURS.no_utilisateur, CATEGORIES.no_categorie \n" +
            "\tFROM ARTICLES_VENDUS \n" +
            "\tINNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_article\n" +
            "\tINNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_article";
    private final static String SELECT_BY_ID = "select nom_article, description, date_debut_encheres, date_fin_encheres, \n" +
            "\t\tprix_initial, prix_vente, UTILISATEURS.no_utilisateur, CATEGORIES.no_categorie \n" +
            "\tFROM ARTICLES_VENDUS\n" +
            "\tINNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_article\n" +
            "\tINNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_article" +
            "WHERE no_article = :no_article";

    private final static String SELECT_BY_NOM = "select nom_article, description, date_debut_encheres, date_fin_encheres, \n" +
            "\t\tprix_initial, prix_vente, UTILISATEURS.no_utilisateur, CATEGORIES.no_categorie \n" +
            "\tFROM ARTICLES_VENDUS\n" +
            "\tINNER JOIN UTILISATEURS ON UTILISATEURS.no_utilisateur = ARTICLES_VENDUS.no_article\n" +
            "\tINNER JOIN CATEGORIES ON CATEGORIES.no_categorie = ARTICLES_VENDUS.no_article" +
            "WHERE nom_article = :nom_article";
    private UtilisateurDAO utilisateurDAO;
    private CategorieDAO categorieDAO;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public void setUtilisateurDAO(UtilisateurDAO utilisateurDAO, CategorieDAO categorieDAO) {
        this.utilisateurDAO = utilisateurDAO;
        this.categorieDAO = categorieDAO;
    }

    public class ArticleRowMapper implements RowMapper<Articles_Vendus> {
        @Override
        public Articles_Vendus mapRow(ResultSet rs, int row) throws SQLException {
            Articles_Vendus a = new Articles_Vendus();
            a.setNo_article(rs.getInt(1));
            a.setNom_article(rs.getString(2));
            a.setDescription(rs.getString(3));
            a.setDate_debut_encheres(rs.getDate(4));
            a.setDate_fin_encheres(rs.getDate(5));
            a.setPrix_vente(rs.getInt(6));
            a.setNo_utilisateur(utilisateurDAO.findUtilisateurById(rs.getInt(7)));
            a.setNo_categorie(categorieDAO.findCategoryById(rs.getInt(8)));
            return a;
        }
    }

    @Override
    public List<Articles_Vendus> findAllArticles() {
        return namedParameterJdbcTemplate.query(SELECT_ALL, new ArticleRowMapper());
    }
    @Override
    public Articles_Vendus findArticleById(Integer no_article) {
        return namedParameterJdbcTemplate.queryForObject(
                SELECT_BY_ID,
                new MapSqlParameterSource("no_article", no_article),
                new ArticleRowMapper()
        );
    }
    @Override
    public Articles_Vendus findArticleByLibelle(String nom_article) {
        return namedParameterJdbcTemplate.queryForObject(
                SELECT_BY_NOM,
                new MapSqlParameterSource("nom_article", nom_article),
                new ArticleRowMapper()
        );
    }
    @Override
    public void saveArticle(Articles_Vendus article) {

    }
    @Override
    public void updateArticle(Articles_Vendus article) {

    }
    @Override
    public void deleteArticle(Integer no_article) {

    }
}
