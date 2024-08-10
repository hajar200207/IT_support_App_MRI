package com.itsolutions.equipment_management.controllers;

import com.itsolutions.equipment_management.models.PanneEquipment;
import com.itsolutions.equipment_management.services.PanneEquipmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/panne-equipment")
public class PanneEquipmentController {

    @Autowired
    private PanneEquipmentService panneEquipmentService;

    @GetMapping
    public List<PanneEquipment> getHistorique() {
        return panneEquipmentService.getAllHistorique();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PanneEquipment> getPanneEquipmentById(@PathVariable Long id) {
        PanneEquipment panneEquipment = panneEquipmentService.getPanneEquipmentById(id);
        if (panneEquipment != null) {
            return ResponseEntity.ok(panneEquipment);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public PanneEquipment createPanneEquipment(@RequestBody PanneEquipment panneEquipment) {
        return panneEquipmentService.savePanneEquipment(panneEquipment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePanneEquipment(@PathVariable Long id) {
        panneEquipmentService.deletePanneEquipment(id);
        return ResponseEntity.noContent().build();
    }
}
