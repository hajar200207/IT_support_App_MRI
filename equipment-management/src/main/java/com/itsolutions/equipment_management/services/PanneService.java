package com.itsolutions.equipment_management.services;

import com.itsolutions.equipment_management.models.Panne;
import com.itsolutions.equipment_management.repositories.PanneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanneService {

    @Autowired
    private PanneRepository panneRepository;

    public Panne reportPanne(Panne panne) {
        return panneRepository.save(panne);
    }

    public List<Panne> getPannesByEquipmentId(Long equipmentId) {
        return panneRepository.findByEquipmentId(equipmentId);
    }

    public List<Panne> getAllPannes() {
        return panneRepository.findAll();
    }

    public Panne updatePanne(Long id, Panne panneDetails) {
        Optional<Panne> optionalPanne = panneRepository.findById(id);
        if (optionalPanne.isPresent()) {
            Panne existingPanne = optionalPanne.get();
            existingPanne.setDescription(panneDetails.getDescription());
            existingPanne.setDatePanne(panneDetails.getDatePanne());
            existingPanne.setEquipment(panneDetails.getEquipment());
            return panneRepository.save(existingPanne);
        } else {
            throw new RuntimeException("Panne not found with id: " + id);
        }
    }

    public void deletePanne(Long id) {
        if (panneRepository.existsById(id)) {
            panneRepository.deleteById(id);
        } else {
            throw new RuntimeException("Panne not found with id: " + id);
        }
    }
}