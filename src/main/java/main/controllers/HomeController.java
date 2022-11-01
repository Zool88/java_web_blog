package main.controllers;

import main.variables.VariablesClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    VariablesClass variables = new VariablesClass();

    @GetMapping("/")
    public String nome(Model model) {
        model.addAttribute("favicon" , variables.getFavicon());
        model.addAttribute("title", variables.getHome());
        model.addAttribute("logoHeight", variables.getLogoHeight());
        model.addAttribute("logoWidth", variables.getLogoWidth());
        model.addAttribute("hat" , variables.getHat());
        return "home";
    }
}
