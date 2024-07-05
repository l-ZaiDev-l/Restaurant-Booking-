package com.example.restaurantApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.restaurantApp.models.Restaurant;
import com.example.restaurantApp.service.impl.RestaurantService;

@Controller
public class SingleController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants/{id}")
    public String single(@PathVariable("id") Long id, Model model) {
        // Fetch the restaurant by ID
        Restaurant restaurant = restaurantService.getRestaurantById(id);

        // Add the restaurant to the model
        model.addAttribute("restaurant", restaurant);

        return "single";
    }
}
