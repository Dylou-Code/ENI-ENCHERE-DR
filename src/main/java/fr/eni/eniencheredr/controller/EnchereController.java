package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Categories;
import fr.eni.eniencheredr.service.CategorieService.CategorieService;
import fr.eni.eniencheredr.service.EnchereService.EnchereService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/encheres")
public class EnchereController {
    private EnchereService enchereService;
    private CategorieService categorieService;
    public EnchereController(EnchereService enchereService, CategorieService categorieService) {
        this.enchereService = enchereService;
        this.categorieService = categorieService;
    }
    @GetMapping
    public String homePage(Model modele) {
        List<Categories> categories =  categorieService.getCategories();
        modele.addAttribute("categories", categories);
        return "index";
    }





}
