import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {EtatTicket, Ticket} from "../models/Ticket";
import {TicketDTO} from "../DTO/TicketDTO";
import {PersonneService} from "./PersonneService";

@Injectable({
  providedIn: 'root'
})

export class TicketService {
  private apiUrl = 'http://localhost:8080/api/tickets';

  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  };

  constructor(private http: HttpClient , private  personneService :PersonneService ) { }

    createTicket(ticket: {
        etatTicket: any;
        description: any;
        panne: { id: any };
        user: { id: string[]; type: string[] };
        technicien: { id: string[]; type: string[] }
    }): Observable<Ticket> {
    return this.http.post<Ticket>(`${this.apiUrl}/create`, ticket);
  }
  getAllTickets(): Observable<TicketDTO[]> {
    return this.http.get<TicketDTO[]>(`${this.apiUrl}/all`);
  }

  getTicketsByUserId(userId: number): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/user/${userId}`);
  }

  getTicketsByTechnicienId(technicienId: number): Observable<TicketDTO[]> {
    return this.http.get<TicketDTO[]>(`${this.apiUrl}/technicien/${technicienId}`);
  }

  updateTicketStatus(technicienId: number, ticketId: number, etatTicket: string): Observable<string> {
    const token = this.personneService.getToken(); // Assurez-vous que cette méthode retourne le bon token
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });
    return this.http.put<string>(`${this.apiUrl}/update-status/${technicienId}/${ticketId}`, { etatTicket }, { headers });
  }


  assignTicket(ticketId: number, technicienId: number): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/assign/${ticketId}?technicienId=${technicienId}`, this.httpOptions);
  }
}
