package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Articles_Vendus;
import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Encheres;
import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.dal.UtilisateurDAO.UtilisateurDAO;
import fr.eni.eniencheredr.service.ArticleService.ArticleService;
import fr.eni.eniencheredr.service.CategorieService.CategorieService;
import fr.eni.eniencheredr.service.EnchereService.EnchereService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.util.Date;
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

        //Récupère le nom de l'utilisateur, fait une requete par nom et passe l'id de la caregorie et de l'utilisateur associé a l'article
        if (authentication.isAuthenticated()){
            String name = authentication.getName();
            Date date = new Date();
            Utilisateurs user1 = utilisateurDAO.findUtilisateurByPseudo(name);
            Categories cat1 = categorieService.getCategory(articlesVendus.getCategories().getNo_categorie());

            articlesVendus.setUtilisateurs(user1);
            articlesVendus.setCategories(cat1);
            articleService.saveArticle(articlesVendus);

            Encheres ench = new Encheres(user1.getNo_utilisateur(), articlesVendus.getNo_article(), date, 0);
            enchereService.addEnchere(ench);
        }else  {
            return "redirect:/403";
        }
        return "redirect:/";
    }

    @GetMapping("/vente")
    public String enchere(@RequestParam(name="no_article") Integer no_article, Model modeleArticle) {
        Articles_Vendus article = articleService.findArticleById(no_article);
        modeleArticle.addAttribute("articles", article);
        return "encherir";
    }

    @PostMapping("/auction")
    public String encherir(@ModelAttribute("articles") Articles_Vendus articlesVendus,
                           Authentication authentication) {
        Utilisateurs user = utilisateurDAO.findUtilisateurByPseudo(authentication.getName());
        Date date = new Date();

/*        System.out.println(user);
        System.out.println(articlesVendus.getPrix_vente());
        System.out.println(articlesVendus.getNo_article());
*/
        //Récupère le nom de l'utilisateur, fait une requete par nom et passe l'id de la categorie et de l'utilisateur associé a l'article
        String name = authentication.getName();
        Utilisateurs user1 = utilisateurDAO.findUtilisateurByPseudo(name);
        Categories cat1 = categorieService.getCategory(articlesVendus.getCategories().getNo_categorie());
        articlesVendus.setUtilisateurs(user1);
        articlesVendus.setCategories(cat1);

        //Fonction enchérir
/*
        modeleEnchere.addAttribute("encheres", enchereService.findById(articlesVendus.getNo_article()));
*/

        Encheres offre = new Encheres(user.getNo_utilisateur(), articlesVendus.getNo_article(), date, articlesVendus.getPrix_vente());
        System.out.println(offre);
        enchereService.updateEnchere(offre);
        articleService.encherirArticle(articlesVendus);
        return "redirect:/vente?no_article=" + articlesVendus.getNo_article();
    }

    @GetMapping("/acquisition")
    public String acquisition() {
        return "acquisition";
    }

}
