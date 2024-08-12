import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TicketService } from '../../Service/ticket.service';
import { TicketDTOcreat, EtatTicket } from '../../DTO/TicketDTOcreat';

@Component({
  selector: 'app-create-ticket',
  templateUrl: './create-ticket.component.html',
  styleUrls: ['./create-ticket.component.css']
})
export class CreateTicketComponent implements OnInit {
  ticketForm: FormGroup;

  constructor(private fb: FormBuilder, private ticketService: TicketService) {
    this.ticketForm = this.fb.group({
      description: ['', Validators.required],
      etatTicket: ['OUVERT'],
      userId: ['', Validators.required],
      userType: ['user'], // Hidden input field value
      panneId: ['', Validators.required]
    });

  }

  ngOnInit(): void {}

  onSubmit(): void {
    if (this.ticketForm.valid) {
      const formValue = this.ticketForm.value;

      const ticketData: TicketDTOcreat = {
        id: 0, // Set id to 0 or remove it if not needed, assuming backend assigns an id
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
          // Handle success
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
