package com.itsolutions.equipment_management.services;

import com.itsolutions.equipment_management.models.Admin;
import com.itsolutions.equipment_management.models.Personne;
import com.itsolutions.equipment_management.models.Technicien;
import com.itsolutions.equipment_management.repositories.PersonneRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger logger = LoggerFactory.getLogger(PersonneService.class);
    public Personne registerPersonne(Personne personne) {
        logger.info("Registering new personne: {}", personne.getEmail());
        logger.info("Personne type: {}", personne.getClass().getSimpleName());

        personne.setMotDePasse(passwordEncoder.encode(personne.getMotDePasse()));

        if (personne instanceof Technicien) {
            logger.info("Registering a Technicien");
            Technicien technicien = (Technicien) personne;
            technicien.setRole("ROLE_TECHNICIEN");
            logger.info("Technicien specialite: {}", technicien.getSpecialite());
        }

        Personne savedPersonne = personneRepository.save(personne);
        logger.info("Personne saved with ID: {}", savedPersonne.getId());

        return savedPersonne;
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
