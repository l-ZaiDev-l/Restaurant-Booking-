package com.example.restaurantApp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.restaurantApp.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

@Controller
public class ContactController {

    @Autowired
    private EmailService emailService;

    @GetMapping("/Contact") // Ajoutez cette annotation pour gérer les requêtes GET
    public String showContactForm() {
        return "contact"; // Assurez-vous d'avoir un fichier contact.html dans vos templates Thymeleaf
    }

    @PostMapping("/Contact")
    public String handleContactForm(
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("email") String email,
            @RequestParam("subject") String subject,
            @RequestParam("message") String message) {

        String fullMessage = "Name: " + name + "\n"
                + "Phone: " + phone + "\n"
                + "Email: " + email + "\n"
                + "Message: " + message;

        emailService.sendEmail("zaid2016hankrii@gmail.com", subject, fullMessage);

        return "redirect:/Home";
    }
}
