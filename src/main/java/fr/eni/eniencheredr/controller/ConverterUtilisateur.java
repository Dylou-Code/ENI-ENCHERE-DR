package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.service.CategorieService.CategorieServiceImpl;
import fr.eni.eniencheredr.service.UtilisateurService.UtilisateurService;
import fr.eni.eniencheredr.service.UtilisateurService.UtilisateurServiceImpl;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ConverterUtilisateur implements Converter<String, Utilisateurs> {
    private UtilisateurServiceImpl service;

    public ConverterUtilisateur(UtilisateurServiceImpl service) {
        this.service = service;
    }

    public Utilisateurs convert(String no_utilisateur) {
        Integer theid = Integer.parseInt(no_utilisateur);

        return service.findById(theid);
    }
}
