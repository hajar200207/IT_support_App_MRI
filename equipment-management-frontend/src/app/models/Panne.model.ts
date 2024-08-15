
export interface Panne {
  id: number;
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
