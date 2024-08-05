package com.itsolutions.equipment_management.models;


import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Entity
public class User extends Personne {
    private String fonction;

    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;
}