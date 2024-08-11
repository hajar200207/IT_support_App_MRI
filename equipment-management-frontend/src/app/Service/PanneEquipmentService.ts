import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {PanneEquipment} from "../models/PanneEquipment";

@Injectable({
  providedIn: 'root'
})
export class PanneEquipmentService {
  private baseUrl = 'http://localhost:8080/api/panne-equipment';

  constructor(private http: HttpClient) { }

  getAllPanneEquipments(): Observable<PanneEquipment[]> {
    return this.http.get<PanneEquipment[]>(this.baseUrl);
  }

  getPanneEquipmentById(id: number): Observable<PanneEquipment> {
    return this.http.get<PanneEquipment>(`${this.baseUrl}/${id}`);
  }

  createPanneEquipment(panneEquipment: PanneEquipment): Observable<PanneEquipment> {
    return this.http.post<PanneEquipment>(this.baseUrl, panneEquipment);
  }

  deletePanneEquipment(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
