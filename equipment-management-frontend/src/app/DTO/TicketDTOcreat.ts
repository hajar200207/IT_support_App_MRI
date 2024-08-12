export interface TicketDTOcreat {
  id: number;
  description: string;
  etatTicket: EtatTicket;
  user: {
    id: number;
    type:string;
  };
  panne: {
    id: number;
  };
}

export enum EtatTicket {
  OUVERT = 'OUVERT',
  EN_COURS = 'EN_COURS',
  RESOLU = 'RESOLU',
  FERME = 'FERME'
}
