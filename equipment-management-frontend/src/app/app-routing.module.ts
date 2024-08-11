import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { TechnicianDashboardComponent } from './technician-dashboard/technician-dashboard.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './register/registration.component';
import { EquipmentListComponent } from "./EquipmentC/equipment-list/equipment-list.component";
import { EquipmentFormComponent } from "./EquipmentC/equipment-form/equipment-form.component";
import { PanneFormComponent } from "./PanneC/panne-form/panne-form.component";
import { PanneListComponent } from "./PanneC/panne-list/panne-list.component";
import { PanneDetailComponent } from "./PanneC/panne-detail/panne-detail.component";
import { PanneSearchComponent } from "./PanneC/panne-search/panne-search.component";
import { PanneEquipmentListComponent } from "./PanneC/panne-equipment/panne-equipment-list.component";
import { CreateTicketComponent } from "./TicketC/create-ticket/create-ticket.component";
import { AdminTicketsComponent } from "./TicketC/admin-tickets/admin-tickets.component";
import { TechnicienTicketsComponent } from "./TicketC/technicien-tickets/technicien-tickets.component";
import { TicketListComponent } from "./TicketC/ticket-list/ticket-list.component";

const routes: Routes = [
  { path: 'user-dashboard', component: UserDashboardComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent },
  { path: 'technician-dashboard', component: TechnicianDashboardComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'equipments', component: EquipmentListComponent },
  { path: 'equipments/new', component: EquipmentFormComponent },
  { path: 'equipments/edit/:id', component: EquipmentFormComponent },
  { path: 'pannes', component: PanneListComponent },
  { path: 'pannes/new', component: PanneFormComponent },
  { path: 'pannes/edit/:id', component: PanneFormComponent },
  { path: 'pannes/detail/:id', component: PanneDetailComponent },
  { path: 'pannes/search', component: PanneSearchComponent },
  { path: 'panne-equipment', component: PanneEquipmentListComponent },
  { path: 'create-ticket', component: CreateTicketComponent },
  { path: 'admin-tickets', component: AdminTicketsComponent },
  { path: 'technicien-tickets', component: TechnicienTicketsComponent },
  { path: 'tickets', component: TicketListComponent },
  { path: '', redirectTo: '/login', pathMatch: 'full' } // Rediriger vers /login par défaut
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
