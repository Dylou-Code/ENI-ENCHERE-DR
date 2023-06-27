package fr.eni.eniencheredr.dal.CategorieDAO;

import fr.eni.eniencheredr.bo.Categories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategorieDAOImpl implements CategorieDAO {
    private final static String SELECT_ALL = "SELECT no_categorie, libelle FROM Categories";
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
}
