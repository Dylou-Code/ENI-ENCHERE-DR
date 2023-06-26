package fr.eni.eniencheredr.dal.Enchere;

import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Encheres;
import fr.eni.eniencheredr.bo.Utilisateurs;

import java.util.List;

public interface EnchereDAO {
    List<Encheres> findAllEncheres();
}
