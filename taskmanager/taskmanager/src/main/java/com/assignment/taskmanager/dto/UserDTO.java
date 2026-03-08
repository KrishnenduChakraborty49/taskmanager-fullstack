package com.assignment.taskmanager.dto;

import lombok.Data;

@Data
public class UserDTO {
    private String email;
    private String password;
    private String role; // "ROLE_USER" or "ROLE_ADMIN"
}
