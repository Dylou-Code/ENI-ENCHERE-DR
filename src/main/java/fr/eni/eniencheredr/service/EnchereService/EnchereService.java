package fr.eni.eniencheredr.service.EnchereService;

import fr.eni.eniencheredr.bo.Articles_Vendus;
import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Encheres;
import fr.eni.eniencheredr.bo.Utilisateurs;

import java.util.List;

public interface EnchereService {
    List<Encheres> getEncheres();

    void addEnchere(Encheres encheres);

    Encheres findById(Integer no_article);

    void updateEnchere(Encheres encheres);

    void deleteEnchere(Encheres enchere);
}
