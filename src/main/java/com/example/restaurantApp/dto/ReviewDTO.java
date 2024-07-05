package com.example.restaurantApp.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ReviewDTO {
    private Long id;
    private UserDTO user;
    private RestaurantDTO restaurant;
    private int rating;
    private String comment;
    private Date createdAt;
}

