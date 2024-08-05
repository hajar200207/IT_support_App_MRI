package com.itsolutions.equipment_management.models;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class User extends Personne {
    private String fonction;
    public User() {
        this.setRole("ROLE_USER");
    }
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
}