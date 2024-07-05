package com.example.restaurantApp.controller;

import com.example.restaurantApp.models.User;
import com.example.restaurantApp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpServletResponse;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class SignInController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SignInController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String loginPage(Model model) {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username, @RequestParam String password, HttpSession session) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                // Authentification réussie : mettre l'utilisateur en session
                session.setAttribute("user", user);
                return "redirect:/Home"; // Redirection vers la page d'accueil après l'authentification
            }
        }
        // Si l'authentification échoue, rediriger vers la page de login avec un message d'erreur
        return "redirect:/login?error";
    }

    @GetMapping("/signup")
    public String signUpForm(Model model) {
        model.addAttribute("user", new User());
        return "login"; // Assuming your sign-up form is named signup.html
    }


    @PostMapping("/signup")
    public String signUpSubmit(@RequestParam("username") String username,
                               @RequestParam("email") String email,
                               @RequestParam("password") String password) {
        User user = new User();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(password));

        userRepository.save(user);
        return "redirect:/Home"; // Redirect to login page after successful sign-up
    }
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute("user");
        return "redirect:/Home";
    }

}
