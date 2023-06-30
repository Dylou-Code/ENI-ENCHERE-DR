package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Articles_Vendus;
import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.dal.UtilisateurDAO.UtilisateurDAO;
import fr.eni.eniencheredr.service.ArticleService.ArticleService;
import fr.eni.eniencheredr.service.CategorieService.CategorieService;
import fr.eni.eniencheredr.service.EnchereService.EnchereService;
import fr.eni.eniencheredr.service.UtilisateurService.UtilisateurService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping({"/", "/encheres"})
public class EnchereController {
    private EnchereService enchereService;
    private CategorieService categorieService;

    private ArticleService articleService;
    private UtilisateurDAO utilisateurDAO;


    public EnchereController(EnchereService enchereService, CategorieService categorieService, ArticleService articleService, UtilisateurDAO utilisateurDAO) {
        this.enchereService = enchereService;
        this.categorieService = categorieService;
        this.articleService = articleService;
        this.utilisateurDAO = utilisateurDAO;
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
    public String addArticle(Model model) {
        List<Categories> listCategories =  categorieService.getCategories();
        model.addAttribute("categories", listCategories);
        model.addAttribute("articles", new Articles_Vendus());
        return "addArticle";
    }

    @PostMapping("/save")
    public String saveArticle(@ModelAttribute("articles") Articles_Vendus articlesVendus, Authentication authentication) {
       /* if(validationResult.hasErrors()) {
            return "form";
        }*/
        if (authentication.isAuthenticated()){
            String name = authentication.getName();
            Utilisateurs user1 = utilisateurDAO.findUtilisateurByPseudo(name);
            Categories cat1 = categorieService.getCategory(articlesVendus.getCategories().getNo_categorie());
            articlesVendus.setUtilisateurs(user1);
            articlesVendus.setCategories(cat1);
            articleService.saveArticle(articlesVendus);

        }else  {
            return "redirect:/403";
        }
        /*int genreId = Integer.parseInt(film.getGenre().getId());*/

        System.out.println("Mon article ajouter" + articlesVendus);
        return "redirect:/";
    }

    @GetMapping("/vente/{no_article}")
    public String enchere(@RequestParam(name="no_article") Integer no_article,Model modeleArticle) {

        modeleArticle.addAttribute(enchereService.getEnchere(no_article));
        return "enchere";
    }

}
