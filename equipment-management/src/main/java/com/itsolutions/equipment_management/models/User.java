package com.itsolutions.equipment_management.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@JsonIgnoreProperties(ignoreUnknown = true)

public class User extends Personne {
    private String fonction;

    @JsonManagedReference(value = "user-tickets")
    @OneToMany(mappedBy = "user")
    private List<Ticket> tickets;

    public User() {
        super();
        this.setRole(Role.ROLE_USER);
    }
}
