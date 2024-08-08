package com.itsolutions.equipment_management.controllers;

import com.itsolutions.equipment_management.models.Personne;
import com.itsolutions.equipment_management.models.User;
import com.itsolutions.equipment_management.security.JwtAuth;
import com.itsolutions.equipment_management.services.PersonneService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/users")
public class PersonneController {

    @Autowired
    private PersonneService personneService;
    @Autowired
    private JwtAuth jwtAuth;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public void registerUser(@RequestBody Personne userRequest) {
        Optional<Personne> existingUser = personneService.findByEmail(userRequest.getEmail());
        if (existingUser.isPresent()) {
            ResponseEntity.status(400).body("Email already registered");
        }

        Personne newUser = personneService.registerPersonne(userRequest);
         ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody Personne userRequest) {
        Optional<Personne> optionalUser = personneService.findByEmail(userRequest.getEmail());
        if (optionalUser.isPresent()) {
            Personne foundUser = optionalUser.get();
            String rawPassword = userRequest.getMotDePasse();
            String encodedPassword = foundUser.getMotDePasse();

            System.out.println("Raw password: " + rawPassword);
            System.out.println("Encoded password from database: " + encodedPassword);

            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                String role = foundUser.getRole();
                String token = jwtAuth.generateToken(foundUser.getEmail(), role);

                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("role", role);

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body("Invalid password");
            }
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePersonne(@PathVariable Long id, @RequestBody Personne updatedPersonne) {
        try {
            Personne updated = personneService.updatePersonne(id, updatedPersonne);
            return ResponseEntity.ok("User updated successfully");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("User not found");
        }
    }
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail;
        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = principal.toString();
        }

        Optional<Personne> optionalPersonne = personneService.findByEmail(userEmail);
        if (optionalPersonne.isPresent()) {
            return ResponseEntity.ok(optionalPersonne.get());
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        personneService.deletePersonne(id);
        return ResponseEntity.ok("User deleted successfully");
    }

}
