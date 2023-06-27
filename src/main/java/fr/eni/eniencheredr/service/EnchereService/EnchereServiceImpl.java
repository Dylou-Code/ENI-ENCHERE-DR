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

}
