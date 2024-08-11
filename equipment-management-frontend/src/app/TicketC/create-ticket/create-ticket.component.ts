import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {TicketService} from "../../Service/ticket.service";

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.css']
})
export class CreateTicketComponent implements OnInit {
  ticketForm: FormGroup;

  // Define default values
  defaultTechnicianId = 42;  // Set this to the default technician ID
  defaultTechnicianType = 'technicien';  // Set this to the default technician type

  constructor(private fb: FormBuilder, private ticketService: TicketService) {
    this.ticketForm = this.fb.group({
      description: ['', Validators.required],
      etatTicket: ['OUVERT'],
      userId: [''],
      userType: ['user'],
      technicienId: [this.defaultTechnicianId],  // Set default value
      technicienType: [this.defaultTechnicianType],  // Set default value
      panneId: ['']
    });
  }

  ngOnInit(): void {
    // Initialization logic if needed
  }

  onSubmit(): void {
    if (this.ticketForm.valid) {
      const formValue = this.ticketForm.value;

      // Construct the nested JSON object
      const ticketData = {
        description: formValue.description,
        etatTicket: formValue.etatTicket,
        user: {
          id: formValue.userId,
          type: formValue.userType
        },
        technicien: {
          id: formValue.technicienId,
          type: formValue.technicienType
        },
        panne: {
          id: formValue.panneId
        }
      };

      this.ticketService.createTicket(ticketData).subscribe(
        response => {
          console.log('Ticket created successfully:', response);
          // Handle success
        },
        error => {
          console.error('Error creating ticket:', error);
          // Handle error
        }
      );
    }
  }
}
