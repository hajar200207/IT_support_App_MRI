package com.itsolutions.equipment_management.Mapper;

import com.itsolutions.equipment_management.DTO.PanneEquipmentDTO;
import com.itsolutions.equipment_management.models.PanneEquipment;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PanneEquipmentMapper {
    PanneEquipmentDTO toDTO(PanneEquipment panneEquipment);
    PanneEquipment toEntity(PanneEquipmentDTO panneEquipmentDTO);
}
