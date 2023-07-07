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
        utilisateurs.setMot_de_passe(bcrypt.encode(utilisateurs.getMot_de_passe()));
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
        System.out.println(utilisateurs);
        utilisateurs.setCredit(0);
        Utilisateurs user = utilisateurDAO.findUtilisateurById(utilisateurs.getNo_utilisateur());

        if (utilisateurs.getMot_de_passe() == null || utilisateurs.getMot_de_passe() == ""){
            System.out.println("mot de passe vide");

            utilisateurs.setMot_de_passe(user.getMot_de_passe());

        }else {
            System.out.println(user);
            utilisateurs.setMot_de_passe(bcrypt.encode(utilisateurs.getMot_de_passe()));

        }
        utilisateurDAO.updateUtilisateur(utilisateurs);
    }

    @Override
    public void deleteUtilisateur(Utilisateurs utilisateurs) {
        utilisateurDAO.deleteUtilisateur(utilisateurs);
    }
}
