package com.itsolutions.equipment_management.controllers;

import com.itsolutions.equipment_management.models.Equipment;
import com.itsolutions.equipment_management.services.EquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//terminer test gestion equipment
@CrossOrigin(origins = "http://localhost:4200/")

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {

    @Autowired
    private EquipmentService equipmentService;

    @PostMapping("/add")
    public ResponseEntity<Equipment> addEquipment(@RequestBody Equipment equipment) {
        return ResponseEntity.ok(equipmentService.addEquipment(equipment));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Equipment> updateEquipment(@PathVariable Long id, @RequestBody Equipment equipmentDetails) {
        return ResponseEntity.ok(equipmentService.updateEquipment(id, equipmentDetails));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEquipment(@PathVariable Long id) {
        equipmentService.deleteEquipment(id);
        return ResponseEntity.ok("Equipment deleted successfully");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Equipment>> getAllEquipments() {
        return ResponseEntity.ok(equipmentService.getAllEquipments());
    }
    @GetMapping("/find-by-nom/{nom}")
    public ResponseEntity<List<Equipment>> getEquipmentByNom(@PathVariable String nom) {
        List<Equipment> equipmentList = equipmentService.findByNom(nom);
        if (equipmentList.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(equipmentList);
    }
    @GetMapping("/total")
    public ResponseEntity<Long> getTotalEquipments() {
        return ResponseEntity.ok(equipmentService.getTotalEquipments());
    }
}
