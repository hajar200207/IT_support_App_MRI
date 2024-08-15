import { Injectable } from '@angular/core'; // Permet à Angular de reconnaître cette classe comme un service injectable
import { HttpClient } from '@angular/common/http'; // Permet de faire des requêtes HTTP
import { Router } from '@angular/router'; // Permet de naviguer entre les routes de l'application
import { Observable, BehaviorSubject, throwError } from 'rxjs'; // RxJS pour la gestion des flux de données asynchrones
import { catchError } from 'rxjs/operators'; // Permet de gérer les erreurs lors des appels HTTP
import { Personne } from '../models/personne.model'; // Modèle pour les utilisateurs
import { Technicien } from "../models/technicien.model"; // Modèle pour les techniciens, assurez-vous que le chemin est correct

@Injectable({
  providedIn: 'root' // Le service sera disponible dans toute l'application
})
export class PersonneService {
  private apiUrl = 'http://localhost:8080/api/users'; // URL de base pour les requêtes API
  private currentUserSubject = new BehaviorSubject<Personne | null>(null); // Sujet pour stocker l'utilisateur courant
  public currentUser$ = this.currentUserSubject.asObservable(); // Observable pour observer les changements de l'utilisateur courant

  constructor(private http: HttpClient, private router: Router) {}

  // Méthode pour stocker le token dans le stockage local du navigateur
  setToken(token: string) {
    localStorage.setItem('token', token);
  }

  // Méthode pour récupérer le token depuis le stockage local
  getToken(): string | null {
    return localStorage.getItem('token');
  }

  // Méthode pour se connecter
  login(loginData: { email: string; motDePasse: string}): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}/login`, loginData).pipe(
      catchError(error => {
        console.error('Login error', error); // Affiche une erreur en cas de problème lors de la connexion
        return throwError(error); // Renvoie l'erreur pour être traitée ailleurs
      })
    );
  }

  // Méthode pour enregistrer un nouvel utilisateur
  register(user: Personne): Observable<any> {
    console.log("ser"+user.role); // Affiche le rôle de l'utilisateur dans la console
    console.log("ser"+user.email); // Affiche l'email de l'utilisateur dans la console
    console.log("ser"+user.motDePasse); // Affiche le mot de passe de l'utilisateur dans la console
    return this.http.post<any>(`${this.apiUrl}/register`, user); // Envoie les données de l'utilisateur à l'API pour l'enregistrement
  }

  // Méthode pour mettre à jour l'utilisateur courant dans le BehaviourSubject
  setCurrentUser(user: Personne) {
    this.currentUserSubject.next(user);
  }

  // Méthode pour récupérer l'utilisateur courant stocké dans le BehaviourSubject
  getCurrentUser(): Personne | null {
    return this.currentUserSubject.value;
  }

  // Méthode pour se déconnecter
  logout() {
    this.currentUserSubject.next(null); // Réinitialise l'utilisateur courant
    localStorage.removeItem('token'); // Supprime le token du stockage local
    this.router.navigate(['/login']); // Redirige vers la page de connexion
  }

  // Méthode pour rediriger l'utilisateur vers le tableau de bord approprié selon son rôle
  redirectToDashboard() {
    const user = this.getCurrentUser();
    if (user) {
      switch (user.role) {
        case 'ROLE_ADMIN':
          this.router.navigate(['/admin-dashboard']); // Redirection vers le tableau de bord admin
          break;
        case 'ROLE_USER':
          this.router.navigate(['/user-dashboard']); // Redirection vers le tableau de bord utilisateur
          break;
        case 'ROLE_TECHNICIEN':
          this.router.navigate(['/technician-dashboard']); // Redirection vers le tableau de bord technicien
          break;
        default:
          this.router.navigate(['/login']); // Redirection vers la page de connexion si le rôle ne correspond à aucun
          break;
      }
    } else {
      this.router.navigate(['/login']); // Redirection vers la page de connexion si aucun utilisateur n'est connecté
    }
  }

  // Méthode pour récupérer le profil de l'utilisateur courant
  getUserProfile(): Observable<Personne> {
    return this.http.get<Personne>(`${this.apiUrl}/profile`);
  }

  // Méthode pour récupérer tous les techniciens
  getAllTechniciens(): Observable<Technicien[]> {
    return this.http.get<Technicien[]>(`${this.apiUrl}/techniciens`).pipe(
      catchError(error => {
        console.error('Error fetching techniciens', error); // Affiche une erreur en cas de problème lors de la récupération des techniciens
        return throwError(error); // Renvoie l'erreur pour être traitée ailleurs
      })
    );
  }

  // Méthode pour récupérer un technicien par ID
  getTechnicienById(id: number): Observable<Technicien> {
    return this.http.get<Technicien>(`${this.apiUrl}/techniciens/${id}`);
  }

  // Méthode pour récupérer tous les utilisateurs
  getAllUsers(): Observable<Personne[]> {
    return this.http.get<Personne[]>(`${this.apiUrl}/all`);
  }

  // Méthode pour supprimer un utilisateur par ID
  deleteUser(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/delete/${id}`);
  }

  // Méthode pour mettre à jour les informations d'un utilisateur par ID
  updateUser(id: number, user: Personne): Observable<Personne> {
    return this.http.put<Personne>(`${this.apiUrl}/update/${id}`, user);
  }
}
