import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Panne} from "../models/Panne.model";

@Injectable({
  providedIn: 'root'
})
export class PanneService {
  private apiUrl = 'http://localhost:8080/api/pannes';

  constructor(private http: HttpClient) { }

  reportPanne(panne: Panne): Observable<Panne> {
    return this.http.post<Panne>(`${this.apiUrl}/report`, panne);
  }

  getPannesByEquipmentId(equipmentId: number): Observable<Panne[]> {
    return this.http.get<Panne[]>(`${this.apiUrl}/equipment/${equipmentId}`);
  }

  getAllPannes(): Observable<Panne[]> {
    return this.http.get<Panne[]>(`${this.apiUrl}/`);
  }

  updatePanne(id: number, panne: Panne): Observable<Panne> {
    return this.http.put<Panne>(`${this.apiUrl}/${id}`, panne);
  }

  deletePanne(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  searchPannesByDescription(keyword: string): Observable<Panne[]> {
    return this.http.get<Panne[]>(`${this.apiUrl}/search`, { params: { keyword } });
  }

  searchPannesByEtat(etatPanne: string): Observable<Panne[]> {
    return this.http.get<Panne[]>(`${this.apiUrl}/search/etat`, { params: { etatPanne } });
  }
}
