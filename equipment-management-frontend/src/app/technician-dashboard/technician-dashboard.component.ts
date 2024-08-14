import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonneService } from '../Service/PersonneService';
import { TicketService } from '../Service/ticket.service';
import { Personne } from '../models/personne.model';

@Component({
  selector: 'app-technician-dashboard',
  templateUrl: './technician-dashboard.component.html',
  styleUrls: ['./technician-dashboard.component.css']
})
export class TechnicianDashboardComponent implements OnInit {
  activeSection: string = 'technician-tickets';
  showTechnicianInfo: boolean = false;
  technicianProfile?: Personne;
  totalTechnicianTickets: number = 0;

  constructor(
    private personneService: PersonneService,
    private router: Router,
    private ticketService: TicketService
  ) {}

  ngOnInit() {
    this.loadTechnicianTicketsCount();
  }

  setActive(section: string) {
    this.activeSection = section;
  }

  logout() {
    this.personneService.logout();
    this.router.navigate(['/login']);
  }

  openTechnicianInfo() {
    this.personneService.getUserProfile().subscribe(profile => {
      this.technicianProfile = profile;
      this.showTechnicianInfo = true;
    });
  }

  closeTechnicianInfo() {
    this.showTechnicianInfo = false;
  }

  private loadTechnicianTicketsCount() {
    // Fetch the technician profile or ID as needed
    this.personneService.getUserProfile().subscribe(profile => {
      const technicienId = profile.id; // Adjust this according to your actual profile structure

      if (technicienId) {
        this.ticketService.countTicketsByTechnicienId(technicienId).subscribe(count => {
          this.totalTechnicianTickets = count;
        });
      }
    });
  }
}
