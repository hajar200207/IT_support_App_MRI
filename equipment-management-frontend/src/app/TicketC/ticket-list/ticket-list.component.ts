import { Component, OnInit } from '@angular/core';
import { TicketService } from '../../Service/ticket.service';
import { TicketDTO } from '../../DTO/TicketDTO';
import {EtatTicket, Ticket} from '../../models/Ticket';

@Component({
  selector: 'app-ticket-list',
  templateUrl: './ticket-list.component.html',
  styleUrls: ['./ticket-list.component.css']
})
export class TicketListComponent implements OnInit {
  tickets: {
    etatTicket: EtatTicket;
    description: string;
    id: number;
    panne: { id: number };
    user: { id: number };
    technicien: { id: number } | null
  }[] = [];

  constructor(private ticketService: TicketService) {}

  ngOnInit(): void {
    this.loadTickets();
  }

  loadTickets(): void {
    this.ticketService.getAllTickets().subscribe((data: TicketDTO[]) => {
      // Map TicketDTO[] to Ticket[]
      this.tickets = data.map(dto => ({
        id: dto.id,
        description: dto.description,
        etatTicket: dto.etatTicket as EtatTicket,
        user: { id: dto.userId, /* other user properties */ },
        technicien: dto.technicienId ? { id: dto.technicienId, /* other technicien properties */ } : null,
        panne: { id: dto.panneId, /* other panne properties */ }
      }));
    }, error => {
      console.error('Error loading tickets', error);
    });
  }
}
