package com.example.restaurantApp.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ReservationDTO {
    private Long id;
    private UserDTO user;
    private RestaurantDTO restaurant;
    private Date reservationDate;
    private Date reservationTime;
    private int numberOfPeople;
    private String status;
    private Date createdAt;
}
