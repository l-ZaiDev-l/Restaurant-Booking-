package com.example.restaurantApp.repository;

import com.example.restaurantApp.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {
    List<Restaurant> findByNameContainingAndCityContainingAndAddressContaining(String name, String city, String address);
}
