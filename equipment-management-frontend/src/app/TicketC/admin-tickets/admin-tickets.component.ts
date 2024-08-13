import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../Service/ticket.service';
import { TicketDTO } from "../../DTO/TicketDTO";
import {error} from "@angular/compiler-cli/src/transformers/util";

@Component({
  selector: 'app-admin-tickets',
  templateUrl: './admin-tickets.component.html',
  styleUrls: ['./admin-tickets.component.css']
})
export class AdminTicketsComponent implements OnInit {
  tickets: TicketDTO[] = [];
  displayedColumns: string[] = ['id', 'description', 'status', 'userId', 'technicianId', 'panneId', 'updateTechnician'];

  constructor(private ticketService: TicketService) {}

  ngOnInit(): void {
    this.loadTickets();
  }
  loadTickets(): void {
    this.ticketService.getAllTickets().subscribe({
      next: (response) => {
        console.log('Fetched tickets:', response); // Debugging line
        this.tickets = response;
      },
      error: (error) => {
        console.error('Error loading tickets', error);
      }
    });
  }


  updateTechnician(ticket: TicketDTO): void {
    const newTechnicianId = ticket.newTechnicianId;

    if (newTechnicianId !== undefined && newTechnicianId !== null && newTechnicianId > 0) {
      this.ticketService.assignTicket(ticket.id, newTechnicianId).subscribe({
        next: (response) => {
          this.loadTickets(); // Reload tickets to reflect the update
          console.log(response); // This will log "Ticket status updated successfully"
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
