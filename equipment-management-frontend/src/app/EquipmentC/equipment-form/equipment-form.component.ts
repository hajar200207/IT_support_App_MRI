// src/app/components/equipment-form/equipment-form.component.ts
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Equipment, EtatEquipement } from '../../models/equipment.model';
import {EquipmentService} from "../../Service/equipment.service";

@Component({
  selector: 'app-equipment-form',
  templateUrl: './equipment-form.component.html',
  styleUrls: ['./equipment-form.component.css']
})
export class EquipmentFormComponent implements OnInit {
  equipment: Equipment = { nom: '', type: '', etatEquipement: EtatEquipement.FONCTIONNEL };
  isEditing = false;
  etatEquipements = Object.values(EtatEquipement);

  constructor(
    private equipmentService: EquipmentService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditing = true;
      this.equipmentService.getAllEquipments().subscribe(data => {
        this.equipment = data.find(e => e.id === +id) || this.equipment;
      });
    }
  }

  save(): void {
    if (this.isEditing) {
      this.equipmentService.updateEquipment(this.equipment.id as number, this.equipment).subscribe(() => {
        this.router.navigate(['/equipments']);
      });
    } else {
      this.equipmentService.addEquipment(this.equipment).subscribe(() => {
        this.router.navigate(['/equipments']);
      });
    }
  }
}
