package fr.eni.eniencheredr.dal.EnchereDAO;

import fr.eni.eniencheredr.bo.Encheres;

import java.util.List;

public interface EnchereDAO {
    List<Encheres> findAllEncheres(Integer no_utilisateur);

    List<Encheres> findMyenchere();

    Encheres findEncheresById(Integer id);
    Encheres getEnchere(Integer no_article);

    void saveEnchere(Encheres enchere);

    void updateEnchere(Encheres enchere);

    void deleteEnchere(Encheres enchere);
}
