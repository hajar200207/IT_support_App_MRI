package com.itsolutions.equipment_management.controllers;

import com.itsolutions.equipment_management.models.Personne;
import com.itsolutions.equipment_management.models.Role;
import com.itsolutions.equipment_management.models.Technicien;
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
import java.util.List;
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

    // Endpoint pour l'enregistrement d'un nouvel utilisateur
    @PostMapping("/register")
    public void registerUser(@RequestBody Personne userRequest) {
        // Vérifie si l'email est déjà enregistré
        Optional<Personne> existingUser = personneService.findByEmail(userRequest.getEmail());
        if (existingUser.isPresent()) {
            ResponseEntity.status(400).body("Email already registered");
        }

        // Enregistre le nouvel utilisateur
        Personne newUser = personneService.registerPersonne(userRequest);
        ResponseEntity.ok("User registered successfully");
    }

    // Endpoint pour la connexion d'un utilisateur
    @PostMapping("/login")
    // Déclaration de la méthode loginUser
// Cette méthode est accessible publiquement et renvoie un objet ResponseEntity qui peut contenir tout type d'objet (indiqué par le générique <?>).
// Elle accepte un corps de requête HTTP de type Map<String, String> qui contient les données de connexion (email et mot de passe).
// L'annotation @RequestBody indique que le corps de la requête sera automatiquement converti en un objet Map en Java.
// map est un utilisée pour capturer les paires clé-valeur provenant du JSON de la requête
    public ResponseEntity<?> loginUser(@RequestBody Map<String, String> userRequest) {

        // Récupère les informations d'identification depuis la requête
        String email = userRequest.get("email");
        String rawPassword = userRequest.get("motDePasse");

        if (email == null || rawPassword == null) {
            return ResponseEntity.badRequest().body("Email and password are required");
        }

        // Vérifie si l'utilisateur existe
        Optional<Personne> optionalUser = personneService.findByEmail(email);
        if (optionalUser.isPresent()) {
            Personne foundUser = optionalUser.get();
            String encodedPassword = foundUser.getMotDePasse();

            // Vérifie si le mot de passe correspond
            if (passwordEncoder.matches(rawPassword, encodedPassword)) {
                Role role = foundUser.getRole();
                // Génère un token JWT pour l'utilisateur
                String token = jwtAuth.generateToken(foundUser.getEmail(), String.valueOf(role));

                // Prépare la réponse contenant le token et le rôle
                Map<String, String> response = new HashMap<>();
                response.put("token", token);
                response.put("role", String.valueOf(role));

                return ResponseEntity.ok(response);
            } else {
                return ResponseEntity.status(401).body("Invalid password");
            }
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }

    // Endpoint pour mettre à jour les informations d'un utilisateur
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePersonne(@PathVariable Long id, @RequestBody Personne updatedPersonne) {
        try {
            // Met à jour les informations de l'utilisateur
            Personne updated = personneService.updatePersonne(id, updatedPersonne);
            return ResponseEntity.ok("User updated successfully");
        } catch (EntityNotFoundException e) {
            // Retourne une réponse d'erreur si l'utilisateur n'est pas trouvé
            return ResponseEntity.status(404).body("User not found");
        }
    }

    // Endpoint pour récupérer les informations du profil de l'utilisateur connecté
    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userEmail;
        if (principal instanceof UserDetails) {
            userEmail = ((UserDetails) principal).getUsername();
        } else {
            userEmail = principal.toString();
        }

        // Récupère les informations de l'utilisateur connecté
        Optional<Personne> optionalPersonne = personneService.findByEmail(userEmail);
        if (optionalPersonne.isPresent()) {
            return ResponseEntity.ok(optionalPersonne.get());
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }

    // Endpoint pour supprimer un utilisateur
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) {
        // Supprime l'utilisateur correspondant à l'ID
        personneService.deletePersonne(id);
        return ResponseEntity.ok("User deleted successfully");
    }

    // Endpoint pour récupérer tous les techniciens
    @GetMapping("/techniciens")
    public ResponseEntity<List<Technicien>> getAllTechniciens() {
        List<Technicien> techniciens = personneService.getAllTechniciens();
        return ResponseEntity.ok(techniciens);
    }

    // Endpoint pour récupérer un technicien par son ID
    @GetMapping("/techniciens/{id}")
    public ResponseEntity<?> getTechnicienById(@PathVariable Long id) {
        try {
            Technicien technicien = personneService.getTechnicienById(id);
            return ResponseEntity.ok(technicien);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(404).body("Technicien not found");
        }
    }

    // Endpoint pour récupérer tous les utilisateurs
    @GetMapping("/all")
    public ResponseEntity<List<Personne>> getAllUsers() {
        List<Personne> users = personneService.getAllUsers();
        return ResponseEntity.ok(users);
    }
}
