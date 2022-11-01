package main.controllers;

import main.models.Post;
import main.repo.PostRepository;
import main.variables.VariablesClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    private VariablesClass variables = new VariablesClass();

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/blog")
    public String blog(Model model) {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        model.addAttribute("favicon", variables.getFavicon());
        model.addAttribute("title", variables.getHomeBlog());
        model.addAttribute("logoHeight", variables.getLogoHeightBlog());
        model.addAttribute("logoWidth", variables.getLogoWidthBLog());
        model.addAttribute("hat", variables.getHatBlog());

        return "blog";
    }

    @GetMapping("/blog/add")
    public String blogAdd(Model model) {
        model.addAttribute("favicon", variables.getFavicon());
        model.addAttribute("title", variables.getHomeBlog());
        model.addAttribute("logoHeight", variables.getLogoHeight());
        model.addAttribute("logoWidth", variables.getLogoWidth());
        model.addAttribute("hat", variables.getHatBlogAdd());

        return "blog-add";
    }

    @PostMapping("/blog/add")
    public String blogPostAdd(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text,
                              Model model) {

        Post post = new Post(title, anons, full_text);
        postRepository.save(post);
        return "redirect:/blog";
    }


    @GetMapping("/blog/{id}")
    public String blogDetails(@PathVariable(value = "id") long id, Model model) {
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }

        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res );
        model.addAttribute("hat", variables.getHatBlogDetails());
        model.addAttribute("title", variables.getHomeBlogDetails());
        model.addAttribute("favicon", variables.getFavicon());
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    public String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if(!postRepository.existsById(id)){
            return "redirect:/blog";
        }

        Optional<Post> post = postRepository.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("post", res );
        model.addAttribute("hat", variables.getHatBlogDetails());
        model.addAttribute("title", variables.getHomeBlogDetails());
        model.addAttribute("favicon", variables.getFaviconEdit());
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    public String blogPostUpdate(@RequestParam String title,
                              @RequestParam String anons,
                              @RequestParam String full_text,
                              @PathVariable(value = "id") long id,
                              Model model) {

        Post post = postRepository.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFull_text(full_text);

        postRepository.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/remove")
    public String blogPostDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepository.findById(id).orElseThrow();
        postRepository.delete(post);
        return "redirect:/blog";
    }

}







