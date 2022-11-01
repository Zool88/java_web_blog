package main.controllers;

import main.variables.VariablesClass;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    VariablesClass variables = new VariablesClass();


    @GetMapping("/about")
    public String about(Model model){
        model.addAttribute("favicon" , variables.getFavicon());
        model.addAttribute("title", variables.getHomeAbout());
        model.addAttribute("logoHeight", variables.getLogoHeight());
        model.addAttribute("logoWidth", variables.getLogoWidth());
        model.addAttribute("hat" , variables.getHatAbout());
        return "about";
    }
}
