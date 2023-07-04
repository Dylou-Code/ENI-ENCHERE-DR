package fr.eni.eniencheredr.dal.EnchereDAO;

import fr.eni.eniencheredr.bo.Encheres;
import fr.eni.eniencheredr.dal.ArticleDAO.ArticleDAO;
import fr.eni.eniencheredr.dal.UtilisateurDAO.UtilisateurDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class EnchereDAOImpl implements EnchereDAO{
    /*Trouver toutes les encheres d'un utilisateurs*/
    private static final String SELECT_USER_ENCHERE = "SELECT ench.date_enchere, ench.montant_enchere, u1.no_utilisateur as enchNo_utilisateur, "
            + "u1.pseudo as enchPseudo, u1.nom as enchNom, u1.prenom as enchPrenom, "
            + "u1.email as enchMail, u1.telephone as enchTelephone, u1.rue as enchRue, u1.code_postal as enchCodePostal,"
            + "u1.ville as enchVille, u1.mot_de_passe as enchPassword, u1.credit as enchCredit, u1.administrateur as enchAdministrateur,\n"
            + "a.*, c.*, u2.* from ENCHERES ench\n"
            + "inner join ARTICLES_VENDUS a on a.no_article = ench.no_article\n"
            + "inner join CATEGORIES c on c.no_categorie = a.no_categorie\n"
            + "inner join UTILISATEURS u2 on u2.no_utilisateur = a.no_utilisateur\n"
            + "inner join UTILISATEURS u1 on u1.no_utilisateur = ench.no_utilisateur\n"
            + "where ench.no_utilisateur = ?\n";

    /*Trouver une enchere par son ID*/
    private static final String SELECT_BY_ID = "SELECT ench.date_enchere, ench.montant_enchere, u1.no_utilisateur as enchNo_utilisateur, "
            + "u1.pseudo as enchPseudo, u1.nom as enchNom, u1.prenom as enchPrenom, "
            + "u1.email as enchMail, u1.telephone as enchTelephone, u1.rue as enchRue, u1.code_postal as enchCodePostal,"
            + "u1.ville as enchVille, u1.mot_de_passe as enchPassword, u1.credit as enchCredit, u1.administrateur as enchAdministrateur,\n"
            + "a.*, c.*, u2.* from ENCHERES ench\n"
            + "inner join ARTICLES_VENDUS a on a.no_article = ench.no_article\n"
            + "inner join CATEGORIES c on c.no_categorie = a.no_categorie\n"
            + "inner join UTILISATEURS u2 on u2.no_utilisateur = a.no_utilisateur\n"
            + "inner join UTILISATEURS u1 on u1.no_utilisateur = ench.no_utilisateur\n"
            + "where ench.no_article = ?\n";
    private static final String INSERT = "INSERT INTO ENCHERES (no_utilisateur, no_article, date_enchere,montant_enchere) VALUES (:no_utilisateur, :no_article, :date_enchere, :montant_enchere)";
    /*private static final String UPDATE = "update ENCHERES set no_utilisateur = ?, no_Article = ?, date_enchere = ?, montant_enchere = ? where no_article = ?";*/
    //private static final String UPDATE = "UPDATE ENCHERES SET no_utilisateur, no_article, date_enchere, montant_enchere = :no_utilisateur, :no_article, :date_enchere, :montant_enchere WHERE no_article = :no_article";
    private static final String UPDATE_ENCHERE = "UPDATE ENCHERES SET no_utilisateur = :no_utilisateur, no_article = :no_article,  " +
            "date_enchere = :date_enchere, montant_enchere = :montant_enchere " +
            "WHERE no_article = :no_article ";
            //" + AND date_montant BETWEEN "

    private final static String DELETE = "DELETE ENCHERES WHERE no_article = :no_article";
    private NamedParameterJdbcTemplate npjt;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate npjt) {
        this.npjt = npjt;
    }

    public class EnchereRowMapper  implements RowMapper<Encheres> {
        public Encheres mapRow(ResultSet rs, int rowNum) throws SQLException {
            Encheres encheres = new Encheres();
            encheres.setNo_utilisateur(rs.getInt("no_utilisateur"));
            encheres.setNo_article(rs.getInt("no_article"));
            encheres.setDate_enchere(rs.getDate("date_enchere"));
            encheres.setMontant_enchere(rs.getInt("montant_enchere"));
            /*encheres.setNo_utilisateur(utilisateurDAO.findUtilisateurById(rs.getInt("no_utilisateur")));
            encheres.setNo_article(articleDAO.findArticleById(rs.getInt("no_article")));*/

            return encheres;
        }
    }

  /**/
    @Override
    public List<Encheres> findAllEncheres() {
        return npjt.query( SELECT_USER_ENCHERE,
                new EnchereRowMapper()
        );
    }

    @Override
    public Encheres findEncheresById(Integer no_utilisateur) {
        Map<String, Object> params = new HashMap<>();
        params.put("id",no_utilisateur);
        Encheres enchere = null;
        enchere = npjt.getJdbcOperations().queryForObject(
                SELECT_BY_ID,
                new EnchereRowMapper(),
                no_utilisateur
        );
        return enchere;
    }

    @Override
    public void saveEnchere(Encheres enchere) {
        Map<String, Object> map = new HashMap<>();
        map.put("no_utilisateur", enchere.getNo_utilisateur());
        map.put("no_article", enchere.getNo_article());
        map.put("date_enchere", enchere.getDate_enchere());
        map.put("montant_enchere", enchere.getMontant_enchere());
        /*map.put("no_utilisateur",article.getUtilisateurs()==null?null:article.getUtilisateurs().getNo_utilisateur());
        map.put("no_categorie", article.getCategories()==null?null:article.getCategories().getNo_categorie());*/
        npjt.update(INSERT, map);
    }

    @Override
    public void updateEnchere(Encheres enchere) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("no_utilisateur", enchere.getNo_utilisateur())
                .addValue("no_article", enchere.getNo_article())
                .addValue("date_enchere", enchere.getDate_enchere())
                .addValue("montant_enchere", enchere.getMontant_enchere());
        npjt.update(UPDATE_ENCHERE, params);
        System.out.println(enchere.getDate_enchere());
    }

    @Override
    public void deleteEnchere(Encheres enchere) {
        npjt.update(DELETE, new BeanPropertySqlParameterSource(enchere));
    }
}
