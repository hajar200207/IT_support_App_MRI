package com.itsolutions.equipment_management.services;


import com.itsolutions.equipment_management.models.PanneEquipment;
import com.itsolutions.equipment_management.repositories.PanneEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanneEquipmentService {

    @Autowired
    private PanneEquipmentRepository panneEquipmentRepository;


    public PanneEquipment getPanneEquipmentById(Long id) {
        return panneEquipmentRepository.findById(id).orElse(null);
    }

    public PanneEquipment savePanneEquipment(PanneEquipment panneEquipment) {
        return panneEquipmentRepository.save(panneEquipment);
    }

    public void deletePanneEquipment(Long id) {
        panneEquipmentRepository.deleteById(id);
    }

    public List<PanneEquipment> getAllHistorique() {
        return panneEquipmentRepository.findAll();
    }
}
