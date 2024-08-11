package com.itsolutions.equipment_management.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Technicien")
@PrimaryKeyJoinColumn(name = "id")
public class Technicien extends Personne {

    @Column(name = "specialite")
    private String specialite;

    @JsonManagedReference(value = "technicien-tickets")
    @OneToMany(mappedBy = "technicien")
    private List<Ticket> tickets;

    public Technicien() {
        super();
        this.setRole(Role.ROLE_TECHNICIEN);
    }
}


