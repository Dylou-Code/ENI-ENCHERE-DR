package fr.eni.eniencheredr.service.UtilisateurService;

import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.dal.UtilisateurDAO.UtilisateurDAO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    private final UtilisateurDAO utilisateurDAO;

    public UtilisateurServiceImpl(UtilisateurDAO utilisateurDAO) {
        this.utilisateurDAO = utilisateurDAO;
    }

    @Override
    public List<Utilisateurs> getUtilisateurs() {
        return utilisateurDAO.findAllUtilisateurs();
    }

    @Override
    public Utilisateurs getUtilisateur(Integer no_utilisateur) {
        return utilisateurDAO.findUtilisateurById(no_utilisateur);
    }

    /*@Override
    public Utilisateurs addUtilisateur(Utilisateurs utilisateurs) {
        return null;
    }*/
}
