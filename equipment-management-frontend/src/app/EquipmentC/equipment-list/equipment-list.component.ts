import { Component, OnInit } from '@angular/core';
import { Equipment } from '../../models/equipment.model';
import {EquipmentService} from "../../Service/equipment.service";

@Component({
  selector: 'app-equipment-list',
  templateUrl: './equipment-list.component.html',
  styleUrls: ['./equipment-list.component.css']
})
export class EquipmentListComponent implements OnInit {
  equipments: Equipment[] = [];
  searchTerm: string = '';


  constructor(private equipmentService: EquipmentService) { }

  ngOnInit(): void {
    this.loadEquipments();
  }

  loadEquipments(): void {
    this.equipmentService.getAllEquipments().subscribe(data => {

      this.equipments = data;
    });
  }
  search(): void {
    if (this.searchTerm.trim()) {
      this.equipmentService.getEquipmentByNom(this.searchTerm).subscribe(equipments => {
        this.equipments = equipments;
      });
    } else {
      this.loadEquipments();
    }
  }
  deleteEquipment(id?: number): void {
    if (id !== undefined) {
      this.equipmentService.deleteEquipment(id).subscribe(() => {
      });
    } else {
      console.error('ID is not defined');
    }
  }

}
