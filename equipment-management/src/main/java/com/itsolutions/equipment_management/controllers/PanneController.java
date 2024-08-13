package com.itsolutions.equipment_management.controllers;

import com.itsolutions.equipment_management.DTO.PanneWithEquipmentDTO;
import com.itsolutions.equipment_management.models.EtatPanne;
import com.itsolutions.equipment_management.models.Panne;
import com.itsolutions.equipment_management.repositories.EquipmentRepository;
import com.itsolutions.equipment_management.services.PanneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/pannes")
public class PanneController {

    @Autowired
    private PanneService panneService;
    @Autowired
    private EquipmentRepository equipmentRepository;

    @PostMapping("/report")
    public ResponseEntity<Panne> reportPanne(@RequestBody Panne panne) {
        panne.setEquipmentsFromIds(panne.getEquipmentIds(), equipmentRepository);
        return ResponseEntity.ok(panneService.reportPanne(panne));
    }
    @GetMapping("/equipment/{equipmentId}")
    public ResponseEntity<List<Panne>> getPannesByEquipmentId(@PathVariable Long equipmentId) {
        List<Panne> pannes = panneService.getPannesByEquipmentId(equipmentId);
        if (pannes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pannes);
    }

    @GetMapping("/")
    public ResponseEntity<List<Panne>> getAllPannes() {
        List<Panne> pannes = panneService.getAllPannes();
        if (pannes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pannes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Panne> getPanneById(@PathVariable Long id) {
        Panne panne = panneService.getPanneById(id);
        if (panne == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(panne);
    }
    @PreAuthorize("hasRole('ADMIN')")

    @PutMapping("/{id}")
    public ResponseEntity<Panne> updatePanne(@PathVariable Long id, @RequestBody Panne panneDetails) {
        Panne updatedPanne = panneService.updatePanne(id, panneDetails);
        if (updatedPanne == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedPanne);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePanne(@PathVariable Long id) {
        boolean isDeleted = panneService.deletePanne(id);
        if (isDeleted) {
            return ResponseEntity.ok("Panne deleted successfully");
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping("/search")
    public ResponseEntity<List<Panne>> searchPannesByDescription(@RequestParam String keyword) {
        List<Panne> pannes = panneService.searchPannesByDescription(keyword);
        if (pannes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pannes);
    }

    @GetMapping("/search/etat")
    public ResponseEntity<List<Panne>> searchPannesByEtat(@RequestParam EtatPanne etatPanne) {
        List<Panne> pannes = panneService.searchPannesByEtat(etatPanne);
        if (pannes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(pannes);
    }
//    @GetMapping("/with-equipments")
//    public List<PanneWithEquipmentDTO> getAllPannesWithEquipments() {
//        return panneService.findAllPannesWithEquipments();
//    }
    @GetMapping("/{id}/details")
    public ResponseEntity<PanneWithEquipmentDTO> getPanneWithEquipment(@PathVariable Long id) {
        PanneWithEquipmentDTO dto = panneService.getPanneWithEquipment(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/total")
    public ResponseEntity<Long> getTotalPannes() {
        return ResponseEntity.ok(panneService.getTotalPannes());
    }
}
