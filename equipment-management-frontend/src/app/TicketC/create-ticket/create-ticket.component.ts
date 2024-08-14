import { Component, OnInit } from '@angular/core';
import { PanneEquipment } from "../../models/PanneEquipment";
import { PanneEquipmentService } from "../../Service/PanneEquipmentService";
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TicketService } from '../../Service/ticket.service';
import { TicketDTOcreat, EtatTicket } from '../../DTO/TicketDTOcreat';
import { PersonneService } from '../../Service/PersonneService';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.css']
})
export class CreateTicketComponent implements OnInit {
  ticketForm: FormGroup;
  panneEquipments: PanneEquipment[] = [];

  constructor(
    private fb: FormBuilder,
    private ticketService: TicketService,
    private personneService: PersonneService,
    private panneEquipmentService: PanneEquipmentService
  ) {
    this.ticketForm = this.fb.group({
      description: ['', Validators.required],
      etatTicket: [EtatTicket.OUVERT],
      userId: [''], // Hidden field
      userType: ['user'], // Hidden field value
      panneId: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.setUserId();
    this.loadPanneEquipments();
  }

  private setUserId(): void {
    this.personneService.getUserProfile().subscribe(profile => {
      if (profile && profile.id) {
        this.ticketForm.patchValue({ userId: profile.id });
      }
    });
  }

  private loadPanneEquipments(): void {
    this.panneEquipmentService.getAllPanneEquipments().subscribe(
      data => {
        this.panneEquipments = data;
      },
      error => {
        console.error('Error fetching panne equipment data', error);
      }
    );
  }

  onSubmit(): void {
    if (this.ticketForm.valid) {
      const formValue = this.ticketForm.value;

      const ticketData: TicketDTOcreat = {
        id: 0, // Backend will handle ID generation
        description: formValue.description,
        etatTicket: formValue.etatTicket,
        user: {
          id: formValue.userId,
          type: formValue.userType
        },
        panne: {
          id: formValue.panneId
        }
      };

      this.ticketService.createTicket(ticketData).subscribe(
        response => {
          console.log('Ticket created successfully:', response);
          // Handle success, maybe redirect or show a message
        },
        error => {
          console.error('Error creating ticket:', error);
          // Handle error
        }
      );
    }
  }

  protected readonly EtatTicket = EtatTicket;
}
