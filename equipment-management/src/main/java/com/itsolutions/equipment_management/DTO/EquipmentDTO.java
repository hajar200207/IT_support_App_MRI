package com.itsolutions.equipment_management.DTO;

import com.itsolutions.equipment_management.models.EtatEquipement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentDTO {
    private Long id;
    private String nom;
    private String type;
    private EtatEquipement etatEquipement;


}

