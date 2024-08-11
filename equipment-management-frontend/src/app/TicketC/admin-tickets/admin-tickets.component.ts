import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../Service/ticket.service';
import { TicketDTO } from "../../DTO/TicketDTO";

@Component({
  selector: 'app-admin-tickets',
  templateUrl: './admin-tickets.component.html',
  styleUrls: ['./admin-tickets.component.css']
})
export class AdminTicketsComponent implements OnInit {
  tickets: TicketDTO[] = [];

  constructor(private ticketService: TicketService) {}

  ngOnInit(): void {
    this.loadTickets();
  }

  loadTickets(): void {
    this.ticketService.getAllTickets().subscribe((tickets: TicketDTO[]) => {
      console.log('Fetched tickets:', tickets); // Debugging line
      this.tickets = tickets;
    }, error => {
      console.error('Error loading tickets', error);
    });
  }

  updateTechnician(ticket: TicketDTO): void {
    const newTechnicianId = ticket.newTechnicianId;

    if (newTechnicianId !== undefined && newTechnicianId !== null && newTechnicianId > 0) {
      this.ticketService.assignTicket(ticket.id, newTechnicianId).subscribe({
        next: () => {
          this.loadTickets(); // Recharger les tickets pour refléter la mise à jour
          console.log(`Technician with ID ${newTechnicianId} assigned to ticket ${ticket.id}`);
        },
        error: (error) => {
          console.error('Error updating ticket technician', error);
        }
      });
    } else {
      console.error('Invalid Technician ID provided');
    }
  }
}
