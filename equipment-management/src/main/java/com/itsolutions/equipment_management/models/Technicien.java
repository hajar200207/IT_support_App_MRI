package com.itsolutions.equipment_management.models;



import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class Technicien extends Personne {

    private String specialite;
}
