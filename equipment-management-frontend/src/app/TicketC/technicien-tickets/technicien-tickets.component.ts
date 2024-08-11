import { Component, OnInit } from '@angular/core';
import {EtatTicket, Ticket} from "../../models/Ticket";
import {TicketService} from "../../Service/ticket.service";


@Component({
  selector: 'app-technicien-tickets',
  templateUrl: './technicien-tickets.component.html',
  styleUrls: ['./technicien-tickets.component.css']
})
export class TechnicienTicketsComponent implements OnInit {
  tickets: Ticket[] = [];
  etatTicketOptions = Object.values(EtatTicket);

  constructor(private ticketService: TicketService) {}

  ngOnInit() {
    this.loadTickets();
  }

  loadTickets() {
    // Supposons que nous avons l'ID du technicien connecté
    const technicienId = 1; // À remplacer par l'ID réel du technicien connecté
    this.ticketService.getTicketsByTechnicienId(technicienId).subscribe(
      (tickets) => {
        this.tickets = tickets;
      },
      (error) => {
        console.error('Erreur lors du chargement des tickets', error);
      }
    );
  }

  updateTicketStatus(ticketId: number, newStatus: EtatTicket) {
    this.ticketService.updateTicketStatus(ticketId, newStatus).subscribe(
      (response) => {
        console.log('Statut du ticket mis à jour', response);
        this.loadTickets(); // Recharger les tickets pour refléter les changements
      },
      (error) => {
        console.error('Erreur lors de la mise à jour du statut du ticket', error);
      }
    );
  }
}
