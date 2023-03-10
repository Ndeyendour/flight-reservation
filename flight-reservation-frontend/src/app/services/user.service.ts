import { HttpClient, HttpErrorResponse, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private http: HttpClient) {}


  /*
  *  **********change base url according to your server**************
  */

  private baseUrl = "http://localhost:8080/user";

  httpHeaders = new HttpHeaders({
    "Content-Type": "application/json",
  });

  options = { headers: this.httpHeaders };

  /* ---method to add user---- */

  addUser(user: any) {
    console.log(JSON.stringify(user));
    return this.http.post(`${this.baseUrl}/addUser`,JSON.stringify(user),this.options).pipe(
      catchError(this.errorHandler)
    );
  
  }

  /* -----method to add booking---- */

  addBooking(flightNumber: any,userId: any,passengers: any){
    return this.http.post(`${this.baseUrl}/addBooking/${userId}/${flightNumber}`,JSON.stringify(passengers),this.options)
    .pipe(catchError(this.errorHandler));
  }

  /* ---method to get bookings */

  getBookingByUser(id: string){
    return this.http.get(this.baseUrl + "/getBookingByUser/" + id).pipe( catchError(this.errorHandler));
  }

  /* ---method to delete booking--- */

  deleteBooking(bookingId: string,userId: string){
    return this.http.delete(this.baseUrl + "/deleteBooking/" + bookingId + "/" + userId).pipe(catchError(this.errorHandler));
  }

  /* ----method to update user----- */
  
  updateUser(user: any){
    return this.http.post(this.baseUrl + "/updateUser",JSON.stringify(user),this.options).pipe(catchError(this.errorHandler));
  }

  /* -------method to get user------- */

  getUser(id: any){
    return this.http.get(this.baseUrl + "/getUser/" + id).pipe(catchError(this.errorHandler));
  }

  /* -----method to get flight----------- */

  getFlightByNumber(flightNumber: any){
    return this.http.get(`${this.baseUrl}/getFlightByNumber/${flightNumber}`).pipe(catchError(this.errorHandler));
  }

  updatePassenger(passenger: any){
    return this.http.post(`${this.baseUrl}/updatePassenger`,JSON.stringify(passenger),this.options).pipe(catchError(this.errorHandler));
  }






  userLogin(user: any):Observable<any> {
    console.log(JSON.stringify(user));
    return this.http
      .post<any>(this.baseUrl + "/userLogin", JSON.stringify(user), this.options)
      .pipe(catchError(this.errorHandler));
  }



  searchFlight(from: any,to: any,date: any): Observable<any>{
    return this.http.get<any>(`${this.baseUrl}/findFlight/${to}/${from}/${date}`).pipe(
      catchError(this.errorHandler)
    );
  }


  errorHandler(error:HttpErrorResponse) {
    let errorMessage = "";
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError(errorMessage);
  }
}
