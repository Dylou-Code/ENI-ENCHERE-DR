package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.service.CategorieService.CategorieService;
import fr.eni.eniencheredr.service.EnchereService.EnchereService;
import fr.eni.eniencheredr.service.UtilisateurService.UtilisateurService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping({"/", "/encheres"})
public class EnchereController {
    private EnchereService enchereService;
    private CategorieService categorieService;
    private UtilisateurService utilisateurService;


    public EnchereController(EnchereService enchereService, CategorieService categorieService, UtilisateurService utilisateurService) {
        this.enchereService = enchereService;
        this.categorieService = categorieService;
        this.utilisateurService = utilisateurService;
    }
    @GetMapping
    public String homePage(Model modele, Principal user) {

        List<Categories> categories =  categorieService.getCategories();
        modele.addAttribute("categories", categories);
        return "index";
    }

    @GetMapping("/addArticle")
    public String addArticle() {


        return "addArticle";
    }

    @GetMapping("/mon-enchere")
    public String enchere() {
        return "enchere";
    }

}
