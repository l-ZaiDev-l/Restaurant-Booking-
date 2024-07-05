package com.example.restaurantApp.service.impl;

import com.example.restaurantApp.models.Reservation;
import com.example.restaurantApp.models.User;
import com.example.restaurantApp.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Transactional
    public void saveReservation(Reservation reservation) {
        reservationRepository.save(reservation);
    }

    @Transactional(readOnly = true)
    public List<Reservation> findReservationsByUser(User user) {
        return reservationRepository.findByUserId(user.getId());
    }

    @Transactional(readOnly = true)
    public Reservation findReservationById(Long reservationId) {
        return reservationRepository.findById(reservationId).orElse(null);
    }

    @Transactional
    public void cancelReservation(Long reservationId) {
        // Retrieve the reservation by ID
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservationId);

        if (optionalReservation.isPresent()) {
            // Delete the reservation if found
            Reservation reservation = optionalReservation.get();
            reservationRepository.delete(reservation);
        } else {
            // Handle case where reservation is not found
            throw new IllegalArgumentException("Reservation not found with id: " + reservationId);
            // You can also handle this scenario based on your application's logic
        }
    }

    // Add more service methods as needed
}
