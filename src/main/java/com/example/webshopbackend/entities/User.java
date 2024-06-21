package com.example.webshopbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
public class User {

    @Id
    private UUID userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Enum role;

    public User(String firstName, String lastName, String email, String password, Enum role) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {

    }
}
