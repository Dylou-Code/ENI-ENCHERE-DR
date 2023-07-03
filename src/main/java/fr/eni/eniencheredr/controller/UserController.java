package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.dal.UtilisateurDAO.UtilisateurDAO;
import fr.eni.eniencheredr.service.CategorieService.CategorieService;
import fr.eni.eniencheredr.service.EnchereService.EnchereService;
import fr.eni.eniencheredr.service.UtilisateurService.UtilisateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

   private UtilisateurService utilisateurService;

    private UtilisateurDAO utilisateurDAO;

   @Autowired
   public UserController(UtilisateurService utilisateurService,  UtilisateurDAO utilisateurDAO) {
       this.utilisateurService = utilisateurService;
       this.utilisateurDAO = utilisateurDAO;
   }

    @GetMapping("/")
    public String navigation(@RequestParam(name="no_utilisateur") Integer user, Model model, Authentication authentication) {
        String name = authentication.getName();
        Utilisateurs user1 = utilisateurDAO.findUtilisateurByPseudo(name);
        String email = user1.getEmail();
        System.out.println(email);
        /*Utilisateurs user = utilisateurService.findById(no_utilisateur);*/
        model.addAttribute("utilisateurs",user);
        model.addAttribute("emailUser",email);
        return "layout/navigation";
    }

    @GetMapping("/mon-profil")
    public String monprofil() {
        return "monProfil";
    }

    @GetMapping("/profil")
    public String detailUser(@RequestParam(name="no_utilisateur") Integer no_utilisateur, Model model) {
        Utilisateurs user = utilisateurService.findById(no_utilisateur);
        model.addAttribute("utilisateurs",user);
        return "profil";
    }

    @GetMapping("/edit-profil")
    public String updateProfil() {
        return "modifierProfil";
    }

}
