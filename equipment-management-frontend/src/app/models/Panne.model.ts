import {Equipment} from "./equipment.model";

export interface Panne {
  id: number;
  description: string;
  datePanne: Date;
  etatPanne: string;
  equipments: Equipment[];
}
