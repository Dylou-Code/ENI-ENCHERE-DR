package fr.eni.eniencheredr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/encheres")
public class EnchereController {
    @GetMapping
    public String homePage() {

        return "index";
    }

}
