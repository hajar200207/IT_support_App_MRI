package com.itsolutions.equipment_management.services;

import com.itsolutions.equipment_management.models.*;
import com.itsolutions.equipment_management.repositories.PersonneRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Personne registerPersonne(Personne personne) {
        logger.info("Registering new personne: {}", personne.getEmail());
        logger.info("Personne type: {}", personne.getClass().getSimpleName());

        // Encode the password
        personne.setMotDePasse(passwordEncoder.encode(personne.getMotDePasse()));

        if (personne instanceof Technicien) {
            logger.info("Registering a Technicien");
            Technicien technicien = (Technicien) personne;
            technicien.setRole(Role.ROLE_TECHNICIEN);
            logger.info("Technicien specialite: {}", technicien.getSpecialite());
        } else if (personne instanceof User) {
            logger.info("Registering a User");
            User user = (User) personne;
            user.setRole(Role.ROLE_USER);
        } else if (personne instanceof Admin) {
            logger.info("Registering an Admin");
            Admin admin = (Admin) personne;
            admin.setRole(Role.ROLE_ADMIN);
        }

        // Save the personne
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
    public Optional<Personne> findById(Long id) {
        return personneRepository.findById(id);
    }

    public Personne updatePersonne(Long id, Personne updatedPersonne) {
        Optional<Personne> optionalPersonne = findById(id);
        if (optionalPersonne.isPresent()) {
            Personne existingPersonne = optionalPersonne.get();

            // Update fields
            existingPersonne.setNom(updatedPersonne.getNom());
            existingPersonne.setPrenom(updatedPersonne.getPrenom());
            existingPersonne.setEmail(updatedPersonne.getEmail());
            if (updatedPersonne.getMotDePasse() != null) {
                existingPersonne.setMotDePasse(updatedPersonne.getMotDePasse()); // Encode password if necessary
            }

            // Specific updates for subclasses (User, Technicien, Admin)
            if (existingPersonne instanceof User && updatedPersonne instanceof User) {
                ((User) existingPersonne).setFonction(((User) updatedPersonne).getFonction());
            } else if (existingPersonne instanceof Technicien && updatedPersonne instanceof Technicien) {
                ((Technicien) existingPersonne).setSpecialite(((Technicien) updatedPersonne).getSpecialite());
            } else if (existingPersonne instanceof Admin && updatedPersonne instanceof Admin) {
                ((Admin) existingPersonne).setDepartement(((Admin) updatedPersonne).getDepartement());
            }

            return personneRepository.save(existingPersonne);
        }
        throw new EntityNotFoundException("Personne not found with id: " + id);
    }

}
