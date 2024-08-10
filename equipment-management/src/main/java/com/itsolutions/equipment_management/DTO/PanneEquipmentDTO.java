package com.itsolutions.equipment_management.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PanneEquipmentDTO {
    private Long id;
    private Long panneId;
    private Long equipmentId;
    private Date date;
    private String description;

}
