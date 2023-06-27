package fr.eni.eniencheredr.service.CategorieService;

import fr.eni.eniencheredr.bo.Categories;

import java.util.List;

public interface CategorieService {
    List<Categories> getCategories();

    Categories getCategory(Integer no_categorie);

    void addCategory(Categories categories);

    void updateCategory(Categories categories);

    void deleteCategory(Integer no_categorie);
}
