package com.itsolutions.equipment_management.models;

import java.util.List;
import java.util.Optional;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.itsolutions.equipment_management.repositories.EquipmentRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "panne")
// L'annotation @JsonIgnoreProperties(ignoreUnknown = true) permet d'ignorer les propriétés inconnues pour eviter les ereurs

@JsonIgnoreProperties(ignoreUnknown = true)
public class Panne {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    private LocalDateTime datePanne;

    @Enumerated(EnumType.STRING)
    private EtatPanne etatPanne;

    @Transient
    private Set<Long> equipmentIds = new HashSet<>();

    @JsonManagedReference(value = "panne-tickets")
    // Had annotation kat7ayad lik la reference mn le "child" l "parent", yani makatsiftch dik part f JSON.
    @OneToMany(mappedBy = "panne")
    private List<Ticket> tickets;

    @ManyToMany
    @JoinTable(
            name = "panne_equipment",
            joinColumns = @JoinColumn(name = "panne_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private Set<Equipment> equipments = new HashSet<>();

    @Transient
    public void setEquipmentsFromIds(Set<Long> equipmentIds, EquipmentRepository equipmentRepository) {
        this.equipments = equipmentIds.stream()
                .map(equipmentRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());
    }

    // Méthode @Transient utilisée  pour obtonu les ids
    @Transient
    public Set<Long> getEquipmentsIds() {
        return equipments.stream()
                .map(Equipment::getId)
                .collect(Collectors.toSet());
    }

    // Méthode pour obtenir la date de la panne.
    public LocalDateTime getDate() {
        return  this.datePanne;
    }
}
