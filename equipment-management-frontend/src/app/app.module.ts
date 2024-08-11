import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import { UserDashboardComponent } from './user-dashboard/user-dashboard.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { TechnicianDashboardComponent } from './technician-dashboard/technician-dashboard.component';
import {AuthInterceptor} from "./interceptors/auth.interceptor";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './register/registration.component';
import {AppRoutingModule} from "./app-routing.module";
import { EquipmentListComponent } from './EquipmentC/equipment-list/equipment-list.component';
import { EquipmentFormComponent } from './EquipmentC/equipment-form/equipment-form.component';
import { PanneListComponent } from './PanneC/panne-list/panne-list.component';
import { PanneFormComponent } from './PanneC/panne-form/panne-form.component';
import { PanneSearchComponent } from './PanneC/panne-search/panne-search.component';
import { PanneDetailComponent } from './PanneC/panne-detail/panne-detail.component';
import { PanneEquipmentListComponent } from './PanneC/panne-equipment/panne-equipment-list.component';
import { TicketListComponent } from './TicketC/ticket-list/ticket-list.component';
import { CreateTicketComponent } from './TicketC/create-ticket/create-ticket.component';
import { AdminTicketsComponent } from './TicketC/admin-tickets/admin-tickets.component';
import { TechnicienTicketsComponent } from './TicketC/technicien-tickets/technicien-tickets.component';

@NgModule({
  declarations: [
    AppComponent,
    UserDashboardComponent,
    AdminDashboardComponent,
    TechnicianDashboardComponent,
    LoginComponent,
    RegistrationComponent,
    EquipmentListComponent,
    EquipmentFormComponent,
    PanneListComponent,
    PanneFormComponent,
    PanneSearchComponent,
    PanneDetailComponent,
    PanneEquipmentListComponent,
    TicketListComponent,
    CreateTicketComponent,
    AdminTicketsComponent,
    TechnicienTicketsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
