import { Component, OnInit } from '@angular/core';
import { Personne } from '../models/personne.model';
import { PersonneService } from '../Service/PersonneService';
import { TicketService } from '../Service/ticket.service';
import { TicketDTO } from '../DTO/TicketDTO';
import {Router} from "@angular/router";

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {
  user: Personne | null = null;
  tickets: TicketDTO[] = [];
  showUserInfo: boolean = false;
  activeSection: string = '';

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
  openUserInfo(): void {
    this.showUserInfo = true;
  }

  closeUserInfo(): void {
    this.showUserInfo = false;
  }

  setActive(section: string): void {
    this.activeSection = section;
  }

  logout(): void {
    this.router.navigate(['/login']);
  }
}
