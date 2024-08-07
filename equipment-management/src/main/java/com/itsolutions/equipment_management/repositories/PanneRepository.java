package com.itsolutions.equipment_management.repositories;

import com.itsolutions.equipment_management.models.Panne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PanneRepository extends JpaRepository<Panne, Long> {
    List<Panne> findByEquipmentId(Long equipmentId);

}
