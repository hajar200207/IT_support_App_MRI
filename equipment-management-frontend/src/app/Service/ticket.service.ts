import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {EtatTicket, Ticket} from "../models/Ticket";
import {TicketDTO} from "../DTO/TicketDTO";

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

  constructor(private http: HttpClient) { }

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

  // Récupération des tickets par ID utilisateur
  getTicketsByUserId(userId: number): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/user/${userId}`);
  }

  // Récupération des tickets par ID technicien
  getTicketsByTechnicienId(technicienId: number): Observable<Ticket[]> {
    return this.http.get<Ticket[]>(`${this.apiUrl}/technicien/${technicienId}`);
  }

  // Mise à jour du statut d'un ticket
  updateTicketStatus(ticketId: number, etatTicket: EtatTicket): Observable<string> {
    return this.http.put<string>(`${this.apiUrl}/update-status/${ticketId}`, { etatTicket }, this.httpOptions);
  }

  // Assignation d'un ticket à un technicien
  assignTicket(ticketId: number, technicienId: number): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/assign/${ticketId}?technicienId=${technicienId}`, this.httpOptions);
  }
}
