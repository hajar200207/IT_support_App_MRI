// technicien-tickets.component.ts
import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../Service/ticket.service';
import { TicketDTO } from '../../DTO/TicketDTO';

@Component({
  selector: 'app-technicien-tickets',
  templateUrl: './technicien-tickets.component.html',
  styleUrls: ['./technicien-tickets.component.css']
})
export class TechnicienTicketsComponent implements OnInit {
  tickets: TicketDTO[] = [];
  technicienId = 42; // Remplace avec l'ID du technicien connecté, si disponible

  constructor(private ticketService: TicketService) { }

  ngOnInit(): void {
    this.loadTickets();
  }

  loadTickets(): void {
    this.ticketService.getTicketsByTechnicienId(this.technicienId).subscribe(
      (tickets: TicketDTO[]) => {
        this.tickets = tickets;
      },
      (error) => {
        console.error('Erreur lors de la récupération des tickets', error);
      }
    );
  }

  updateStatus(ticketId: number, event: Event): void {
    const target = event.target as HTMLSelectElement;
    const newStatus = target.value;
    this.ticketService.updateTicketStatus(this.technicienId, ticketId, newStatus).subscribe(
      (response: string) => {
        console.log(response);
        this.loadTickets(); // Recharge les tickets pour voir les changements
      },
      (error) => {
        console.error('Erreur lors de la mise à jour du statut du ticket', error);
      }
    );
  }

}
