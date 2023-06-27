package fr.eni.eniencheredr.dal.UtilisateurDAO;

import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Utilisateurs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class UtilisateurDAOImpl implements UtilisateurDAO {
    private final static String SELECT_ALL = "SELECT no_utilisateur, pseudo, nom, " +
            "prenom, email, telephone, rue, code_postal, ville, mot_de_passe, " +
            "credit,administrateur" +
            " FROM Utilisateurs";
    private final static String SELECT_BY_ID = "SELECT no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit, administrateur" +
            " FROM Utilisateurs WHERE no_utilisateur = :no_utilisateur";
    private final static String CREATE = "INSERT INTO Utilisateurs " +
            "(pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe) " +
            "VALUES (:pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe)";
    private final static String UPDATE = "UPDATE Categories SET no_utilisateur, pseudo, nom, prenom, email, telephone, rue, code_postal, ville, mot_de_passe, credit = " +
            ":pseudo, :nom, :prenom, :email, :telephone, :rue, :code_postal, :ville, :mot_de_passe, :credit " +
            "WHERE no_categorie = :no_categorie";
    private final static String DELETE = "DELETE FROM Utilisateurs WHERE no_utilisateur = :no_uilisateur";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public List<Utilisateurs> findAllUtilisateurs() {
        return namedParameterJdbcTemplate.query(SELECT_ALL,
                new BeanPropertyRowMapper<>(Utilisateurs.class)
        );
    }


    @Override
    public Utilisateurs findUtilisateurById(Integer no_utilisateur) {
        return namedParameterJdbcTemplate.queryForObject(
                SELECT_BY_ID,
                new BeanPropertySqlParameterSource(no_utilisateur),
                new BeanPropertyRowMapper<>(Utilisateurs.class));
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
                .addValue("pseudo", utilisateur.getPseudo())
                .addValue("nom", utilisateur.getNom())
                .addValue("prenom", utilisateur.getPrenom())
                .addValue("email", utilisateur.getEmail())
                .addValue("telephone", utilisateur.getTelephone())
                .addValue("rue", utilisateur.getRue())
                .addValue("code_postal", utilisateur.getCode_postal())
                .addValue("ville", utilisateur.getVille())
                .addValue("mot_de_passe", utilisateur.getMot_de_passe())
                .addValue("administrateur", utilisateur.isAdministrateur());
        namedParameterJdbcTemplate.update(UPDATE, params);
    }

    @Override
    public void deleteUtilisateur(Integer no_utilisateur) {
        namedParameterJdbcTemplate.update(DELETE, new BeanPropertySqlParameterSource(no_utilisateur));
    }
}
