package com.itsolutions.equipment_management.controllers;

import com.itsolutions.equipment_management.models.Technicien;
import com.itsolutions.equipment_management.services.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/techniciens")
public class TechnicienController {

    @Autowired
    private TechnicienService technicienService;

    @PostMapping("/register")
    public ResponseEntity<?> registerTechnicien(@RequestBody Technicien technicien) {
        Technicien newTechnicien = technicienService.registerTechnicien(technicien);
        return ResponseEntity.ok(newTechnicien);
    }

    @GetMapping
    public ResponseEntity<List<Technicien>> getAllTechniciens() {
        List<Technicien> techniciens = technicienService.getAllTechniciens();
        return ResponseEntity.ok(techniciens);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTechnicienById(@PathVariable Long id) {
        Optional<Technicien> technicien = technicienService.getTechnicienById(id);
        return technicien.isPresent() ? ResponseEntity.ok(technicien.get()) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTechnicien(@PathVariable Long id, @RequestBody Technicien updatedTechnicien) {
        Technicien technicien = technicienService.updateTechnicien(id, updatedTechnicien);
        return technicien != null ? ResponseEntity.ok(technicien) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTechnicien(@PathVariable Long id) {
        technicienService.deleteTechnicien(id);
        return ResponseEntity.ok("Technicien deleted successfully");
    }
}
