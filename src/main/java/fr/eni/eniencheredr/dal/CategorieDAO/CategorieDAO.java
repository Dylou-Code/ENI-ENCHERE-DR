package fr.eni.eniencheredr.dal.CategorieDAO;

import fr.eni.eniencheredr.bo.Categories;

import java.util.List;

public interface CategorieDAO {
    List<Categories> findAllCategories();
}
