package com.itsolutions.equipment_management.models;



import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @Entity
public abstract class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String prenom;
    private String email;
    private String motDePasse;
}
