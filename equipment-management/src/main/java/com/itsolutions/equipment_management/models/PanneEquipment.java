package com.itsolutions.equipment_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "panne_equipment")
public class PanneEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "panne_id", nullable = false)
    private Panne panne;

    @ManyToMany
    @JoinTable(
            name = "panne_equipment",
            joinColumns = @JoinColumn(name = "panne_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipments;

    @Column(name = "date_of_link")
    private LocalDateTime dateOfLink;
}
