//package com.itsolutions.equipment_management.Mapper;
//
//import com.itsolutions.equipment_management.DTO.PanneEquipmentDTO;
//import com.itsolutions.equipment_management.models.PanneEquipment;
//import org.mapstruct.Mapper;
//
//// L'annotation @Mapper est  utiliser pour apple mapstruct.
//// MapStruct est une bibliothèque Java qui génère automatiquement des implémentations de mappage
//// (mapping) pour convertir entre différents types d'objets, en l'occurrence entre des entités et des DTOs.
//
//@Mapper(componentModel = "spring")
//public interface PanneEquipmentMapper {
//
//    // Cette méthode mappe une entité PanneEquipment à un DTO PanneEquipmentDTO.
//    // Elle prend en entrée une instance de PanneEquipment et retourne une instance correspondante de PanneEquipmentDTO.
//    PanneEquipmentDTO toDTO(PanneEquipment panneEquipment);
//
//    // Cette méthode mappe un DTO PanneEquipmentDTO à une entité PanneEquipment.
//    // Elle prend en entrée une instance de PanneEquipmentDTO et retourne une instance correspondante de PanneEquipment.
//    PanneEquipment toEntity(PanneEquipmentDTO panneEquipmentDTO);
//}
