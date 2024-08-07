package com.itsolutions.equipment_management.services;

import com.itsolutions.equipment_management.models.PanneEquipment;
import com.itsolutions.equipment_management.repositories.PanneEquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PanneEquipmentService {

    @Autowired
    private PanneEquipmentRepository panneEquipmentRepository;

    // Save a new PanneEquipment record
    public PanneEquipment savePanneEquipment(PanneEquipment panneEquipment) {
        return panneEquipmentRepository.save(panneEquipment);
    }

    // Get all records of PanneEquipment
    public List<PanneEquipment> getAllPanneEquipments() {
        return panneEquipmentRepository.findAll();
    }

    // Get PanneEquipment history by Panne ID
    public List<PanneEquipment> getPanneHistory(Long panneId) {
        return panneEquipmentRepository.findByPanne_Id(panneId);
    }

    // Get PanneEquipment history by Equipment ID
    public List<PanneEquipment> getEquipmentHistory(Long equipmentId) {
        return panneEquipmentRepository.findByEquipment_Id(equipmentId);
    }

    // Get a specific PanneEquipment record by its ID
    public Optional<PanneEquipment> getPanneEquipmentById(Long id) {
        return panneEquipmentRepository.findById(id);
    }

    // Delete a PanneEquipment record
    public void deletePanneEquipment(Long id) {
        panneEquipmentRepository.deleteById(id);
    }
}
