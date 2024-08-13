import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, BehaviorSubject, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Personne } from '../models/personne.model';
import {Technicien} from "../models/technicien.model"; // Ensure this path is correct

@Injectable({
  providedIn: 'root'
})
export class PersonneService {
  private apiUrl = 'http://localhost:8080/api/users';
  private currentUserSubject = new BehaviorSubject<Personne | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient, private router: Router) {}

  setToken(token: string) {
    localStorage.setItem('token', token);
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  login(loginData: { email: string; motDePasse: string; type: string }): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, loginData).pipe(
      catchError(error => {
        console.error('Login error', error);
        return throwError(error);
      })
    );
  }

  register(user: Personne): Observable<any> {
    console.log("ser"+user.role);
    console.log("ser"+user.email);
    console.log("ser"+user.motDePasse);
    return this.http.post<any>(`${this.apiUrl}/register`, user);
  }

  setCurrentUser(user: Personne) {
    this.currentUserSubject.next(user);
  }

  getCurrentUser(): Personne | null {
    return this.currentUserSubject.value;
  }

  logout() {
    this.currentUserSubject.next(null);
    localStorage.removeItem('token');
    this.router.navigate(['/login']);
  }

  redirectToDashboard() {
    const user = this.getCurrentUser();
    if (user) {
      switch (user.role) {
        case 'ROLE_ADMIN':
          this.router.navigate(['/admin-dashboard']);
          break;
        case 'ROLE_USER':
          this.router.navigate(['/user-dashboard']);
          break;
        case 'ROLE_TECHNICIEN':
          this.router.navigate(['/technician-dashboard']);
          break;
        default:
          this.router.navigate(['/login']);
          break;
      }
    } else {
      this.router.navigate(['/login']);
    }
  }

  getUserProfile(): Observable<Personne> {
    return this.http.get<Personne>(`${this.apiUrl}/profile`);
  }
  getAllTechniciens(): Observable<Technicien[]> {
    return this.http.get<Technicien[]>(`${this.apiUrl}/techniciens`).pipe(
      catchError(error => {
        console.error('Error fetching techniciens', error);
        return throwError(error);
      })
    );
  }
  getTechnicienById(id: number): Observable<Technicien> {
    return this.http.get<Technicien>(`${this.apiUrl}/techniciens/${id}`);
  }
  getAllUsers(): Observable<Personne[]> {
    return this.http.get<Personne[]>(`${this.apiUrl}/all`);
  }
  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  updateUser(id: number, user: Personne): Observable<Personne> {
    return this.http.put<Personne>(`${this.apiUrl}/update/${id}`, user);
  }
}
