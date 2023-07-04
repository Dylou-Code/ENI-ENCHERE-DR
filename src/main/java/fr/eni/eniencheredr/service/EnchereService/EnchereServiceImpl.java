package fr.eni.eniencheredr.service.EnchereService;

import fr.eni.eniencheredr.bo.Articles_Vendus;
import fr.eni.eniencheredr.bo.Encheres;
import fr.eni.eniencheredr.dal.ArticleDAO.ArticleDAO;
import fr.eni.eniencheredr.dal.EnchereDAO.EnchereDAO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("enchereService")
public class EnchereServiceImpl implements EnchereService{
    private final EnchereDAO enchereDAO;
    private final ArticleDAO articleDAO;
    EnchereServiceImpl(EnchereDAO enchereDAO, ArticleDAO articleDAO) {
        this.enchereDAO = enchereDAO;
        this.articleDAO = articleDAO;
    }

    @Override
    public List<Encheres> getEncheres() {
        return enchereDAO.findAllEncheres();
    }

    @Override
    public Encheres getEnchere(Integer no_article) {
        return null;
    }

    @Override
    public void addEnchere(Encheres encheres) {
        enchereDAO.saveEnchere(encheres);
    }

    @Override
    public Encheres findById(Integer no_article) {
        return null;
    }

    @Override
    public void updateEnchere(Encheres encheres) {
        enchereDAO.updateEnchere(encheres);
    }

    @Override
    public void deleteEnchere(Encheres encheres) {

    }
}
