package fr.eni.eniencheredr.service.EnchereService;

import fr.eni.eniencheredr.bo.Encheres;
import fr.eni.eniencheredr.dal.EnchereDAO.EnchereDAO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service("enchereService")
public class EnchereServiceImpl implements EnchereService{
    private EnchereDAO enchereDAO;

    EnchereServiceImpl(EnchereDAO enchereDAO) {
        this.enchereDAO = enchereDAO;
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

    }

    @Override
    public Encheres findById(Integer no_article) {
        return null;
    }

    @Override
    public void updateUtilisateur(Encheres encheres) {

    }

    @Override
    public void deleteUtilisateur(Encheres encheres) {

    }

}
