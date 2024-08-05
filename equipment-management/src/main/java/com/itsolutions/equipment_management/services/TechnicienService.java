package com.itsolutions.equipment_management.services;

import com.itsolutions.equipment_management.models.Technicien;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.itsolutions.equipment_management.repositories.TechnicienRepository;
@Service
public class TechnicienService {
    @Autowired
    private TechnicienRepository technicienRepository;



        public Technicien getTechnicienById(Long id) {
            return technicienRepository.findById(id).orElse(null);
        }

        public Technicien saveTechnicien(Technicien technicien) {
            return technicienRepository.save(technicien);
        }
    }



