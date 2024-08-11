package com.itsolutions.equipment_management.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonBackReference(value = "technicien-tickets")
    @ManyToOne
    @JoinColumn(name = "technicien_id")
    private Technicien technicien;

    @Enumerated(EnumType.STRING)
    private EtatTicket etatTicket;

    @JsonBackReference(value = "user-tickets")
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
//    @JsonBackReference(value = "admin-tickets")
//    @ManyToOne
//    @JoinColumn(name = "admin_id", nullable = false)
//    private Admin admin;
    @JsonBackReference(value = "panne-tickets")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "panne_id")
    private Panne panne;
}
