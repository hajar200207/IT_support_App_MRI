package com.itsolutions.equipment_management.controllers;

import com.itsolutions.equipment_management.models.PanneEquipment;
import com.itsolutions.equipment_management.services.PanneEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/panne-equipment")
public class PanneEquipmentController {

    @Autowired
    private PanneEquipmentService panneEquipmentService;

    // Create a new PanneEquipment record
    @PostMapping
    public ResponseEntity<PanneEquipment> createPanneEquipment(@RequestBody PanneEquipment panneEquipment) {
        PanneEquipment createdPanneEquipment = panneEquipmentService.savePanneEquipment(panneEquipment);
        return ResponseEntity.ok(createdPanneEquipment);
    }

    // Get all PanneEquipment records
    @GetMapping
    public ResponseEntity<List<PanneEquipment>> getAllPanneEquipments() {
        List<PanneEquipment> panneEquipments = panneEquipmentService.getAllPanneEquipments();
        return ResponseEntity.ok(panneEquipments);
    }

    // Get PanneEquipment history by Panne ID
    @GetMapping("/panne/{panneId}")
    public ResponseEntity<List<PanneEquipment>> getPanneHistory(@PathVariable Long panneId) {
        List<PanneEquipment> panneHistory = panneEquipmentService.getPanneHistory(panneId);
        return ResponseEntity.ok(panneHistory);
    }

    // Get PanneEquipment history by Equipment ID
    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<List<PanneEquipment>> getEquipmentHistory(@PathVariable Long equipmentId) {
        List<PanneEquipment> equipmentHistory = panneEquipmentService.getEquipmentHistory(equipmentId);
        return ResponseEntity.ok(equipmentHistory);
    }

    // Get a specific PanneEquipment record by its ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<PanneEquipment>> getPanneEquipmentById(@PathVariable Long id) {
        Optional<PanneEquipment> panneEquipment = panneEquipmentService.getPanneEquipmentById(id);
        return ResponseEntity.ok(panneEquipment);
    }

    // Delete a PanneEquipment record
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePanneEquipment(@PathVariable Long id) {
        panneEquipmentService.deletePanneEquipment(id);
        return ResponseEntity.noContent().build();
    }
}
