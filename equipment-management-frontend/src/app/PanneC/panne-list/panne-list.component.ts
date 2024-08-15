import { Component, OnInit } from '@angular/core';
import {EtatPanne, Panne} from '../../models/Panne.model';
import { PanneService } from '../../Service/Panne.Service';

@Component({
  selector: 'app-panne-list',
  templateUrl: './panne-list.component.html',
  styleUrls: ['./panne-list.component.css']
})
export class PanneListComponent implements OnInit {
  pannes: Panne[] = [];
  keyword: string = '';
  etatPanne: EtatPanne | '' = '';
  etatPanneOptions: string[] = Object.values(EtatPanne);

  constructor(private panneService: PanneService) {}

  ngOnInit(): void {
    this.loadPannes();
  }

  loadPannes(): void {
    this.panneService.getAllPannes().subscribe((data) => {
      this.pannes = data.map(panne => {
        return panne;
      });
    });
  }

  deletePanne(id: number): void {
    if (id && confirm('Are you sure you want to delete this panne?')) {
      this.panneService.deletePanne(id).subscribe(() => {
        this.loadPannes();
      });
    }
  }
  searchByDescription(): void {
    this.panneService.searchPannesByDescription(this.keyword).subscribe((data) => {
      this.pannes = data;
    });
  }

  searchByEtat(): void {
    this.panneService.searchPannesByEtat(this.etatPanne).subscribe((data) => {
      this.pannes = data;
    });
  }
}
