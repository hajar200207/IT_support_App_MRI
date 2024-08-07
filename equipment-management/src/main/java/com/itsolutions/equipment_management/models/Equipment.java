package com.itsolutions.equipment_management.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.List;
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

    @OneToMany(mappedBy = "equipment")
    private List<PanneEquipment> panneEquipments;



    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
