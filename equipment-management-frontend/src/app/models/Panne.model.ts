import { Equipment } from './equipment.model';

export interface Panne {
  id: number;  // Ensure id is always a number and never null
  description: string;
  datePanne: Date;
  etatPanne: EtatPanne;
  equipmentIds: number[];
}

export enum EtatPanne {
  SIGNALE = 'SIGNALE',
  EN_DIAGNOSTIC = 'EN_DIAGNOSTIC',
  EN_REPARATION = 'EN_REPARATION',
  RESOLU = 'RESOLU'
}
