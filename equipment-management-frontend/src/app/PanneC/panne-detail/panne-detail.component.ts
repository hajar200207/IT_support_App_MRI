import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import {PanneWithEquipmentDTO} from "../../DTO/panne-with-equipment.dto";
import {PanneService} from "../../Service/Panne.Service";

@Component({
  selector: 'app-panne-detail',
  templateUrl: './panne-detail.component.html',
  styleUrls: ['./panne-detail.component.css']
})
export class PanneDetailComponent implements OnInit {
  panne: PanneWithEquipmentDTO | undefined;

  constructor(
    private panneService: PanneService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.panneService.getPanneWithEquipment(id).subscribe((data) => {
      this.panne = data;
    });
  }
}
