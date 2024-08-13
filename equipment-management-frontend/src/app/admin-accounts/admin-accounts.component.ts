import { Component, OnInit } from '@angular/core';
import { Personne } from '../models/personne.model';
import {PersonneService} from "../Service/PersonneService";

@Component({
  selector: 'app-admin-accounts',
  templateUrl: './admin-accounts.component.html',
  styleUrls: ['./admin-accounts.component.css']
})
export class AdminAccountsComponent implements OnInit {
  users: Personne[] = [];

  constructor(private personneService: PersonneService) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.personneService.getAllUsers().subscribe(
      data => this.users = data,
      error => console.error('Error fetching users', error)
    );
  }
}
