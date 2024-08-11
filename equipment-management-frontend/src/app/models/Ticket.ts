export interface Ticket {
  id: number;
  description: string;
  etatTicket: string;
  user: {
    id: number;
    fonction: string;
  };
  technicien: {
    id: number;
    specialite: string;
  };
  panne: {
    id: number;
    description: string;
  };
  admin :{
    id : number;
    departement:string;
  }
}

export enum EtatTicket {
  OUVERT = 'OUVERT',
  EN_COURS = 'EN_COURS',
  RESOLU = 'RESOLU',
  FERME = 'FERME'
}
