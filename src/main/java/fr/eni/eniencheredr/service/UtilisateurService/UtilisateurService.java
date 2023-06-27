package fr.eni.eniencheredr.service.UtilisateurService;

import fr.eni.eniencheredr.bo.Utilisateurs;

import java.util.List;

public interface UtilisateurService {
    List<Utilisateurs> getUtilisateurs();

    Utilisateurs getUtilisateur(Integer no_utilisateur);
}
