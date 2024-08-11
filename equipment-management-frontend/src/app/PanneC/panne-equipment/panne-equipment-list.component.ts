import { Component, OnInit } from '@angular/core';
import {PanneEquipment} from "../../models/PanneEquipment";
import {PanneEquipmentService} from "../../Service/PanneEquipmentService";

@Component({
  selector: 'app-panne-equipment-list',
  templateUrl: './panne-equipment-list.component.html',
  styleUrls: ['./panne-equipment-list.component.css']
})
export class PanneEquipmentListComponent implements OnInit {
  panneEquipments: PanneEquipment[] = [];

  constructor(private panneEquipmentService: PanneEquipmentService) { }

  ngOnInit(): void {
    this.loadPanneEquipments();
  }

  loadPanneEquipments(): void {
    this.panneEquipmentService.getAllPanneEquipments().subscribe(
      data => {
        this.panneEquipments = data;
      },
      error => {
        console.error('Error fetching panne equipment data', error);
      }
    );
  }
}
