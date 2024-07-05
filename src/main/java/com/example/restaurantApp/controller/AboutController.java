package com.example.restaurantApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AboutController {
    @GetMapping("/About")
    public String getAbout(Model model) {
        return "about";
    }
}
