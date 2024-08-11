package com.itsolutions.equipment_management.models;

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
@Table(name = "admin")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Admin extends Personne {
    private String departement;
    @JsonManagedReference(value = "admin-tickets")
    @OneToMany(mappedBy = "admin")
    private List<Ticket> tickets;
    public Admin() {
        super();
        this.setRole(Role.ROLE_ADMIN);
    }
}
