package com.itsolutions.equipment_management.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
// TicketDTO.java
public class TicketDTO {
    private Long id;
    private String description;
    private String etatTicket;
    private Long panneId;
    private Long userId;
    private Long technicienId;

}
