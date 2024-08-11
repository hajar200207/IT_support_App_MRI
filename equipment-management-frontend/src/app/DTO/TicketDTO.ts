export  interface TicketDTO{
  id: number;
  description: string;
  etatTicket: string;
  userId: number;          // Updated from user: { id: number; ... }
  technicienId: number | null; // Updated from technicien: { id: number; ... }
  panneId: number;
  newTechnicianId?: number;
}
