import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { PersonneService } from '../Service/PersonneService';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  errorMessage: string = '';
  showLoginForm = false;
  showHeart = false;

  constructor(private fb: FormBuilder, private personneService: PersonneService) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      motDePasse: ['', Validators.required]
    });
  }

  login(): void {
    if (this.loginForm.valid) {
      const { email, motDePasse } = this.loginForm.value;
      const loginData = { email, motDePasse };

      this.personneService.login(loginData).subscribe(
        response => {
          this.personneService.setToken(response.token);
          this.personneService.setCurrentUser({
            email: email,
            role: response.role,
            motDePasse: motDePasse,
          });
          this.personneService.redirectToDashboard();
        },
        error => {
          console.error('Login error', error);
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
