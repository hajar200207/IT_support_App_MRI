import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PersonneService } from "../Service/PersonneService";
import { Observable } from 'rxjs';
import { Personne } from '../models/personne.model';
import {EquipmentService} from "../Service/equipment.service";
import {PanneService} from "../Service/Panne.Service";
import {TicketService} from "../Service/ticket.service"; // Adjust the import based on your model's location

@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css']
})
export class AdminDashboardComponent implements OnInit {
  activeSection: string = 'equipments';
  showAdminInfo: boolean = false;
  adminProfile?: Personne;
  totalEquipments: number = 0;
  totalPannes: number = 0;
  totalTickets: number = 0;

  constructor(
    private authService: PersonneService,
    private router: Router,
    private equipmentService: EquipmentService,
    private panneService: PanneService,
    private ticketService: TicketService
  ) {}

  ngOnInit() {
    this.loadCounts();
  }

  setActive(section: string) {
    this.activeSection = section;
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  openAdminInfo() {
    this.authService.getUserProfile().subscribe(profile => {
      this.adminProfile = profile;
      this.showAdminInfo = true;
    });
  }

  closeAdminInfo() {
    this.showAdminInfo = false;
  }

  private loadCounts() {
    this.equipmentService.getTotalEquipments().subscribe(count => this.totalEquipments = count);
    this.panneService.getTotalPannes().subscribe(count => this.totalPannes = count);
    this.ticketService.getTotalTickets().subscribe(count => this.totalTickets = count);
  }
}
