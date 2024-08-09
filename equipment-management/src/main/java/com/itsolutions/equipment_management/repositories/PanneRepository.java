package com.itsolutions.equipment_management.repositories;

import com.itsolutions.equipment_management.models.EtatPanne;
import com.itsolutions.equipment_management.models.Panne;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PanneRepository extends JpaRepository<Panne, Long> {
    @Query("SELECT p FROM Panne p JOIN p.equipments e WHERE e.id = :equipmentId")
    List<Panne> findByEquipmentId(@Param("equipmentId") Long equipmentId);
    List<Panne> findByDescriptionContainingIgnoreCase(String keyword);
    List<Panne> findByEtatPanne(EtatPanne etatPanne);


}
