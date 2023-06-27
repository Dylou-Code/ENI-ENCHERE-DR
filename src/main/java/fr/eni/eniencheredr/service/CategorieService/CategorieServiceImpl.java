package fr.eni.eniencheredr.service.CategorieService;

import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.dal.CategorieDAO.CategorieDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("categorieService")
public class CategorieServiceImpl implements CategorieService{
    private final CategorieDAO categorieDAO;
    @Autowired
    public CategorieServiceImpl(CategorieDAO categorieDAO) {
        this.categorieDAO = categorieDAO;
    }
    @Override
    public List<Categories> getCategories() {
        System.out.println(categorieDAO.findAllCategories());
        return categorieDAO.findAllCategories();
    }

    @Override
    public Categories getCategory(Integer no_categorie) {
        System.out.println(categorieDAO.findCategoryById(no_categorie));
        return categorieDAO.findCategoryById(no_categorie);
    }

    @Override
    public void addCategory(Categories categories) {
        categorieDAO.saveCategories(categories);
    }

    @Override
    public void updateCategory(Categories categories) {
        categorieDAO.updateCategories(categories);
    }

    @Override
    public void deleteCategory(Integer no_categorie) {
        categorieDAO.deleteCategories(no_categorie);
    }
}
