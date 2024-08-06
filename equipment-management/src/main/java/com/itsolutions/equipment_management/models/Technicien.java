package com.itsolutions.equipment_management.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@Table(name = "Technicien")
@PrimaryKeyJoinColumn(name = "id")
public class Technicien extends Personne {
    @Column(name = "specialite")
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