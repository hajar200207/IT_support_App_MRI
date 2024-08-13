import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Personne } from '../models/personne.model';
import {PersonneService} from "../Service/PersonneService";
import {EtatEquipement} from "../models/equipment.model";

@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css']
})
export class EditUserComponent implements OnInit {
  user: Personne = { nom: '', prenom: '', email: '', type:'',motDePasse:'' };
  id: number | undefined;

  constructor(
    private personneService: PersonneService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.id = +this.route.snapshot.paramMap.get('id')!;
    this.loadUser();
  }

  loadUser(): void {
    if (this.id) {
      this.personneService.getTechnicienById(this.id).subscribe(
        (data: Personne) => {
          this.user = data;
        },
        (error) => {
          console.error('Error loading user data', error);
        }
      );
    }
  }

  updateUser(): void {
    if (this.id) {
      this.personneService.updateUser(this.id, this.user).subscribe(
        (data) => {
          console.log('User updated successfully', data);
          this.router.navigate(['/admin/accounts']);
        },
        (error) => {
          console.error('Error updating user', error);
        }
      );
    }
  }
}
