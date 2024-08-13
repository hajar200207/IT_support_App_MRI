import { Injectable } from '@angular/core';
import {HttpClient, HttpErrorResponse, HttpHeaders} from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Panne } from '../models/Panne.model';
import { PanneWithEquipmentDTO } from '../DTO/panne-with-equipment.dto';
import {PersonneService} from "./PersonneService";



@Injectable({
  providedIn: 'root'
})
export class PanneService {
  private apiUrl = 'http://localhost:8080/api/pannes';
  private apiurlhis=' http://localhost:8080/api/panne-equipment'
  private getHeaders(): HttpHeaders {
    const token = this.personneService.getToken();
    let headers = new HttpHeaders();
    if (token) {
      headers = headers.set('Authorization', `Bearer ${token}`);
    }
    return headers;
  }
  constructor(private http: HttpClient , private    personneService:PersonneService) {}

  reportPanne(panne: Panne): Observable<Panne> {
    return this.http.post<Panne>(`${this.apiUrl}/report`, panne);
  }

  getPannesByEquipmentId(equipmentId: number): Observable<Panne[]> {
    return this.http.get<Panne[]>(`${this.apiUrl}/equipment/${equipmentId}`).pipe(
      catchError(this.handleError)
    );
  }

  getAllPannes(): Observable<Panne[]> {
    return this.http.get<Panne[]>(`${this.apiUrl}/`).pipe(
      catchError(this.handleError)
    );
  }


  updatePanne(id: number, panne: Panne): Observable<Panne> {
    return this.http.put<Panne>(`${this.apiUrl}/${id}`, panne, { headers: this.getHeaders() }).pipe(
      catchError(this.handleError)
    );
  }


  deletePanne(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  searchPannesByDescription(keyword: string): Observable<Panne[]> {
    return this.http.get<Panne[]>(`${this.apiUrl}/search`, { params: { keyword } }).pipe(
      catchError(this.handleError)
    );
  }

  searchPannesByEtat(etatPanne: string): Observable<Panne[]> {
    return this.http.get<Panne[]>(`${this.apiUrl}/search/etat`, { params: { etatPanne } }).pipe(
      catchError(this.handleError)
    );
  }

  getPanneById(id: number): Observable<Panne> {
    return this.http.get<Panne>(`${this.apiUrl}/${id}`).pipe(
      catchError(this.handleError)
    );
  }

  getPanneWithEquipment(id: number): Observable<PanneWithEquipmentDTO> {
    return this.http.get<PanneWithEquipmentDTO>(`${this.apiUrl}/${id}/details`).pipe(
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    // Handle the error as needed
    console.error('An error occurred:', error.error.message);
    return throwError(() => new Error('An error occurred; please try again later.'));
  }



  getTotalPannes(): Observable<number> {
    return this.http.get<number>(`${this.apiUrl}/total`);
  }
}
