import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PersonneService } from "../Service/PersonneService";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string = '';

  constructor(private fb: FormBuilder, private personneService: PersonneService, private router: Router) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      motDePasse: ['', Validators.required],
      type: ['', Validators.required]
    });
  }

  login(): void {
    if (this.loginForm.valid) {
      const { email, motDePasse, type } = this.loginForm.value;
      const loginData = {
        email: email,
        motDePasse: motDePasse,
        type: type
      };
      this.personneService.login(loginData).subscribe(
        response => {
          if (this.mapRoleToType(response.role )!== type) {
            this.errorMessage = `You are a ${response.role}. Please select the correct user type.`;
            return;
          }
          this.personneService.setToken(response.token);
          this.personneService.setCurrentUser({
            email: email,
            role: response.role,
            motDePasse: motDePasse,
            type: type
          });
          this.personneService.redirectToDashboard();
        },
        error => {
          console.error('Login error', error);
          this.errorMessage = 'Invalid credentials. Please try again.';
        }
      );
    }
  }
  private mapRoleToType(role: string): string {
    switch (role) {
      case 'ROLE_USER':
        return 'user';
      case 'ROLE_TECHNICIEN':
        return 'technicien';
      case 'ROLE_ADMIN':
        return 'admin';
      default:
        return '';
    }
  }
}
