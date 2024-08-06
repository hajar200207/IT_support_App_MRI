package com.itsolutions.equipment_management.services;

import com.itsolutions.equipment_management.models.Panne;
import com.itsolutions.equipment_management.repositories.PanneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
