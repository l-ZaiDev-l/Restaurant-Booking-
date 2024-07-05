package com.example.restaurantApp.dto;

import lombok.Data;
import java.util.Date;
import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String phone;
    private Date createdAt;
    private Set<RoleDTO> roles;
}

