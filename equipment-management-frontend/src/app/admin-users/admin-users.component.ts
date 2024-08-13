import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { MatDialog } from '@angular/material/dialog';
import { Router } from '@angular/router';
import { Personne } from '../models/personne.model';
import {PersonneService} from "../Service/PersonneService";

@Component({
  selector: 'app-admin-users',
  templateUrl: './admin-users.component.html',
  styleUrls: ['./admin-users.component.css']
})
export class AdminUsersComponent implements OnInit {
  users: Personne[] = [];
  displayedColumns: string[] = ['id', 'name', 'email', 'role', 'actions'];

  constructor(
    private personneService: PersonneService,
    private snackBar: MatSnackBar,
    private dialog: MatDialog,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadUsers();
  }

  loadUsers(): void {
    this.personneService.getAllUsers().subscribe(users => {
      this.users = users;
    });
  }

  editUser(user: Personne): void {
    // Navigate to a user edit form or open a dialog
    this.router.navigate(['/admin/edit-user', user.id]);
  }

  deleteUser(id: number): void {
    if (confirm('Are you sure you want to delete this user?')) {
      this.personneService.deleteUser(id).subscribe(() => {
        this.snackBar.open('User deleted successfully', 'Close', {
          duration: 3000,
        });
        this.loadUsers();
      });
    }
  }
}
