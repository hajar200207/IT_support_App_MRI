package com.itsolutions.equipment_management.repositories;

import com.itsolutions.equipment_management.models.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicienRepository extends JpaRepository<Technicien, Long> {
}