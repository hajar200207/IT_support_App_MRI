package com.itsolutions.equipment_management.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "User")
public class User extends Personne {
    private String fonction;

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Ticket> tickets;

    public User() {
        super();
        this.setRole(Role.ROLE_USER);
    }
}
