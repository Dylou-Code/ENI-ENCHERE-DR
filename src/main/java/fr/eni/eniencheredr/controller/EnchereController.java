package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Articles_Vendus;
import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.service.ArticleService.ArticleService;
import fr.eni.eniencheredr.service.CategorieService.CategorieService;
import fr.eni.eniencheredr.service.EnchereService.EnchereService;
import fr.eni.eniencheredr.service.UtilisateurService.UtilisateurService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping({"/", "/encheres"})
public class EnchereController {
    private EnchereService enchereService;
    private CategorieService categorieService;

    private ArticleService articleService;


    public EnchereController(EnchereService enchereService, CategorieService categorieService, ArticleService articleService) {
        this.enchereService = enchereService;
        this.categorieService = categorieService;
        this.articleService = articleService;
    }
    @GetMapping
    public String homePage(Model modele) {
        List<Categories> categories =  categorieService.getCategories();
        List<Articles_Vendus> articles =  articleService.findAllArticles();
        modele.addAttribute("categories", categories);
        modele.addAttribute("articles", articles);
        System.out.println(articles);
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
