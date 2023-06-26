package fr.eni.eniencheredr.dal.Enchere;

import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Encheres;
import fr.eni.eniencheredr.bo.Utilisateurs;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EnchereDAOImpl implements EnchereDAO{
    private EnchereDAO enchereDAO;
    @Override
    public List<Encheres> findAllEncheres() {
        return null;
    }
}
