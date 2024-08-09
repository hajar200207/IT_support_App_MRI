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
@Table(name = "panne")
public class Panne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private LocalDateTime datePanne;

    @Enumerated(EnumType.STRING)
    private EtatPanne etatPanne;

    @ManyToMany(mappedBy = "pannes")
    private Set<Equipment> equipments;
}
