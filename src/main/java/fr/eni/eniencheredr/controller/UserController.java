package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.service.CategorieService.CategorieService;
import fr.eni.eniencheredr.service.EnchereService.EnchereService;
import fr.eni.eniencheredr.service.UtilisateurService.UtilisateurService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
    public String register(Model model) {
        List<Utilisateurs> utilisateurs = utilisateurService.getUtilisateurs();
        model.addAttribute("utilisateurs", utilisateurs);
        return "register";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
