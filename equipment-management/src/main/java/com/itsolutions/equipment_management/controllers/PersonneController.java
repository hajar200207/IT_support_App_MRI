package com.itsolutions.equipment_management.controllers;

import com.itsolutions.equipment_management.security.JwtAuth;
import com.itsolutions.equipment_management.models.Admin;
import com.itsolutions.equipment_management.models.Personne;
import com.itsolutions.equipment_management.services.PersonneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

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
    public ResponseEntity<?> loginUser(@RequestBody Personne userRequest) {
        Optional<Personne> optionalUser = personneService.findByEmail(userRequest.getEmail());

        if (optionalUser.isPresent()) {
            Personne foundUser = optionalUser.get();
            if (passwordEncoder.matches(userRequest.getMotDePasse(), foundUser.getMotDePasse())) {
                String role = foundUser.getRole();

                String token = jwtAuth.generateToken(foundUser.getEmail(), role);

                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("role", role);

                return ResponseEntity.ok(response);
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
