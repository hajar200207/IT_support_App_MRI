package com.itsolutions.equipment_management.models;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class Admin extends Personne {

    private String departement;
}