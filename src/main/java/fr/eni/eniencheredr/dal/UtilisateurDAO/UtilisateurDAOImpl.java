package fr.eni.eniencheredr.dal.UtilisateurDAO;

import fr.eni.eniencheredr.bo.Utilisateurs;
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
public class UtilisateurDAOImpl implements UtilisateurDAO {
    private final static String SELECT_ALL = "SELECT no_utilisateur, pseudo, nom, " +
            "prenom, email, telephone, rue, code_postal, ville, mot_de_passe, " +
            "credit,administrateur" +
            " FROM Utilisateurs";
    private final static String SELECT_BY_ID = "SELECT * FROM Utilisateurs WHERE no_utilisateur= ?";
    private final static String SELECT_BY_PSEUDO = "SELECT * FROM Utilisateurs WHERE pseudo= ?";
    private final static String CREATE = "INSERT INTO Utilisateurs " +
            "(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) " +
            "VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe)";
    /*private final static String UPDATE = "UPDATE Utilisateurs SET pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit = " +
            ":pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, :credit " +
            "WHERE no_utilisateur= :no_utilisateur";*/

    private final static String UPDATE = "UPDATE Utilisateurs SET pseudo = :pseudo, nom = :nom, prenom = :prenom, email = :email, telephone = :telephone, " +
            "rue = :rue, code_postal =:code_postal, ville = :ville, mot_de_passe = :mot_de_passe, credit = :credit " +
            "WHERE no_utilisateur= :no_utilisateur";
    private final static String DELETE = "DELETE Utilisateurs WHERE no_utilisateur= :no_utilisateur";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    public class UtilisateurRowMapper implements RowMapper<Utilisateurs> {
        @Override
        public Utilisateurs mapRow(ResultSet rs, int row) throws SQLException {
            Utilisateurs u = new Utilisateurs();
            u.setNo_utilisateur(rs.getInt("no_utilisateur"));
            u.setPseudo(rs.getString("pseudo"));
            u.setNom(rs.getString("nom"));
            u.setPrenom(rs.getString("prenom"));
            u.setEmail(rs.getString("email"));
            u.setTelephone(rs.getString("telephone"));
            u.setRue(rs.getString("rue"));
            u.setCode_postal(rs.getString("code_postal"));
            u.setVille(rs.getString("ville"));
            u.setMot_de_passe(rs.getString("mot_de_passe"));
            u.setCredit(rs.getInt("credit"));
            u.setAdministrateur(rs.getBoolean("administrateur"));

            return u;
        }
    }

    @Override
    public List<Utilisateurs> findAllUtilisateurs() {
        return namedParameterJdbcTemplate.query(SELECT_ALL,
                new UtilisateurRowMapper()
        );
    }
    /*MapSqlParameterSource paramSrc = new MapSqlParameterSource("no_utilisateur", no_utilisateur);
    Utilisateurs utilisateurs = namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, paramSrc,new UtilisateurRowMapper());
*/
    @Override
    public Utilisateurs findUtilisateurById(Integer no_utilisateur) {

        /*return namedParameterJdbcTemplate.getJdbcOperations().queryForObject(
                SELECT_BY_ID,
               new UtilisateurRowMapper(),
               no_utilisateur);*/

        Map<String, Object> params = new HashMap<>();
        params.put("id",no_utilisateur);

        Utilisateurs utilisateurs = null;

        utilisateurs = namedParameterJdbcTemplate.getJdbcOperations().queryForObject(
                SELECT_BY_ID,
                new UtilisateurRowMapper(),
                no_utilisateur);
        return utilisateurs;
    }

    @Override
    public Utilisateurs findUtilisateurByPseudo(String pseudo) {
        return namedParameterJdbcTemplate.getJdbcOperations().queryForObject(
                SELECT_BY_PSEUDO,
                new UtilisateurRowMapper(),
                pseudo);
    }

    @Override
    public void saveUtilisateur(Utilisateurs utilisateur) {
        Map<String, Object> map = new HashMap<>();
        map.put("pseudo", utilisateur.getPseudo());
        map.put("nom", utilisateur.getNom());
        map.put("prenom", utilisateur.getPrenom());
        map.put("email", utilisateur.getEmail());
        map.put("telephone", utilisateur.getTelephone());
        map.put("rue", utilisateur.getRue());
        map.put("code_postal", utilisateur.getCode_postal());
        map.put("ville", utilisateur.getVille());
        map.put("mot_de_passe", utilisateur.getMot_de_passe());
        namedParameterJdbcTemplate.update(CREATE, map);
    }

    @Override
    public void updateUtilisateur(Utilisateurs utilisateur) {
        SqlParameterSource params = new MapSqlParameterSource()
                .addValue("no_utilisateur", utilisateur.getNo_utilisateur())
                .addValue("pseudo", utilisateur.getPseudo())
                .addValue("nom", utilisateur.getNom())
                .addValue("prenom", utilisateur.getPrenom())
                .addValue("email", utilisateur.getEmail())
                .addValue("telephone", utilisateur.getTelephone())
                .addValue("rue", utilisateur.getRue())
                .addValue("code_postal", utilisateur.getCode_postal())
                .addValue("ville", utilisateur.getVille())
                .addValue("mot_de_passe", utilisateur.getMot_de_passe())
                .addValue("credit", utilisateur.getCredit());
/*
                .addValue("administrateur", utilisateur.isAdministrateur());
*/
        namedParameterJdbcTemplate.update(UPDATE, params);
    }

    @Override
    public void deleteUtilisateur(Utilisateurs utilisateurs) {
        namedParameterJdbcTemplate.update(DELETE, new BeanPropertySqlParameterSource(utilisateurs));
    }
}
