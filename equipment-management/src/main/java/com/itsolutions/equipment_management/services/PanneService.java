package com.itsolutions.equipment_management.services;

import com.itsolutions.equipment_management.DTO.EquipmentDTO;
import com.itsolutions.equipment_management.DTO.PanneWithEquipmentDTO;
import com.itsolutions.equipment_management.exception.ResourceNotFoundException;
import com.itsolutions.equipment_management.models.Equipment;
import com.itsolutions.equipment_management.models.EtatPanne;
import com.itsolutions.equipment_management.models.Panne;
import com.itsolutions.equipment_management.repositories.EquipmentRepository;
import com.itsolutions.equipment_management.repositories.PanneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
@Service
public class PanneService {

    @Autowired
    private PanneRepository panneRepository;

    @Autowired
    EquipmentRepository  equipmentRepository;

    public Panne reportPanne(Panne panne) {
        System.out.println("Setting equipments for panne with ID: " + panne.getId());
        panne.getEquipmentsIds().forEach(equipmentId ->
                System.out.println("Equipment ID: " + equipmentId)
        );
        return panneRepository.save(panne);
    }




    public List<Panne> getPannesByEquipmentId(Long equipmentId) {
        return panneRepository.findByEquipmentId(equipmentId);
    }

    public List<Panne> getAllPannes() {
        return panneRepository.findAll();
    }

    public Panne updatePanne(Long id, Panne panneDetails) {
        if (panneRepository.existsById(id)) {
            Panne panne = panneRepository.findById(id).orElseThrow(() -> new RuntimeException("Panne not found"));
            panne.setDescription(panneDetails.getDescription());
            panne.setEtatPanne(panneDetails.getEtatPanne());
            return panneRepository.save(panne);
        }
        return null;
    }

    public boolean deletePanne(Long id) {
        if (panneRepository.existsById(id)) {
            panneRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    public List<Panne> searchPannesByDescription(String keyword) {
        return panneRepository.findByDescriptionContainingIgnoreCase(keyword);
    }

    public List<Panne> searchPannesByEtat(EtatPanne etatPanne) {
        return panneRepository.findByEtatPanne(etatPanne);
    }

    public Panne getPanneById(Long id) {
        Optional<Panne> optionalPanne = panneRepository.findById(id);
        return optionalPanne.orElse(null);
    }

    public PanneWithEquipmentDTO getPanneWithEquipment(Long id) {
        Optional<Panne> panneOpt = panneRepository.findById(id);
        if (panneOpt.isPresent()) {
            Panne panne = panneOpt.get();
            Set<Equipment> equipments = panne.getEquipments();
            Set<EquipmentDTO> equipmentDTOs = new HashSet<>();
            for (Equipment equipment : equipments) {
                equipmentDTOs.add(new EquipmentDTO(
                        equipment.getId(),
                        equipment.getNom(),
                        equipment.getType(),
                        equipment.getEtatEquipement()
                ));
            }
            return new PanneWithEquipmentDTO(
                    panne.getId(),
                    panne.getDescription(),
                    panne.getDatePanne(),
                    panne.getEtatPanne(),
                    new ArrayList<>(equipmentDTOs)  // Convertir Set à List
            );
        }
        return null;
    }

//    public List<PanneWithEquipmentDTO> findAllPannesWithEquipments() {
//        List<Panne> pannes = panneRepository.findAllPannesWithEquipments();
//        return pannes.stream()
//                .map(panneMapper::toDTO)
//                .collect(Collectors.toList());
//    }
}
