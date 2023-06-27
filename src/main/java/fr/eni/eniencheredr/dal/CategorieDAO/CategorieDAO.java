package fr.eni.eniencheredr.dal.CategorieDAO;

import fr.eni.eniencheredr.bo.Categories;

import java.util.List;

public interface CategorieDAO {
    List<Categories> findAllCategories();

    Categories findCategoryById(Integer no_categorie);

    Categories findCategoryByLibelle(String libelle);

    void saveCategories(Categories categories);

    void updateCategories(Categories categories);

    void deleteCategories(Integer no_categorie);

}
