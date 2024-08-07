package com.itsolutions.equipment_management.controllers;

import com.itsolutions.equipment_management.models.Panne;
import com.itsolutions.equipment_management.services.PanneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pannes")
public class PanneController {

    @Autowired
    private PanneService panneService;

    @PostMapping("/report")
    public ResponseEntity<Panne> reportPanne(@RequestBody Panne panne) {
        return ResponseEntity.ok(panneService.reportPanne(panne));
    }

    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<List<Panne>> getPannesByEquipmentId(@PathVariable Long equipmentId) {
        return ResponseEntity.ok(panneService.getPannesByEquipmentId(equipmentId));
    }

    @GetMapping("/")
    public ResponseEntity<List<Panne>> getAllPannes() {
        return ResponseEntity.ok(panneService.getAllPannes());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Panne> updatePanne(@PathVariable Long id, @RequestBody Panne panneDetails) {
        return ResponseEntity.ok(panneService.updatePanne(id, panneDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePanne(@PathVariable Long id) {
        panneService.deletePanne(id);
        return ResponseEntity.ok("Panne deleted successfully");
    }
}