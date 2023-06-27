package fr.eni.eniencheredr.dal.EnchereDAO;

import fr.eni.eniencheredr.bo.Encheres;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnchereDAOImpl implements EnchereDAO{
    private EnchereDAO enchereDAO;

    /*public class EnchereRowMapper  implements RowMapper<Encheres> {
        public Encheres mapRow(ResultSet rs, int rowNum) throws SQLException {
            Encheres encheres = new Encheres();
            encheres.setPseudo(rs.getString("pseudo"));
            encheres.setNom(rs.getString("nom"));
            encheres.setPrenom(rs.getString("prenom"));
            return encheres;
        }
    }*/
    @Override
    public List<Encheres> findAllEncheres() {
        return null;
    }

    @Override
    public Encheres findEncheresById(Integer id) {
        return null;
    }
}
