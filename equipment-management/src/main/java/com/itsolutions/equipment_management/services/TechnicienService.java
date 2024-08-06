package com.itsolutions.equipment_management.services;

import com.itsolutions.equipment_management.models.Technicien;
import com.itsolutions.equipment_management.repositories.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TechnicienService {

    @Autowired
    private TechnicienRepository technicienRepository;

    public Technicien registerTechnicien(Technicien technicien) {
        return technicienRepository.save(technicien);
    }

    public List<Technicien> getAllTechniciens() {
        return technicienRepository.findAll();
    }

    public Optional<Technicien> getTechnicienById(Long id) {
        return technicienRepository.findById(id);
    }

    public Technicien updateTechnicien(Long id, Technicien updatedTechnicien) {
        Optional<Technicien> existingTechnicien = technicienRepository.findById(id);
        if (existingTechnicien.isPresent()) {
            Technicien technicien = existingTechnicien.get();
            technicien.setNom(updatedTechnicien.getNom());
            technicien.setPrenom(updatedTechnicien.getPrenom());
            technicien.setEmail(updatedTechnicien.getEmail());
            technicien.setSpecialite(updatedTechnicien.getSpecialite());
            return technicienRepository.save(technicien);
        }
        return null;
    }

    public void deleteTechnicien(Long id) {
        technicienRepository.deleteById(id);
    }
}
