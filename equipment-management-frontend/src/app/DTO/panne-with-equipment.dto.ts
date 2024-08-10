import {Equipment} from "../models/equipment.model";

export interface PanneWithEquipmentDTO {
  id: number;
  description: string;
  datePanne: string;
  etatPanne: string;
  equipmentList: Equipment[];
}
