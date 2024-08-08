import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {PersonneService} from "../Service/PersonneService";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  registreForm!: FormGroup;
  type:any;
  Roles:string[]=['user','technicien']


  constructor(private fb: FormBuilder,private srv:PersonneService,private route:Router) {}

  ngOnInit(): void {
    this.registreForm = this.fb.group({
      role: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      motDePasse: ['', Validators.required],
      fonction: [''],
      specialite: [''],
      type:[''],
      username:['',Validators.required]

    });

    // Watch for changes in the role to show/hide additional fields
    this.registreForm.get('role')?.valueChanges.subscribe(value => {
      if (value === 'user') {
        this.registreForm.get('fonction')?.setValidators(Validators.required);
        this.registreForm.get('specialite')?.clearValidators();
      } else if (value === 'technicien') {
        this.registreForm.get('specialite')?.setValidators(Validators.required);
        this.registreForm.get('fonction')?.clearValidators();
      } else {
        this.registreForm.get('fonction')?.clearValidators();
        this.registreForm.get('specialite')?.clearValidators();
      }
      this.registreForm.get('fonction')?.updateValueAndValidity();
      this.registreForm.get('specialite')?.updateValueAndValidity();
    }
    );
  }

  register(): void {
    if (this.registreForm.valid) {

      const formData = this.registreForm.value;
      formData.type=formData.role

      this.srv.register(formData).subscribe(
        ()=>{
          this.route.navigateByUrl("login")

        }
      )

      console.log(formData);
    }
  }
}
