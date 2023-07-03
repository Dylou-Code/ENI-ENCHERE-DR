package fr.eni.eniencheredr.dal.RetraitDAO;

import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Retraits;

import java.util.List;

public interface RetraitDAO {
    List<Retraits> findAllRetraits();

    Retraits findRetraitById(Integer no_article);

    Retraits findRetraitByLibelle(String libelle);

    void saveRetraits(Retraits retrait);

    void updateRetraits(Retraits retrait);

    void deleteRetrait(Integer no_article);
}
