package com.itsolutions.equipment_management.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


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
    private LocalDateTime dateCreation;

    @Enumerated(EnumType.STRING)
    private EtatTicket etatTicket;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;


}

