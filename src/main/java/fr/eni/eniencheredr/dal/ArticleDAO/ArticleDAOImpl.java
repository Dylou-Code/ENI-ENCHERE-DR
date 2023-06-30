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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class ArticleDAOImpl implements ArticleDAO {
    private final static String SELECT_ALL = "select * FROM ARTICLES_VENDUS " +
            "INNER JOIN UTILISATEURS u ON u.no_utilisateur = ARTICLES_VENDUS.no_utilisateur " +
            "INNER JOIN CATEGORIES c ON c.no_categorie = ARTICLES_VENDUS.no_categorie";

    /*SELECT nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, U.no_utilisateur, C.no_categorie
    FROM ARTICLES_VENDUS AV
    INNER JOIN UTILISATEURS U ON U.no_utilisateur = AV.no_utilisateur
    INNER JOIN CATEGORIES C ON C.no_categorie = AV.no_categorie*/
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

    private final static String INSERT = "INSERT INTO ARTICLES_VENDUS " +
            "(nom_article, description, prix_initial,date_debut_encheres, date_fin_encheres, no_utilisateur, no_categorie) " +
            "VALUES (:nom_article, :description, :prix_initial, :date_debut_encheres, :date_fin_encheres, :no_utilisateur, :no_categorie)";


    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    private UtilisateurDAO utilisateurDAO;
    @Autowired
    private CategorieDAO categorieDAO;

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
            a.setPrix_initial(rs.getInt(6));
            //a.setPrix_vente(rs.getInt(7));
            //a.setUtilisateurs(utilisateurDAO.findUtilisateurById(rs.getInt("no_utilisateur")));
            a.setUtilisateurs(utilisateurDAO.findUtilisateurById(rs.getInt("no_utilisateur")));
            a.setCategories(categorieDAO.findCategoryById(rs.getInt("no_categorie")));

           /* Utilisateurs utilisateurs = null;
            try {
                utilisateurs = utilisateurDAO.findUtilisateurById(rs.getInt("no_utilisateur"));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            a.setUtilisateurs(utilisateurs);*/


            /*film.setGenre(genre);*/
            return a;
        }
    }

    @Override
    public List<Articles_Vendus> findAllArticles() {
        /*List<Film> films;
        films = namedParameterJdbcTemplate.query(SELECT_ALL, new FilmRowMapper());
        System.out.println(films);
        return films;*/
        List<Articles_Vendus> articles;
        articles = namedParameterJdbcTemplate.query(SELECT_ALL, new ArticleRowMapper());
        System.out.println(articles);
        return articles;
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
        System.out.println(article);
        Map<String, Object> map = new HashMap<>();
        map.put("nom_article", article.getNom_article());
        map.put("description", article.getDescription());
        map.put("date_debut_encheres", article.getDate_debut_encheres());
        map.put("date_fin_encheres", article.getDate_fin_encheres());
        map.put("prix_initial", article.getPrix_initial());
        map.put("no_utilisateur", article.getUtilisateurs().getNo_utilisateur());
        map.put("no_categorie", article.getCategories().getNo_categorie());
        /*map.put("no_utilisateur",article.getUtilisateurs()==null?null:article.getUtilisateurs().getNo_utilisateur());
        map.put("no_categorie", article.getCategories()==null?null:article.getCategories().getNo_categorie());*/
        namedParameterJdbcTemplate.update(INSERT, map);

       /* MapSqlParameterSource paramSrc = new MapSqlParameterSource("titre", film.getTitre() );
        paramSrc.addValue("annee", film.getAnnee());
        paramSrc.addValue("duree", 0);
        paramSrc.addValue("synopsis", film.getSynopsis());
        paramSrc.addValue("lien_image", film.getLienImage());
        paramSrc.addValue("genre_id", film.getGenre()==null?null:film.getGenre().getId());
        *//* paramSrc.addValue("realisateur_id", film.getRealisateur()==null?null:film.getRealisateur().getId());*//*

        namedParameterJdbcTemplate.update(INSERT, paramSrc);*/

    }
    @Override
    public void updateArticle(Articles_Vendus article) {

    }
    @Override
    public void deleteArticle(Articles_Vendus article) {

    }
}
