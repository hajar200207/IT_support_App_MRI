package com.itsolutions.equipment_management.controllers;

import com.itsolutions.equipment_management.models.Personne;
import com.itsolutions.equipment_management.security.JwtAuth;
import com.itsolutions.equipment_management.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public ResponseEntity<?> registerUser(@RequestBody Personne userRequest) {
        Optional<Personne> existingUser = personneService.findByEmail(userRequest.getEmail());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(400).body("Email already registered");
        }

        Personne newUser = personneService.registerPersonne(userRequest);
        return ResponseEntity.ok("User registered successfully");
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


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        personneService.deletePersonne(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
