package com.itsolutions.equipment_management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipment")
public class Equipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String type;

    @Enumerated(EnumType.STRING)
    private EtatEquipement etatEquipement;
@JsonIgnore
    @ManyToMany(mappedBy = "equipments")
    private Set<Panne> pannes = new HashSet<>();
}
