package fr.eni.eniencheredr.dal.ArticleDAO;

import fr.eni.eniencheredr.bo.Articles_Vendus;
import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.dal.CategorieDAO.CategorieDAO;
import fr.eni.eniencheredr.dal.UtilisateurDAO.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
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
    private final static String SELECT_BY_ID = "select no_article, nom_article, description, date_debut_encheres, date_fin_encheres, " +
            "prix_initial, prix_vente, u.no_utilisateur, c.no_categorie " +
            "FROM ARTICLES_VENDUS " +
            "INNER JOIN UTILISATEURS u ON u.no_utilisateur = ARTICLES_VENDUS.no_utilisateur " +
            "INNER JOIN CATEGORIES c ON c.no_categorie = ARTICLES_VENDUS.no_categorie " +
            "WHERE no_article= ?";

    private final static String SELECT_BY_NOM = "SELECT no_article, nom_article, description, date_debut_encheres, date_fin_encheres, " +
            "prix_initial, prix_vente, u.no_utilisateur, c.no_categorie " +
            "FROM ARTICLES_VENDUS " +
            "INNER JOIN UTILISATEURS u ON u.no_utilisateur = ARTICLES_VENDUS.no_article " +
            "INNER JOIN CATEGORIES c ON c.no_categorie = ARTICLES_VENDUS.no_article " +
            "WHERE nom_article LIKE '%' + :nom_article + '%'";




    private final static String INSERT = "INSERT INTO ARTICLES_VENDUS " +
            "(nom_article, description, prix_initial,date_debut_encheres, date_fin_encheres, no_utilisateur, no_categorie) " +
            "VALUES (:nom_article, :description, :prix_initial, :date_debut_encheres, :date_fin_encheres, :no_utilisateur, :no_categorie)";

    private final static String UPDATE = "UPDATE ARTICLES_VENDUS SET nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente,no_categorie = " +
            ":nom_article, :description, :date_debut_encheres, :date_fin_encheres, :prix_initial, :prix_vente,:no_categorie " +
            "WHERE no_article= ?";

    private static final String UPDATE_ARTICLE = "UPDATE ARTICLES_VENDUS SET nom_article = :nom_article, description = :description,  " +
            "date_debut_encheres = :date_debut_encheres, date_fin_encheres = :date_fin_encheres, prix_initial = :prix_initial, prix_vente = :prix_vente " +
            "WHERE no_article = :no_article ";

    private static final String ENCHERIR_ARTICLE = "UPDATE ARTICLES_VENDUS SET prix_vente = :prix_vente WHERE no_article = :no_article";

    private final static String DELETE = "DELETE ARTICLES_VENDUS WHERE no_article = :no_article";


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
            a.setPrix_vente(rs.getInt(7));
            //a.setUtilisateurs(utilisateurDAO.findUtilisateurById(rs.getInt("no_utilisateur")));
            a.setUtilisateurs(utilisateurDAO.findUtilisateurById(rs.getInt("no_utilisateur")));
            a.setCategories(categorieDAO.findCategoryById(rs.getInt("no_categorie")));
            return a;
        }
    }

    @Override
    public List<Articles_Vendus> findAllArticles() {
        List<Articles_Vendus> articles;
        articles = namedParameterJdbcTemplate.query(SELECT_ALL, new ArticleRowMapper());
        return articles;
    }
    @Override
    public Articles_Vendus findArticleById(Integer no_article) {
        Map<String, Object> params = new HashMap<>();
        params.put("id",no_article);

        Articles_Vendus article = null;
        article = namedParameterJdbcTemplate.getJdbcOperations().queryForObject(
                SELECT_BY_ID,
                new ArticleRowMapper(),
                no_article
        );
        return article;
    }
    @Override
    public List<Articles_Vendus> findArticleByName(String nom_article) {
      /*  return namedParameterJdbcTemplate.queryForObject(
                SELECT_BY_NOM,
                new MapSqlParameterSource("nom_article", nom_article),
                new ArticleRowMapper()
        );*/

       /* List<Articles_Vendus> articles;
        articles = namedParameterJdbcTemplate.query(SELECT_BY_NOM, new ArticleRowMapper());
        return articles;*/

        SqlParameterSource parameters = new MapSqlParameterSource("nom_article", "%" + nom_article + "%");
        List<Articles_Vendus> articles = namedParameterJdbcTemplate.query(SELECT_BY_NOM, parameters, new ArticleRowMapper());
        return articles;
    }
    @Override
    public void saveArticle(Articles_Vendus article) {
        /*Map<String, Object> map = new HashMap<>();
        map.put("nom_article", article.getNom_article());
        map.put("description", article.getDescription());
        map.put("date_debut_encheres", article.getDate_debut_encheres());
        map.put("date_fin_encheres", article.getDate_fin_encheres());
        map.put("prix_initial", article.getPrix_initial());
        map.put("prix_vente", article.getPrix_vente());
        map.put("no_utilisateur", article.getUtilisateurs().getNo_utilisateur());
        map.put("no_categorie", article.getCategories().getNo_categorie());*/
        /*map.put("no_utilisateur",article.getUtilisateurs()==null?null:article.getUtilisateurs().getNo_utilisateur());
        map.put("no_categorie", article.getCategories()==null?null:article.getCategories().getNo_categorie());*/


        KeyHolder keyHolder = new GeneratedKeyHolder();

        SqlParameterSource params = new MapSqlParameterSource()
                //.addValue("no_article", article.getNo_article())
                .addValue("nom_article", article.getNom_article())
                .addValue("description", article.getDescription())
                .addValue("date_debut_encheres", article.getDate_debut_encheres())
                .addValue("date_fin_encheres", article.getDate_fin_encheres())
                .addValue("prix_initial", article.getPrix_initial())
                .addValue("prix_vente", article.getPrix_vente())
                .addValue("no_utilisateur", article.getUtilisateurs().getNo_utilisateur())
                .addValue("no_categorie", article.getCategories().getNo_categorie());
        namedParameterJdbcTemplate.update(INSERT, params, keyHolder);

        if (keyHolder.getKey() != null) {
            // Mise à jour de l'identifiant du film auto-généré par la base
            article.setNo_article(keyHolder.getKey().intValue());
        }
    }
    @Override
    public void updateArticle(Articles_Vendus article) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("no_article", article.getNo_article())
                .addValue("nom_article", article.getNom_article())
                .addValue("description", article.getDescription())
                .addValue("date_debut_encheres", article.getDate_debut_encheres())
                .addValue("date_fin_encheres", article.getDate_fin_encheres())
                .addValue("prix_initial", article.getPrix_initial())
                .addValue("prix_vente", article.getPrix_vente())
                .addValue("no_utilisateur", article.getUtilisateurs())
                .addValue("no_categorie", article.getCategories().getNo_categorie());
        namedParameterJdbcTemplate.update(UPDATE_ARTICLE, params);
    }
    @Override
    public void deleteArticle(Articles_Vendus article) {
        namedParameterJdbcTemplate.update(DELETE, new BeanPropertySqlParameterSource(article));
    }

    @Override
    public void encherirArticle(Articles_Vendus article) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("prix_vente", article.getPrix_vente())
                .addValue("no_article", article.getNo_article());

        namedParameterJdbcTemplate.update(ENCHERIR_ARTICLE, params);
    }


    public List<Articles_Vendus> articleByFilter(Integer filtre, boolean encheresOuvertes, boolean encheresEnCours,
                                              boolean encheresRemportees, boolean ventesEnCours, boolean ventesNonDebutees, boolean ventesTerminees, int userId) {

        StringBuilder requete = new StringBuilder();
        List<Articles_Vendus> listArticle;


        System.out.println("dans la DAL TEMP méthode ArticleByfilter");

        if (filtre == 1) {
            System.out.println("Mes achats");

            if (!encheresOuvertes && !encheresEnCours && !encheresRemportees) {
                requete.append(SELECT_ALL);
            } else {
                requete.append(
                        "SELECT ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur, ARTICLES_VENDUS.no_categorie\r\n"
                                + "FROM ARTICLES_VENDUS INNER JOIN ENCHERES\r\n"
                                + "ON (ARTICLES_VENDUS.no_article = ENCHERES.no_article) ");
                if (encheresOuvertes) {
                    requete.append("WHERE ARTICLES_VENDUS.date_fin_encheres >= GETDATE() ");
                }
                if (encheresEnCours) {
                    if (requete.toString().contains("WHERE")) {
                        requete.append("OR ");
                    } else {
                        requete.append("WHERE ");
                    }
                    requete.append("ARTICLES_VENDUS.date_fin_encheres >= GETDATE() AND  ENCHERES.no_utilisateur = :id ");
                }

                if (encheresRemportees) {
                    if (requete.toString().contains("WHERE")) {
                        requete.append("OR ");
                    } else {
                        requete.append("WHERE ");
                    }
                    requete.append(
                            "ENCHERES.no_utilisateur = :id AND ARTICLES_VENDUS.date_fin_encheres <= GETDATE() AND montant_enchere = ARTICLES_VENDUS.prix_vente");
                }
            }

        } else if (filtre == 2) {

            System.out.println("Mes ventes");
            if (ventesEnCours == false && ventesNonDebutees == false && ventesTerminees == false) {
                requete.append(SELECT_ALL);
            } else {
                requete.append(
                        "SELECT ARTICLES_VENDUS.no_article, nom_article, description, date_debut_encheres, date_fin_encheres, prix_initial, prix_vente, ARTICLES_VENDUS.no_utilisateur, ARTICLES_VENDUS.no_categorie "
                                + "FROM ARTICLES_VENDUS ");
                if (ventesEnCours == true) {
                    requete.append(
                            "WHERE ARTICLES_VENDUS.no_utilisateur = :id AND ARTICLES_VENDUS.date_debut_encheres <= GETDATE() AND ARTICLES_VENDUS.date_fin_encheres > GETDATE() ");
                }

                if (ventesNonDebutees == true) {
                    if (requete.toString().contains("WHERE")) {
                        requete.append("OR ");

                    } else {
                        requete.append("WHERE ");
                    }

                    requete.append(
                            "ARTICLES_VENDUS.no_utilisateur = :id AND ARTICLES_VENDUS.date_debut_encheres >= GETDATE() ");
                }

                if (ventesTerminees == true) {
                    if (requete.toString().contains("WHERE")) {
                        requete.append("OR ");
                    } else {
                        requete.append("WHERE ");
                    }
                    requete.append(
                            "ARTICLES_VENDUS.no_utilisateur = :id AND ARTICLES_VENDUS.date_fin_encheres <= GETDATE()");
                }
            }
        }
        System.out.println("!!!!!!!!!!!" + requete);


        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("id",userId);

       /* listArticle = namedParameterJdbcTemplate.query(requete.toString(), params,
                new ArticleRowMapper(utilisateurDAO, encheresCategoriesDAO));*/

      /*  System.out.println("liste articles = " + listArticle.toString());*/

//      listArticle = null;

        return null;
    }
}
