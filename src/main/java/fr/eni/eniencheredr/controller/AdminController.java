package fr.eni.eniencheredr.controller;

import fr.eni.eniencheredr.bo.Utilisateurs;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/dashboard")
    public String dashboard(@ModelAttribute("utilisateurs") Utilisateurs utilisateurs) {
        //Affiche des utilisateurs
       /* List<Utilisateurs> utilisateurs = new ArrayList<>();*/
        return "Admin/dashboard";
    }
}
