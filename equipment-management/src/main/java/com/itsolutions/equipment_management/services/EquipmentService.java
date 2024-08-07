package com.itsolutions.equipment_management.services;

import com.itsolutions.equipment_management.models.Equipment;
import com.itsolutions.equipment_management.repositories.EquipmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipmentService {

    @Autowired
    private EquipmentRepository equipmentRepository;

    public Equipment addEquipment(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    public Equipment updateEquipment(Long id, Equipment equipmentDetails) {
        Equipment equipment = equipmentRepository.findById(id).orElseThrow();
        equipment.setNom(equipmentDetails.getNom());
        equipment.setType(equipmentDetails.getType());
        equipment.setEtatEquipement(equipmentDetails.getEtatEquipement());
        return equipmentRepository.save(equipment);
    }

    public void deleteEquipment(Long id) {
        equipmentRepository.deleteById(id);
    }

    public List<Equipment> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Equipment getEquipmentById(Long id) {
        return equipmentRepository.findById(id).orElseThrow(() -> new RuntimeException("Equipment not found"));
    }
}
