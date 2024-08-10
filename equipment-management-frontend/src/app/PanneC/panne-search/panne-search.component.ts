import { Component } from '@angular/core';
import {EtatPanne, Panne} from '../../models/Panne.model';
import { PanneService } from '../../Service/Panne.Service';

@Component({
  selector: 'app-panne-search',
  templateUrl: './panne-search.component.html',
  styleUrls: ['./panne-search.component.css']
})
export class PanneSearchComponent {
  keyword: string = '';
  etatPanne: EtatPanne | '' = '';
  pannes: Panne[] = [];
  etatPanneOptions: string[] = Object.values(EtatPanne);

  constructor(private panneService: PanneService) {}

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
