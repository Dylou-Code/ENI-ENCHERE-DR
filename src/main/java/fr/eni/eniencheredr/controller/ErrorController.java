package fr.eni.eniencheredr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class ErrorController {

    /*Page d'erreur*/
    @GetMapping("/404")
    public String page404() {
        return "ErrorPage/404";
    }

    @GetMapping("/403")
    public String page403() {
        return "ErrorPage/403";
    }
}
