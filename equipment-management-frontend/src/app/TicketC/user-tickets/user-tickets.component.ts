import { Component } from '@angular/core';
import {TicketDTO} from "../../DTO/TicketDTO";
import {PersonneService} from "../../Service/PersonneService";
import {TicketService} from "../../Service/ticket.service";
import {Router} from "@angular/router";
import {Personne} from "../../models/personne.model";

@Component({
  selector: 'app-user-tickets',
  templateUrl: './user-tickets.component.html',
  styleUrls: ['./user-tickets.component.css']
})
export class UserTicketsComponent {
  tickets: TicketDTO[] = [];
  user: Personne | null = null;
  constructor(
    private personneService: PersonneService,
    private ticketService: TicketService,
    private router: Router
  ) {}
  ngOnInit(): void {
    this.loadUserProfile();
  }

  loadUserProfile(): void {
    this.personneService.getUserProfile().subscribe(profile => {
      this.user = profile;
      if (this.user && this.user.id) {
        this.loadUserTickets(this.user.id);
      }
    });
  }

  loadUserTickets(userId: number): void {
    this.ticketService.getTicketsByUserId(userId).subscribe(tickets => {
      this.tickets = tickets;
    });
  }
}
