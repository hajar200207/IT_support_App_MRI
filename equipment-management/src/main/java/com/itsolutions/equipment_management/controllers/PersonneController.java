package com.itsolutions.equipment_management.controllers;

import com.itsolutions.equipment_management.security.JwtAuth;
import com.itsolutions.equipment_management.models.Admin;
import com.itsolutions.equipment_management.models.Personne;
import com.itsolutions.equipment_management.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> registerPersonne(@RequestBody Personne personne) {
        try {
            Personne registeredPersonne = personneService.registerPersonne(personne);
            return ResponseEntity.ok(registeredPersonne);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error registering person: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody Personne loginRequest) {
        Optional<Personne> user = personneService.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && passwordEncoder.matches(loginRequest.getMotDePasse(), user.get().getMotDePasse())) {
            String token = jwtAuth.generateToken(user.get().getEmail(), user.get().getRole()); // Include role
            return ResponseEntity.ok(token);
        } else {
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        personneService.deletePersonne(id);
        return ResponseEntity.ok("User deleted successfully");
    }
}
