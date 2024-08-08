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
        }
      );
    }
  }
}
