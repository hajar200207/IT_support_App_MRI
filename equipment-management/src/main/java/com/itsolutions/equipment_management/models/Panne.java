package com.itsolutions.equipment_management.models;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itsolutions.equipment_management.repositories.EquipmentRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "panne")
public class Panne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private LocalDateTime datePanne;

    @Enumerated(EnumType.STRING)
    private EtatPanne etatPanne;

    @Transient
    private Set<Long> equipmentIds = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name = "panne_equipment",
            joinColumns = @JoinColumn(name = "panne_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipments = new HashSet<>();

    @Transient
    public void setEquipmentsFromIds(Set<Long> equipmentIds, EquipmentRepository equipmentRepository) {
        this.equipments = equipmentIds.stream()
                .map(equipmentRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }
    @Transient
public Set<Long> getEquipmentsIds() {
    return equipments.stream()
            .map(Equipment::getId)
            .collect(Collectors.toSet());
}

    public LocalDateTime getDate() {
        return  this.datePanne;
    }
}


