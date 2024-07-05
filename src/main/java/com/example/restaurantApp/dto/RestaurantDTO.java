package com.example.restaurantApp.dto;

import lombok.Data;
import java.util.Date;
import java.util.Set;

@Data
public class RestaurantDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String phone;
    private String email;
    private String description;
    private Date createdAt;
    private Set<CategoryDTO> categories;
}

