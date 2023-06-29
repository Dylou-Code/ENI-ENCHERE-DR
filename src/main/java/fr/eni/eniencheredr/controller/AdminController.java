package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.service.UtilisateurService.UtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private UtilisateurService utilisateurService;

    @Autowired
    public AdminController( UtilisateurService utilisateurService) {
        this.utilisateurService = utilisateurService;
    }

    @GetMapping("/dashboard")
    public String dashboard(Model modele, Principal user) {
        //Afficher des utilisateurs
        List<Utilisateurs> listUsers = utilisateurService.getUtilisateurs();
        modele.addAttribute("utilisateurs", listUsers);

        return "Admin/dashboard";
    }

    /*Add users*/
    @GetMapping("/register")
    public String registerUser(@ModelAttribute("utilisateurs") Utilisateurs utilisateurs) {

        return "Admin/registerUserAdmin";
    }

    @PostMapping("/save")
    public String ajouterFilm(@ModelAttribute("utilisateurs") Utilisateurs utilisateur) {
       /* if(validationResult.hasErrors()) {
            return "form";
        }*/
        utilisateurService.addUtilisateur(utilisateur);
        /*int genreId = Integer.parseInt(film.getGenre().getId());*/

        System.out.println("Mon user ajouter" + utilisateur);
        return "redirect:/admin/dashboard";
    }

    @GetMapping("/detail-user")
    public String detailUser(@RequestParam(name="no_utilisateur") Integer no_utilisateur, Model model) {

        Utilisateurs user = utilisateurService.findById(no_utilisateur);
        model.addAttribute("utilisateurs",user);
        return "Admin/detailUserAdmin";
    }

    /*update*/
    @RequestMapping("/edit-user")
    public String editUser(@RequestParam(name= "no_utilisateur") Integer no_utilisateur, Model modele){
        Utilisateurs utilisateurs = utilisateurService.findById(no_utilisateur);
        modele.addAttribute("utilisateurs",utilisateurs);
        return "Admin/modifierProfilAdmin";
    }


   /* @GetMapping("/delete")*/
    @GetMapping("/delete")
    public String deleteUser(@ModelAttribute("utilisateurs") Utilisateurs utilisateurs) {
        utilisateurService.deleteUtilisateur(utilisateurs);
        return "redirect:/admin/dashboard";
    }
}
