package com.example.restaurantApp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/Home")
    public String blog(Model model) {
        // Add attributes to the model as needed
        return "index";
    }

}
