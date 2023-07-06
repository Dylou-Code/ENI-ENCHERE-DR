package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Articles_Vendus;
import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.bo.Encheres;
import fr.eni.eniencheredr.bo.Utilisateurs;
import fr.eni.eniencheredr.dal.UtilisateurDAO.UtilisateurDAO;
import fr.eni.eniencheredr.exception.UserNotFoundException;
import fr.eni.eniencheredr.service.ArticleService.ArticleService;
import fr.eni.eniencheredr.service.CategorieService.CategorieService;
import fr.eni.eniencheredr.service.EnchereService.EnchereService;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
    public String homePage(Model modele, Authentication authentication) throws UserNotFoundException {
        List<Categories> categories =  categorieService.getCategories();
        List<Articles_Vendus> articles =  articleService.findAllArticles();

        if (authentication != null && authentication.isAuthenticated()){
            String name = authentication.getName();
            Utilisateurs user = utilisateurDAO.findUtilisateurByPseudo(name);
            if (user == null) {
                throw new UserNotFoundException("Utilisateur connecté non trouvée");
            }
            modele.addAttribute("utilisateurs", user);
        }

        modele.addAttribute("categories", categories);
        modele.addAttribute("articles", articles);
        modele.addAttribute("articleSearch", new Articles_Vendus());
        return "index";
    }


    @PostMapping("/search")
    public String searchArticle(Model model, @ModelAttribute("articles") Articles_Vendus articles){
        List<Articles_Vendus> resultList = articleService.findByName(articles.getNom_article());
        model.addAttribute("articleSearch", new Articles_Vendus());
        model.addAttribute("articles", resultList);
        return "index";
    }

    //filtre des checkbox
    @RequestMapping ("/searchFilter")
    public String searchFilter(@RequestParam("type") String type, Model model ,Authentication authentication ) throws UserNotFoundException {
        if (authentication != null && authentication.isAuthenticated()){
            String name = authentication.getName();
            Utilisateurs user = utilisateurDAO.findUtilisateurByPseudo(name);
            if (user == null) {
                throw new UserNotFoundException("Utilisateur connecté non trouvée");
            }
            List<Encheres> enchere = new ArrayList<>();

            if ("enchOuverte".equals(type)) {
                enchere = enchereService.getEncheres(user.getNo_utilisateur());
            }
            model.addAttribute("utilisateurs", user);
            model.addAttribute("encheres", enchere);
        }
        /*List<Articles_Vendus> articles = new ArrayList<>();*/


       /* if ("achat".equals(type)) {
            articles = articleService.findArticlesAchat();
        } */



        /*else if ("enchOuverte".equals(type)) {
            articles = articleService.findArticlesEnchOuverte();
        } else if ("enchEnCours".equals(type)) {
            articles = articleService.findArticlesEnchEnCours();
        } else if ("enchRemporte".equals(type)) {
            articles = articleService.findArticlesEnchRemporte();
        }*/

        /*model.addAttribute("articles", articles);*/


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
    public String saveArticle(@ModelAttribute("articles") Articles_Vendus articlesVendus, Authentication authentication) throws UserNotFoundException {
       /* if(validationResult.hasErrors()) {
            return "form";
        }*/

        //Récupère le nom de l'utilisateur, fait une requete par nom et passe l'id de la caregorie et de l'utilisateur associé a l'article
        if (authentication.isAuthenticated()){

            /*if (user1 == null) {
                throw new UserNotFoundException("Utilisateur connecté non trouvée");
            }*/
            String name = authentication.getName();
            Utilisateurs user1 = utilisateurDAO.findUtilisateurByPseudo(name);
            articlesVendus.setUtilisateurs(user1);
            Categories cat1 = categorieService.getCategory(articlesVendus.getCategories().getNo_categorie());
            articlesVendus.setCategories(cat1);
            articleService.saveArticle(articlesVendus);
            Date date = new Date();
            System.out.println(date);
            Encheres ench = new Encheres(user1.getNo_utilisateur(), articlesVendus.getNo_article(), date, 0);
            enchereService.addEnchere(ench);
        }else  {
            return "redirect:/403";
        }
        return "redirect:/";
    }

    @GetMapping("/vente")
    public String enchere(@RequestParam(name="no_article") Integer no_article, Model modeleArticle, Model modeleUser,
                          Model myAuction,
                          Authentication authentication) throws UserNotFoundException {
        Articles_Vendus article = articleService.findArticleById(no_article);
        modeleArticle.addAttribute("articles", article);
        Boolean monEnchere = false;
        Boolean encherir = true;

        //Récupère l'utilisateur connecté
        if (authentication != null && authentication.isAuthenticated()){
            String name = authentication.getName();
            Utilisateurs user = utilisateurDAO.findUtilisateurByPseudo(name);
            if (user == null) {
                throw new UserNotFoundException("Utilisateur connecté non trouvée");
            }
            modeleUser.addAttribute("utilisateur", user);

            //Récupère l'enchère concerné
            Encheres ench = enchereService.findById(article.getNo_article());

            //Compare l'id utilisateur et l'id de l'enchère
            // Utilisateur peux encherir mais pas annuler
            if (user.getNo_utilisateur().equals(ench.getNo_utilisateur())){
                monEnchere = true;
                encherir = false;
            }

            // Utilisateur peut annuler la vente
            if (user.getNo_utilisateur() != ench.getNo_utilisateur()) {
                encherir = true;
                monEnchere = false;
            }
            myAuction.addAttribute("monEnchere", monEnchere);
            myAuction.addAttribute("peutEncherir", encherir);
        }


        return "encherir";
    }

    @PostMapping("/auction")
    public String encherir(@ModelAttribute("articles") Articles_Vendus articlesVendus,
                           Authentication authentication) throws UserNotFoundException {
        //Récupère le nom de l'utilisateur, fait une requete par nom et passe l'id de la categorie et de l'utilisateur associé a l'article
        Utilisateurs user = utilisateurDAO.findUtilisateurByPseudo(authentication.getName());
        if (user == null) {
            throw new UserNotFoundException("Utilisateur connecté non trouvée");
        }
        Date date = new Date();
        Articles_Vendus articlePage = articleService.findArticleById(articlesVendus.getNo_article());

        Categories cat1 = categorieService.getCategory(articlesVendus.getCategories().getNo_categorie());
        articlesVendus.setUtilisateurs(user);
        articlesVendus.setCategories(cat1);

        // ----------------------------------------- Verification du montant -----------------------------------------
        // Si le prix est supérieure à la meilleur offre
        if (articlesVendus.getPrix_vente() < articlePage.getPrix_vente() && articlesVendus.getPrix_vente() < Integer.MAX_VALUE){
            //System.out.println("Inférieur a la meilleur offre : " + articlesVendus.getPrix_vente());
            return "redirect:/vente?no_article=" + articlesVendus.getNo_article();
        }

        //Si le prix est supérieure au prix initial
        if (articlesVendus.getPrix_vente() < articlePage.getPrix_initial()){
            //System.out.println("Inférieur au prix initial" + articlePage.getPrix_initial());
            return "redirect:/vente?no_article=" + articlesVendus.getNo_article();
        }

        // ----------------------------------------- Verificationde la date -----------------------------------------
        System.out.println("true" + date);
        if ((articlePage.getDate_debut_encheres() != null) && (articlePage.getDate_fin_encheres() != null)){
            if (date.before(articlePage.getDate_debut_encheres()) || date.after(articlePage.getDate_fin_encheres())){
                return "redirect:/vente?no_article=" + articlesVendus.getNo_article();
            }
        }

        //Fonction ecnherir
        Encheres offre = new Encheres(user.getNo_utilisateur(), articlesVendus.getNo_article(), date, articlesVendus.getPrix_vente());

        enchereService.updateEnchere(offre);
        articleService.encherirArticle(articlesVendus);
        return "redirect:/vente?no_article=" + articlesVendus.getNo_article();
    }

    @GetMapping("/delete")
    public String deleteEnchere(@ModelAttribute("articles") Articles_Vendus articles,
                                Authentication authentication) throws UserNotFoundException {
        Boolean monEnchere = false;
        // Récupère l'utilisateur
        String name = authentication.getName();
        Utilisateurs user = utilisateurDAO.findUtilisateurByPseudo(name);

        //Verifie si il est trouvé
        if (user == null) {
            throw new UserNotFoundException("Utilisateur connecté non trouvée");
        }

        //Récupère l'article'
        Encheres ench = enchereService.findById(articles.getNo_article());

        //Supprimer l'enchère et l'article
        enchereService.deleteEnchere(ench);
        articleService.deleteArticle(articles);


        return "redirect:/";
    }

    @GetMapping("/acquisition")
    public String acquisition() {
        return "acquisition";
    }

}
