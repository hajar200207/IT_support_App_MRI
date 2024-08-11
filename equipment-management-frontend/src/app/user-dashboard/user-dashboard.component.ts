import { Component, OnInit } from '@angular/core';
import {Personne} from "../models/personne.model";
import {PersonneService} from "../Service/PersonneService";

@Component({
  selector: 'app-user-dashboard',
  templateUrl: './user-dashboard.component.html',
  styleUrls: ['./user-dashboard.component.css']
})
export class UserDashboardComponent implements OnInit {
  user: Personne | null = null;

  constructor(private personneService: PersonneService) {}

  ngOnInit(): void {
    this.loadUserProfile();
  }

  loadUserProfile(): void {
    this.personneService.getUserProfile().subscribe(profile => {
      this.user = profile;
    });
  }
}
