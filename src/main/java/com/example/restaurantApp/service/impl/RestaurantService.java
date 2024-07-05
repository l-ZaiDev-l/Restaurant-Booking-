package com.example.restaurantApp.service.impl;

import com.example.restaurantApp.models.Restaurant;
import com.example.restaurantApp.repository.RestaurantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }

    public List<Restaurant> findRestaurants(String name, String city, String address) {
        if ((name != null && !name.isEmpty()) || (city != null && !city.isEmpty()) || (address != null && !address.isEmpty())) {
            return restaurantRepository.findByNameContainingAndCityContainingAndAddressContaining(
                    name == null ? "" : name,
                    city == null ? "" : city,
                    address == null ? "" : address
            );
        } else {
            return getAllRestaurants();
        }
    }
    public Restaurant getRestaurantById(Long id) {
        return restaurantRepository.findById(id).orElse(null);
    }

    public Restaurant findRestaurantById(Long restaurantId) {
        return restaurantRepository.findById(restaurantId).orElse(null);
    }
}
