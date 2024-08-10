import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { TechnicianDashboardComponent } from './technician-dashboard/technician-dashboard.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './register/registration.component';
import {EquipmentListComponent} from "./EquipmentC/equipment-list/equipment-list.component";
import {EquipmentFormComponent} from "./EquipmentC/equipment-form/equipment-form.component";
import {PanneFormComponent} from "./PanneC/panne-form/panne-form.component";
import {PanneListComponent} from "./PanneC/panne-list/panne-list.component";
import {PanneDetailComponent} from "./PanneC/panne-detail/panne-detail.component";
import {PanneSearchComponent} from "./PanneC/panne-search/panne-search.component";
import {PanneEquipmentListComponent} from "./PanneC/panne-equipment/panne-equipment-list.component";

const routes: Routes = [
  { path: 'user-dashboard', component: UserDashboardComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'technician-dashboard', component: TechnicianDashboardComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' },
  { path: 'equipments', component: EquipmentListComponent },
  { path: 'equipments/new', component: EquipmentFormComponent },
  { path: 'equipments/edit/:id', component: EquipmentFormComponent },
  { path: '', redirectTo: '/equipments', pathMatch: 'full' },
  { path: 'pannes', component: PanneListComponent },
  { path: 'pannes/new', component: PanneFormComponent },
  { path: 'pannes/edit/:id', component: PanneFormComponent },
  { path: 'pannes/detail/:id', component: PanneDetailComponent },
  { path: 'pannes/search', component: PanneSearchComponent },
  { path: 'pannes/:id/details', component: PanneDetailComponent },
  { path: 'panne-equipment', component: PanneEquipmentListComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
