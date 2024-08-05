package com.itsolutions.equipment_management.services;

import com.itsolutions.equipment_management.models.Admin;
import com.itsolutions.equipment_management.models.Personne;
import com.itsolutions.equipment_management.models.Technicien;
import com.itsolutions.equipment_management.repositories.PersonneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonneService {

    @Autowired
    private PersonneRepository personneRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Personne registerPersonne(Personne personne) {
        personne.setMotDePasse(passwordEncoder.encode(personne.getMotDePasse()));

        Optional<Personne> existingPersonnes = personneRepository.findByEmail(personne.getEmail());
        if (existingPersonnes.isPresent()) {
            throw new RuntimeException("Email already registered");
        }

        if (personne instanceof Technicien) {
            Technicien technicien = (Technicien) personne;
            technicien.setRole("ROLE_TECHNICIEN");
            return personneRepository.save(technicien);
        }


        return personneRepository.save(personne);
    }

        public Optional<Personne> findByEmail(String email) {
        return personneRepository.findByEmail(email);
    }

    public List<Personne> findAll() {
        return personneRepository.findAll();
    }

    public void deletePersonne(Long id) {
        personneRepository.deleteById(id);
    }

    public void createAdminUserIfNotExist() {
        String adminEmail = "admin@example.com";
        Optional<Personne> existingAdmin = personneRepository.findByEmail(adminEmail);

        if (existingAdmin.isEmpty()) {
            Admin admin = new Admin();
            admin.setEmail(adminEmail);
            admin.setMotDePasse(passwordEncoder.encode("admin"));
            admin.setDepartement("Admin Department");
            personneRepository.save(admin);
        }
    }
}
