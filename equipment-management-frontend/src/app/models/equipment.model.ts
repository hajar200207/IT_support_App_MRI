export enum EtatEquipement {
  FONCTIONNEL = 'FONCTIONNEL',
  EN_PANNE = 'EN_PANNE',
  EN_REPARATION = 'EN_REPARATION',
  HORS_SERVICE = 'HORS_SERVICE'
}
export interface Equipment {
  id?: number;
  nom: string;
  type: string;
  etatEquipement: EtatEquipement;
}
