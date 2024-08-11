import {Panne} from "./Panne.model";
import {Equipment} from "./equipment.model";

export interface PanneEquipment {
  id: number;
  panne: Panne;
  equipment: Equipment;
  dateOfLink: Date;
}
