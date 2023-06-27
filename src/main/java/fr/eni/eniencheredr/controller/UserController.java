package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.service.CategorieService.CategorieService;
import fr.eni.eniencheredr.service.EnchereService.EnchereService;
import fr.eni.eniencheredr.service.UtilisateurService.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UtilisateurService utilisateurService;

    public UserController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    /*@GetMapping
    public String user() {

        return "test";
    }*/
    @GetMapping("/register")
    public String register(@ModelAttribute("utilisateurs") Utilisateurs utilisateurs) {

        return "register";
    }

    @PostMapping("/save")
    public String ajouterFilm(@ModelAttribute("utilisateurs") Utilisateurs utilisateur) {
       /* if(validationResult.hasErrors()) {
            return "form";
        }*/
        utilisateurService.addUtilisateur(utilisateur);
        /*int genreId = Integer.parseInt(film.getGenre().getId());*/

        System.out.println("Mon user ajouter" + utilisateur);
        return "redirect:/encheres";
    }

    @GetMapping("/login")
    public String login(@ModelAttribute ("utilisateurs") Utilisateurs utilisateurs) {
       /* modele.addAttribute("utilisateurs", new Utilisateurs());*/
        return "login";
    }

    @PostMapping("/login")
    /*Pour l'envoi du formulaire*/
    public String Connexion() {

        return "login";
    }
}
