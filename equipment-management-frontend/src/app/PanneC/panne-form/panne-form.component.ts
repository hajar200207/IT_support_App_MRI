import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EtatPanne, Panne } from '../../models/Panne.model';
import { PanneService } from '../../Service/Panne.Service';
import { Equipment } from "../../models/equipment.model";
import { EquipmentService } from "../../Service/equipment.service";

@Component({
  selector: 'app-panne-form',
  templateUrl: './panne-form.component.html',
  styleUrls: ['./panne-form.component.css']
})
export class PanneFormComponent implements OnInit {
  panne: Panne = {
    id: 0,
    description: '',
    datePanne: new Date(),
    etatPanne: EtatPanne.SIGNALE,
    equipmentIds: []
  };

  isEditing: boolean = false;
  etatPanneEnum = EtatPanne;
  equipments: Equipment[] = [];
  selectedEquipmentIds: number[] = [];

  constructor(
    private panneService: PanneService,
    private equipmentService: EquipmentService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const panneId = this.route.snapshot.paramMap.get('id');
    if (panneId) {
      this.isEditing = true;
      this.panneService.getPanneById(Number(panneId)).subscribe((panne) => {
        this.panne = panne;
        // Remplir les IDs des équipements sélectionnés
        this.selectedEquipmentIds = panne.equipmentIds || [];
      });
    }
    this.loadEquipments();
  }

  loadEquipments(): void {
    this.equipmentService.getAllEquipments().subscribe(equipments => {
      this.equipments = equipments;
    });
  }

  isSelected(equipmentId: number): boolean {
    return this.selectedEquipmentIds.includes(equipmentId);
  }

  onCheckboxChange(event: any, equipmentId: number): void {
    if (event.target.checked) {
      // Ajouter l'équipement sélectionné
      this.selectedEquipmentIds.push(equipmentId);
    } else {
      // Retirer l'équipement désélectionné
      this.selectedEquipmentIds = this.selectedEquipmentIds.filter(id => id !== equipmentId);
    }
  }

  savePanne(): void {
    this.panne.equipmentIds = this.selectedEquipmentIds;

    if (this.isEditing) {
      this.panneService.updatePanne(this.panne.id, this.panne).subscribe(
        () => {
          // Gestion du succès
          console.log('Panne mise à jour avec succès');
          this.router.navigate(['/pannes']);
        },
        (error) => {
          // Gestion des erreurs
          console.error('Erreur lors de la mise à jour de la panne:', error);
        }
      );
    } else {
      this.panneService.reportPanne(this.panne).subscribe(
        () => {
          // Gestion du succès
          console.log('Panne signalée avec succès');
          this.router.navigate(['admin/pannes']);
        },
        (error) => {
          // Gestion des erreurs
          console.error('Erreur lors de la signalisation de la panne:', error);
        }
      );
    }
  }
}
