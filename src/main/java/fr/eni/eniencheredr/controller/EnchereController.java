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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
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
    public String searchFilter(@RequestParam("type") String type, Model model ,Authentication authentication) throws UserNotFoundException {
        if (authentication != null && authentication.isAuthenticated()){
            String name = authentication.getName();
            Utilisateurs user = utilisateurDAO.findUtilisateurByPseudo(name);
            if (user == null) {
                throw new UserNotFoundException("Utilisateur connecté non trouvée");
            }

            /*Appel des filtres*/
            /*Ventes terminé*/
            if ("venteTerminé".equals(type)) {
                List<Articles_Vendus> articles =  articleService.findByDateInf(user.getNo_utilisateur());
                model.addAttribute("articles", articles);

                System.out.println(articles);
            }

            /*Toutes mes ventes*/
            if ("venteEnCours".equals(type)) {
                List<Articles_Vendus> articles =  articleService.findMyArticles(user.getNo_utilisateur());
                model.addAttribute("articles", articles);
                System.out.println(articles);
            }

            /*Toutes mes encheres*/
            if ("enchEnCours".equals(type)) {
                List<Articles_Vendus> articles =  articleService.findMyAuction(user.getNo_utilisateur());
                model.addAttribute("articles", articles);
                System.out.println(articles);
            }

            model.addAttribute("utilisateurs", user);

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
    public String saveArticle(@ModelAttribute("articles") Articles_Vendus articlesVendus, @RequestParam("image") MultipartFile imageFile, Authentication authentication) throws UserNotFoundException {
       /* if(validationResult.hasErrors()) {
            return "form";
        }*/

        //Récupère le nom de l'utilisateur, fait une requete par nom et passe l'id de la caregorie et de l'utilisateur associé a l'article
        if (authentication.isAuthenticated()){
            String name = authentication.getName();
            Date date = new Date();
            Utilisateurs user1 = utilisateurDAO.findUtilisateurByPseudo(name);
            if (user1 == null) {
                throw new UserNotFoundException("Utilisateur connecté non trouvée");
            }
            Categories cat1 = categorieService.getCategory(articlesVendus.getCategories().getNo_categorie());


            /*ajout image*/
            if (!imageFile.isEmpty()) {
                try {
                    // Générer un nom de fichier unique basé sur l'heure actuelle
                    String fileName = StringUtils.cleanPath(new Date().getTime() + "-" + StringUtils.getFilename(imageFile.getOriginalFilename()));
                    String uploadDir = "./src/main/resources/static/images";

                    // Copier le fichier dans le dossier d'images statiques
                    java.nio.file.Path uploadPath = Paths.get(uploadDir);
                    if (!Files.exists(uploadPath)) {
                        Files.createDirectories(uploadPath);
                    }
                    Path filePath = uploadPath.resolve(fileName);
                    Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

                    // Enregistrer le lien de l'image dans la base de données
                    String imageLink = "/images/" + fileName;
                   /* String imageLink = converter.convert(imageFile);*/
                    articlesVendus.setImageLink(imageLink);
                    // ... autres logiques de création d'article ...
                } catch (IOException e) {
                    // Gérer les erreurs lors du téléchargement et de l'enregistrement de l'image
                    e.printStackTrace();
                }
            }


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
    public String enchere(@RequestParam(name="no_article") Integer no_article, Model modeleArticle, Model modeleUser,
                          Authentication authentication) throws UserNotFoundException {
        Articles_Vendus article = articleService.findArticleById(no_article);
        modeleArticle.addAttribute("articles", article);

        if (authentication != null && authentication.isAuthenticated()){
            String name = authentication.getName();
            Utilisateurs user = utilisateurDAO.findUtilisateurByPseudo(name);
            if (user == null) {
                throw new UserNotFoundException("Utilisateur connecté non trouvée");
            }
            modeleUser.addAttribute("utilisateur", user);
        }
        return "encherir";
    }

    @PostMapping("/auction")
    public String encherir(@ModelAttribute("articles") Articles_Vendus articlesVendus,
                           Authentication authentication) throws UserNotFoundException {
        Utilisateurs user = utilisateurDAO.findUtilisateurByPseudo(authentication.getName());
        if (user == null) {
            throw new UserNotFoundException("Utilisateur connecté non trouvée");
        }
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

    @GetMapping("/delete")
    public String deleteEnchere(@ModelAttribute("articles") Articles_Vendus articles, Authentication authentication) throws UserNotFoundException {
        if (authentication != null && authentication.isAuthenticated()){
            String name = authentication.getName();
            Utilisateurs user = utilisateurDAO.findUtilisateurByPseudo(name);
            if (user == null) {
                throw new UserNotFoundException("Utilisateur connecté non trouvée");
            }
            Encheres ench = enchereService.findById(articles.getNo_article());
            //Articles_Vendus article = articleService.findArticleById(articles.getNo_article());
            System.out.println(ench);
            enchereService.deleteEnchere(ench);
            articleService.deleteArticle(articles);
        }
        return "redirect:/";
    }

    @GetMapping("/acquisition")
    public String acquisition() {
        return "acquisition";
    }

}
