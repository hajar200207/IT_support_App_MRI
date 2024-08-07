package com.itsolutions.equipment_management.repositories;

import com.itsolutions.equipment_management.models.PanneEquipment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface PanneEquipmentRepository extends JpaRepository<PanneEquipment, Long> {
    List<PanneEquipment> findByPanne_Id(Long panneId);
    List<PanneEquipment> findByEquipment_Id(Long equipmentId);
    List<PanneEquipment> findByEquipmentId(Long equipmentId);
    List<PanneEquipment> findByDateOfLinkBetween(LocalDateTime startDate, LocalDateTime endDate);
}
