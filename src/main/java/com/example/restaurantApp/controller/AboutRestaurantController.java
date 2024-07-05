package com.example.restaurantApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutRestaurantController {
    @GetMapping("/AboutRestaurant")
    public String AboutRestaurantController(Model model){
        return "aboutRestaurant";
    }
}
