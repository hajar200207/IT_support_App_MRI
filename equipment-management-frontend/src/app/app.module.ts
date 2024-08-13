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
import { HomeComponent } from './home/home.component';
import { ServicesComponent } from './services/services.component';
import { AboutUsComponent } from './about-us/about-us.component';
import { ContactComponent } from './contact/contact.component';
import { FooterComponent } from './footer/footer.component';
import { AdminAccountsComponent } from './admin-accounts/admin-accounts.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AdminUsersComponent } from './admin-users/admin-users.component';
import {MatTableModule} from "@angular/material/table";
import { EditUserComponent } from './edit-user/edit-user.component';
import {MatInputModule} from "@angular/material/input";
import {MatSelectModule} from "@angular/material/select";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import {MatDialogModule} from "@angular/material/dialog";

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
    TechnicienTicketsComponent,
    HomeComponent,
    ServicesComponent,
    AboutUsComponent,
    ContactComponent,
    FooterComponent,
    AdminAccountsComponent,
    AdminUsersComponent,
    EditUserComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MatTableModule,
    MatInputModule,
    MatSelectModule,
    MatSnackBarModule,
    MatDialogModule,

  ],
  providers: [
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
