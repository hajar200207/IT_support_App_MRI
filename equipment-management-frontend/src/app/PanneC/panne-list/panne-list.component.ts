import { Component, OnInit } from '@angular/core';
import { Panne } from '../../models/Panne.model';
import { PanneService } from '../../Service/Panne.Service';

@Component({
  selector: 'app-panne-list',
  templateUrl: './panne-list.component.html',
  styleUrls: ['./panne-list.component.css']
})
export class PanneListComponent implements OnInit {
  pannes: Panne[] = [];

  constructor(private panneService: PanneService) {}

  ngOnInit(): void {
    this.loadPannes();
  }

  loadPannes(): void {
    this.panneService.getAllPannes().subscribe((data) => {
      this.pannes = data.map(panne => {
        // Convertir les dates au format Date
        if (panne.datePanne) {
          panne.datePanne = new Date(panne.datePanne);
        }
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

}
