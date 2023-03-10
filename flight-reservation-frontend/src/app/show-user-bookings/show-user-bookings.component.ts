import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-show-user-bookings',
  templateUrl: './show-user-bookings.component.html',
  styleUrls: ['./show-user-bookings.component.css']
})
export class ShowUserBookingsComponent implements OnInit {
  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) {}

  userId!: any;
  bookings!: any;
  found!: boolean;
  notFound!: boolean;
  passengers: any;

  flights!: any;

  ngOnInit(): void {
    this.userId = localStorage.getItem("userId");
    console.log(this.userId);
    if (this.userId == null) {
      this.router.navigate(["/error", "not logged in, login to continue"]);
    } else {
      this.userService.getBookingByUser(this.userId).subscribe({
       next: (data) => {
          console.log(data);
          this.bookings = data;
          if (this.bookings.length > 0) {
            this.found = true;
            this.notFound = false;
          } else {
            this.found = false;
            this.notFound = true;
          }
        },
       error: (error) => {
          this.router.navigate(["/error", "not logged in, login to continue"]);
        }
       } );
    }
  }


  /* ------method to delete booking-------- */


  delete(bookingId: string) {
    if (confirm("are you sure you want to delete")) {
      this.passengers = null;
      this.userService.deleteBooking(bookingId, this.userId).subscribe({
       next: (data) => {
          console.log("deleted");
          this.userService.getBookingByUser(this.userId).subscribe({
            next:(data) => {
              console.log(data);
              this.bookings = data;
              if (this.bookings.length > 0) {
                this.found = true;
                this.notFound = false;
              } else {
                this.found = false;
                this.notFound = true;
              }
            },
            error:(error) => {
              this.router.navigate([
                "/error",
                "not logged in, login to continue",
              ]);
            }
           } );
        },
      error:  (error) => {
          this.router.navigate(["/error", "cannot delete"]);
        }
  });
    }
  }


  /* ------method to get flight detais---------- */

  getFlightDetails(flightNumber: any){
    this.passengers = null;
    this.userService.getFlightByNumber(flightNumber).subscribe({
     next: data => {
        this.flights = data;
      },
      error: error => {
        this.router.navigate(["/error","no flight found or flight is deleted"]);
      }
  });
  }

  updatePassenger(passengerId: any){
    this.router.navigate(["/updatePassenger",passengerId]);
  }



  getPassengers(booking: { passengers: any; }){
    this.flights = null;
    this.passengers = booking.passengers;
  }

  logout() {
    localStorage.removeItem("userId");
    this.router.navigate(["/userLogin"]);
  }
}

