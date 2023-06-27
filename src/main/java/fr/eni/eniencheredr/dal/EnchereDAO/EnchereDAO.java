package fr.eni.eniencheredr.dal.EnchereDAO;

import fr.eni.eniencheredr.bo.Encheres;

import java.util.List;

public interface EnchereDAO {
    List<Encheres> findAllEncheres();

    Encheres findEncheresById(Integer id);
}
