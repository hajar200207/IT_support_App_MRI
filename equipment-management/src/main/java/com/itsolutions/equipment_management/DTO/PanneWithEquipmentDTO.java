package com.itsolutions.equipment_management.DTO;

import com.itsolutions.equipment_management.models.EtatPanne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PanneWithEquipmentDTO {
    private Long id;
    private String description;
    private LocalDateTime datePanne;
    private EtatPanne etatPanne;
    private List<EquipmentDTO> equipmentList;

}
