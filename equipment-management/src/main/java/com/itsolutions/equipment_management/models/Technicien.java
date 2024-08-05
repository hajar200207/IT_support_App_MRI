package com.itsolutions.equipment_management.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
@Table(name = "Technicien")
public class Technicien extends Personne {
    private String specialite;

    @Override
    public String getRole() {
        return "ROLE_TECHNICIEN";
    }
    public Technicien() {
        super();
        this.setRole("ROLE_TECHNICIEN");
    }

}