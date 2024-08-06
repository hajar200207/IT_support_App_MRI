package com.itsolutions.equipment_management.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Ticket")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private Date dateCreation;
    @PrePersist
    protected void onCreate() {
        this.dateCreation = new Date();
    }
    @ManyToOne
    @JoinColumn(name = "technicien_id")
    private Technicien technicien;
    @Enumerated(EnumType.STRING)
    private EtatTicket etatTicket;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;
    @ManyToOne
    @JoinColumn(name = "panne_id")
    private Panne panne;

}

