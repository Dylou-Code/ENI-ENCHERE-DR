package fr.eni.eniencheredr.service.EnchereService;

import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Encheres;
import fr.eni.eniencheredr.bo.Utilisateurs;

import java.util.List;

public interface EnchereService {
    List<Encheres> getEncheres();

    Encheres getEnchere(Integer no_article);

    void addEnchere(Encheres encheres);

    Encheres findById(Integer no_article);

    void updateUtilisateur(Encheres encheres);

    void deleteUtilisateur(Encheres encheres);
}
