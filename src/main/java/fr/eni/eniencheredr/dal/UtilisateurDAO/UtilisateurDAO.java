package fr.eni.eniencheredr.dal.UtilisateurDAO;

import fr.eni.eniencheredr.bo.Utilisateurs;

import java.util.List;

public interface UtilisateurDAO {
    List<Utilisateurs> findAllUtilisateurs();
    Utilisateurs findUtilisateurById(Integer no_utilisateur);
    Utilisateurs findUtilisateurByPseudo(String pseudo);
    void saveUtilisateur(Utilisateurs utilisateur);
    void updateUtilisateur(Utilisateurs utilisateur);
    void deleteUtilisateur(Utilisateurs utilisateurs);
}
