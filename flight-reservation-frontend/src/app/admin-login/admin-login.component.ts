import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AdminService } from '../services/admin.service';

@Component({
  selector: 'app-admin-login',
  templateUrl: './admin-login.component.html',
  styleUrls: ['./admin-login.component.css']
})
export class AdminLoginComponent implements OnInit {

  constructor(
    private router: Router,
    private adminService: AdminService,
    private formBuilder: FormBuilder
  ) {}

  failure = { value: false };
  adminData!: any;



    
  loginForm = this.formBuilder.group({

    adminId : [null,Validators.required],
    password : [null,[Validators.required,Validators.minLength(8)]]

  });

  ngOnInit(): void {}




  submit(){
    this.adminService.adminLogin(this.loginForm.value).subscribe({
     next: (data) => {
        this.adminData = data;
        this.failure.value = false;
        localStorage.setItem("adminId",this.adminData.adminId);
        this.router.navigate(["/adminHome"]);
      },
     error: (error) => {
        this.failure.value = true;
        this.loginForm.reset();
      }
     } );
  }
}
