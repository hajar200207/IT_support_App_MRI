import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {EtatTicket, Ticket} from "../models/Ticket";
import {TicketDTO} from "../DTO/TicketDTO";
import {PersonneService} from "./PersonneService";
import {TicketDTOcreat} from "../DTO/TicketDTOcreat";

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

  createTicket(ticket: TicketDTOcreat): Observable<TicketDTOcreat> {
    return this.http.post<TicketDTOcreat>(`${this.apiUrl}/create`, ticket);
  }
  getAllTickets(): Observable<TicketDTO[]> {
    return this.http.get<TicketDTO[]>(`${this.apiUrl}/all`);
  }

  getTicketsByUserId(userId: number): Observable<TicketDTO[]> {
    return this.http.get<TicketDTO[]>(`${this.apiUrl}/user/${userId}`);
  }

  getTicketsByTechnicienId(technicienId: number): Observable<TicketDTO[]> {
    return this.http.get<TicketDTO[]>(`${this.apiUrl}/technicien/${technicienId}`);
  }

  updateTicketStatus(technicienId: number, ticketId: number, etatTicket: string): Observable<string> {
    const token = this.personneService.getToken();
    const headers = new HttpHeaders({
      'Authorization': `Bearer ${token}`,
      'Content-Type': 'application/json'
    });

    return this.http.put<string>(
      `${this.apiUrl}/update-status/${technicienId}/${ticketId}`,
      { etatTicket },
      { headers, responseType: 'text' as 'json' }
    );
  }





  assignTicket(ticketId: number, technicienId: number): Observable<Ticket> {
    return this.http.put<Ticket>(`${this.apiUrl}/assign/${ticketId}?technicienId=${technicienId}`, this.httpOptions);
  }

  getTotalTickets(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/total`);
  }
}
