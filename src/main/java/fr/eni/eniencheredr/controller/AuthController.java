package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.service.UtilisateurService.UtilisateurService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
public class AuthController {

    private UtilisateurService utilisateurService;

    public AuthController(UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/register")
    public String register(@ModelAttribute("utilisateurs") Utilisateurs utilisateurs) {

        return "register";
    }

    @PostMapping("/registerUser")
    public String ajouterUser(@ModelAttribute("utilisateurs") Utilisateurs utilisateur) {
       /* if(validationResult.hasErrors()) {
            return "form";
        }*/
        System.out.println(utilisateur);
        utilisateurService.addUtilisateur(utilisateur);
        return "redirect:/encheres";
    }

    @GetMapping("/login")
    public String login() {
        /* modele.addAttribute("utilisateurs", new Utilisateurs());*/
        return "login";
    }

    @PostMapping("/login")
    /*Pour l'envoi du formulaire*/
    public String connexion(@ModelAttribute("utilisateurs") Utilisateurs utilisateurs) {
        return "redirect:/";
    }

    @GetMapping(value="/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}