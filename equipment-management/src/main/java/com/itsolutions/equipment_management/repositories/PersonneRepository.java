package com.itsolutions.equipment_management.repositories;

import com.itsolutions.equipment_management.models.Personne;
import com.itsolutions.equipment_management.models.Role;
import com.itsolutions.equipment_management.models.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {

    Optional<Personne> findByEmail(String email);

    List<Personne> findAllByRole(Role role);
}

