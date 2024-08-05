package com.itsolutions.equipment_management.models;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter @Entity
@Table(name = "User")
public class User extends Personne {
    private String fonction;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    @Override
    public String getRole() {
        return "ROLE_USER";
    }
    public User() {
        this.setRole("ROLE_USER");
    }

}