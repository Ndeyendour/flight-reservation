import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HttpClientModule } from "@angular/common/http";
import { AddFlightDetailsComponent } from './add-flight-details/add-flight-details.component';
import { AddPassengersComponent } from './add-passengers/add-passengers.component';
import { AdminHomeComponent } from './admin-home/admin-home.component';
import { AdminLoginComponent } from './admin-login/admin-login.component';
import { ErrorComponent } from './error/error.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { ShowUserBookingsComponent } from './show-user-bookings/show-user-bookings.component';
import { UpdateFlightComponent } from './update-flight/update-flight.component';
import { UpdatePassengerComponent } from './update-passenger/update-passenger.component';
import { UpdateUserDetailsComponent } from './update-user-details/update-user-details.component';
import { UserHomeComponent } from './user-home/user-home.component';
import { UserLoginComponent } from './user-login/user-login.component';
import { UserRegisterComponent } from './user-register/user-register.component';
import { ViewUserDetailsComponent } from './view-user-details/view-user-details.component';
import { ViewAllFlightDetailsComponent } from './view-all-flight-details/view-all-flight-details.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { AboutComponent } from './about/about.component';

@NgModule({
  declarations: [
    AppComponent,
    AddFlightDetailsComponent,
    AddPassengersComponent,
    AdminHomeComponent,
    AdminLoginComponent,
    ErrorComponent,
    PageNotFoundComponent,
    ShowUserBookingsComponent,
    UpdateFlightComponent,
    UpdatePassengerComponent,
    UpdateUserDetailsComponent,
    UserHomeComponent,
    UserLoginComponent,
    UserRegisterComponent,
    ViewUserDetailsComponent,
    ViewAllFlightDetailsComponent,
    HeaderComponent,
    FooterComponent,
    AboutComponent
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
