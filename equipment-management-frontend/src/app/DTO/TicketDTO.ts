import {EtatTicket} from "../models/Ticket";

export  interface TicketDTO{
  id: number;
  description: string;
  etatTicket: string;
  userId: number;
  technicienId: number | null; 
  panneId: number;
  newTechnicianId?: number;
  newStatus?: EtatTicket;
  dateCreation: string;
}
