package com.example.webshopbackend.services;

import com.example.webshopbackend.dto.UserDTO;
import com.example.webshopbackend.entities.User;
import com.example.webshopbackend.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getSpecificUser(UUID userId) {
        Optional<User> foundUser = userRepository.findById(userId);

        if(foundUser.isPresent()) {
            return foundUser.get();
        } else {
            throw new NullPointerException(String.format("Could not find item with ID: %s!", userId));
        }
    }

    public ResponseEntity<User> createUser(User user) {
        User userToCreate = new User();

        userToCreate.setUserId(UUID.randomUUID());
        userToCreate.setFirstName(user.getFirstName());
        userToCreate.setLastName(user.getLastName());
        userToCreate.setEmail(user.getEmail());
        userToCreate.setRole(user.getRole());

        userRepository.save(userToCreate);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public ResponseEntity<UserDTO> updateUser(UUID userId, UserDTO userDTO) {

        if(userId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        if(userDTO == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }

        Optional<User> userToUpdate = userRepository.findById(userId);

        if(userToUpdate.isPresent()) {
            userToUpdate.get().setFirstName(userDTO.firstName());
            userToUpdate.get().setLastName(userDTO.lastName());
            userToUpdate.get().setEmail(userDTO.email());
            userToUpdate.get().setPassword(userDTO.password());

            userRepository.save(userToUpdate.get());
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            throw new NullPointerException(String.format("Could not find item with ID: %s!", userId));
        }
    }

    public ResponseEntity deleteUser(UUID userId) {
        if(userId == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Optional<User> foundUser = userRepository.findById(userId);

        if(foundUser.isPresent()) {
            userRepository.deleteById(userId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            throw new NullPointerException(String.format("Could not find item with ID: %s!", userId));
        }
    }
}
