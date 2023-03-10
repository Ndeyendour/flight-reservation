import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError } from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  constructor(private http: HttpClient) {}

  /*
   *  **********change base url according to your server**************
   */

  private baseUrl = "http://localhost:8080/admin";

  httpHeaders = new HttpHeaders({
    "Content-Type": "application/json",
  });

  options = { headers: this.httpHeaders };

  adminLogin(admin: any) {
    return this.http
      .post(
        `${this.baseUrl}/adminLogin`,
        JSON.stringify(admin),
        this.options
      )
      .pipe(catchError(this.errorHandler));
  }

  getAdminDetails(id: any) {
    return this.http
      .get(`${this.baseUrl}/getAdmin/${id}`)
      .pipe(catchError(this.errorHandler));
  }

  addFlight(flight: any): Observable<Object> {
    return this.http.post(`${this.baseUrl}/addFlightDetails`, JSON.stringify(flight),this.options).pipe(catchError(this.errorHandler));
  }

  modifyFlight(flight: any): Observable<Object> {
    return this.http.post(`${this.baseUrl}/updateFlightDetails`, JSON.stringify(flight),this.options).pipe(catchError(this.errorHandler));
  }

  removeFlight(flightNo: any): Observable<any> {
    return this.http.delete(`${this.baseUrl}/deleteFlightDetails/${flightNo}`).pipe(catchError(this.errorHandler));
  }

  viewAllFlight(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getAllFlightDetails`).pipe(catchError(this.errorHandler));
  }

  errorHandler(error: { error: { message: any; }; status: any; message: any; }) {
    let errorMessage = "";
    if (error.error instanceof ErrorEvent) {
      // client-side error
      errorMessage = `Error: ${error.error.message}`;
    } else {
      // server-side error
      errorMessage = `Error Code: ${error.status}\nMessage: ${error.message}`;
    }
    console.log(errorMessage);
    return throwError (errorMessage);
  }
}

