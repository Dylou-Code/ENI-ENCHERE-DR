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
        System.out.println(utilisateurDAO.findAllUtilisateurs());
        return utilisateurDAO.findAllUtilisateurs();
    }

    @Override
    public Utilisateurs getUtilisateur(Integer no_utilisateur) {
        return utilisateurDAO.findUtilisateurById(no_utilisateur);
    }

    @Override
    public void addUtilisateur(Utilisateurs utilisateurs) {
        utilisateurDAO.saveUtilisateur(utilisateurs);
    }

    @Override
    public Utilisateurs findById(Integer no_utilisateur) {
        return utilisateurDAO.findUtilisateurById(no_utilisateur);
    }

    @Override
    public void updateUtilisateur(Utilisateurs utilisateurs) {
        utilisateurDAO.updateUtilisateur(utilisateurs);
    }

    @Override
    public void deleteUtilisateur(Utilisateurs utilisateurs) {
        utilisateurDAO.deleteUtilisateur(utilisateurs);
    }
}
