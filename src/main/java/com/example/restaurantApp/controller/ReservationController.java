package com.example.restaurantApp.controller;
import com.example.restaurantApp.models.Reservation;
import com.example.restaurantApp.models.Restaurant;
import com.example.restaurantApp.models.User;
import com.example.restaurantApp.service.impl.ReservationService;
import com.example.restaurantApp.service.impl.RestaurantService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RestaurantService restaurantService;



    @GetMapping("/Reservations")
    public String getReservations(Model model, HttpSession session) {
        // Retrieve the currently authenticated user from the session
        User user = (User) session.getAttribute("user");

        if (user != null) {
            // Fetch reservations specific to the logged-in user
            List<Reservation> reservations = reservationService.findReservationsByUser(user);
            model.addAttribute("reservations", reservations);
        } else {
            // Handle case where user is not authenticated
            // Redirect to the login page or handle as needed
            return "redirect:/login";
        }

        return "reservations"; // Return the name of your Thymeleaf template
    }





    @PostMapping("/cancelReservation/{reservationId}")
    public String cancelReservation(@PathVariable Long reservationId) {
        // Cancel the reservation by calling the service method
        reservationService.cancelReservation(reservationId);

        // Redirect back to the reservations page
        return "redirect:/Reservations";
    }











    // Endpoint for handling reservation form submission
    @PostMapping("/Reservate")
    public String reserve(@ModelAttribute("reservation") Reservation reservation,
                          @RequestParam("reservationDateTime") LocalDateTime reservationDateTime,
                          @RequestParam("numberOfPeople") int numberOfPeople,
                          @RequestParam("restaurantId") Long restaurantId,
                          HttpServletRequest request) {

        // Retrieve the currently authenticated user from the session
        HttpSession session = request.getSession(false);
        User user = (session != null) ? (User) session.getAttribute("user") : null;

        if (user != null) {
            // Fetch the restaurant by its ID from the database
            Restaurant restaurant = restaurantService.findRestaurantById(restaurantId);

            if (restaurant == null) {
                // Handle case where the restaurant with the given ID is not found
                return "redirect:/error"; // Redirect to an error page or handle as needed
            }

            // Associate the user with the reservation
            reservation.setUser(user);

            // Set the fetched restaurant to the reservation
            reservation.setRestaurant(restaurant);

            // Set reservation date and time
            reservation.setReservationDate(reservationDateTime);

            // Set number of people
            reservation.setNumberOfPeople(numberOfPeople);

            // Save the reservation to the database
            reservationService.saveReservation(reservation);

            // Redirect to a success page after successful reservation
            return "redirect:/Reservations";
        } else {
            // Redirect to the login page if the user is not authenticated
            return "redirect:/login";
        }
    }




}

