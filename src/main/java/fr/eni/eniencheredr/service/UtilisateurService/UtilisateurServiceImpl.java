package fr.eni.eniencheredr.service.UtilisateurService;

import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.dal.UtilisateurDAO.UtilisateurDAO;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurServiceImpl implements UtilisateurService {
    private final BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();

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
    public void addUtilisateur(Utilisateurs utilisateurs) {
        String mdp = utilisateurs.getMot_de_passe();
        utilisateurs.setMot_de_passe(bcrypt.encode(mdp));
        System.out.println(utilisateurs);
            try {
                utilisateurDAO.saveUtilisateur(utilisateurs);
                System.out.println("Utilisateur ajouté");
            }catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Override
    public Utilisateurs findById(Integer no_utilisateur) {
        return utilisateurDAO.findUtilisateurById(no_utilisateur);
    }

    @Override
    public Utilisateurs findByPseudo(String pseudo) {
        return utilisateurDAO.findUtilisateurByPseudo(pseudo);
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
