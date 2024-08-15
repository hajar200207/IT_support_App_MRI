import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../Service/ticket.service';
import { TicketDTO } from '../../DTO/TicketDTO';
import { PersonneService } from '../../Service/PersonneService';
import { Personne } from '../../models/personne.model';

@Component({
  selector: 'app-technicien-tickets',
  templateUrl: './technicien-tickets.component.html',
  styleUrls: ['./technicien-tickets.component.css']
})
export class TechnicienTicketsComponent implements OnInit {
  tickets: TicketDTO[] = [];
  user: Personne | null = null;

  constructor(private ticketService: TicketService, private personneService: PersonneService) {}

  ngOnInit(): void {
    this.loadUserProfile();
  }

  loadUserProfile(): void {
    this.personneService.getUserProfile().subscribe(profile => {
      this.user = profile;
      // Check if user and user.id are defined
      if (this.user?.id !== undefined) {
        this.loadUserTickets(this.user.id);
      }
    });
  }

  loadUserTickets(userId: number): void {
    this.ticketService.getTicketsByTechnicienId(userId).subscribe(tickets => {
      this.tickets = tickets;
    });
  }

  updateStatus(ticketId: number, event: Event): void {
    const target = event.target as HTMLSelectElement;
    const newStatus = target.value;

    if (this.user && this.user.id !== undefined) {
      this.ticketService.updateTicketStatus(this.user.id, ticketId, newStatus).subscribe(
        (response: string) => {
          console.log(response);
          this.loadUserTickets(this.user!.id!);
        },
        (error) => {
          console.error('Error updating ticket status', error);
        }
      );
    }
  }
}
