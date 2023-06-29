package fr.eni.eniencheredr.service.UtilisateurService;

import fr.eni.eniencheredr.bo.Utilisateurs;

import java.util.List;

public interface UtilisateurService {
    List<Utilisateurs> getUtilisateurs();

    void addUtilisateur(Utilisateurs utilisateurs);

    Utilisateurs findById(Integer no_utilisateur);
    Utilisateurs findByPseudo(String pseudo);

    void updateUtilisateur(Utilisateurs utilisateurs);

    void deleteUtilisateur(Utilisateurs utilisateurs);
}
