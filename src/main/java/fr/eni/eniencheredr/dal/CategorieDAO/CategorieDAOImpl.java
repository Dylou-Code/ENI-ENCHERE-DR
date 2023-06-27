package fr.eni.eniencheredr.dal.CategorieDAO;

import fr.eni.eniencheredr.bo.Categories;
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
public class CategorieDAOImpl implements CategorieDAO {
    private final static String SELECT_ALL = "SELECT libelle FROM Categories";
    private final static String SELECT_BY_ID = "SELECT libelle FROM Categories where no_categorie = :no_categorie";
    private final static String SELECT_BY_LIBELLE = "SELECT libelle FROM Categories where libelle LIKE :libelle";
    private final static String CREATE = "INSERT INTO Categories (lilbele) VALUES (:libelle)";
    private final static String UPDATE = "UPDATE Categories SET libelle = :libelle WHERE no_categorie = :no_categorie";
    private final static String DELETE = "DELETE FROM Categories WHERE no_categorie = :no_categorie";
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Autowired
    public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }
    @Override
    public List<Categories> findAllCategories() {
        return namedParameterJdbcTemplate.getJdbcOperations().query(SELECT_ALL,
                new BeanPropertyRowMapper<>(Categories.class)
        );
    }

    @Override
    public Categories findCategoryById(Integer no_categorie) {
        return namedParameterJdbcTemplate.queryForObject(SELECT_BY_ID, new BeanPropertySqlParameterSource(no_categorie), new BeanPropertyRowMapper<>(Categories.class));
    }

    @Override
    public Categories findCategoryByLibelle(String libelle) {
        return namedParameterJdbcTemplate.queryForObject(SELECT_BY_LIBELLE, new BeanPropertySqlParameterSource(libelle), new BeanPropertyRowMapper<>(Categories.class));
    }

    @Override
    public void saveCategories(Categories categories) {
        Map<String, Object> map = new HashMap<>();
        map.put("libelle", categories.getLibelle());
        namedParameterJdbcTemplate.update(CREATE, map);
    }

    @Override
    public void updateCategories(Categories categories) {
        Map<String, Object> map = new HashMap<>();
        map.put("libelle", categories.getLibelle());
        namedParameterJdbcTemplate.update(UPDATE, map);
    }

    @Override
    public void deleteCategories(Integer no_categorie) {
        SqlParameterSource paramSource = new MapSqlParameterSource("no_categorie", no_categorie);
        namedParameterJdbcTemplate.update(DELETE, paramSource);
    }
}
