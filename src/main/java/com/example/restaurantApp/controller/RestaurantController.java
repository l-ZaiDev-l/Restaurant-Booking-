package com.example.restaurantApp.controller;

import com.example.restaurantApp.models.Restaurant;
import com.example.restaurantApp.service.impl.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class RestaurantController {

    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/Restaurants")
    public String getRestaurants(Model model) {
        model.addAttribute("restaurants", restaurantService.getAllRestaurants());
        return "restaurants";
    }

    @GetMapping("/Restaurants/search")
    public String searchRestaurants(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "city", required = false) String city,
            @RequestParam(value = "address", required = false) String address,
            Model model) {

        List<Restaurant> restaurants = restaurantService.findRestaurants(name, city, address);
        model.addAttribute("restaurants", restaurants);
        return "restaurants";
    }
}
