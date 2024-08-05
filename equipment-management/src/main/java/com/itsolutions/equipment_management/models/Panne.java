package com.itsolutions.equipment_management.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.time.LocalDateTime;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor @Entity
@Table(name = "Panne")
public class Panne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
   private LocalDateTime datePanne;

    @Enumerated(EnumType.STRING)
    private EtatPanne etatPanne;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

}
