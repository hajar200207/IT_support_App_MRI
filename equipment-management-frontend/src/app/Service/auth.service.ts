import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, BehaviorSubject } from 'rxjs';
import { User } from '../models/user.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/users';
  private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  constructor(private http: HttpClient, private router: Router) {}

  getToken(): string | null {
    return localStorage.getItem('token'); // Adjust based on your actual token storage
  }

  login(email: string, password: string): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, { email, motDePasse: password });
  }

  register(user: User): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/register`, user);
  }

  setCurrentUser(user: User) {
    this.currentUserSubject.next(user);
  }

  getCurrentUser(): User | null {
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
}
