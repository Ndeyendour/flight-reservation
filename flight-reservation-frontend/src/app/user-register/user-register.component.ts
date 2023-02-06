import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-user-register',
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService
  ) {}

  private userData!: any;



  /* -------register form for user------ */

  userRegisterForm = this.formBuilder.group(
    {
      userName: [null, Validators.required],
      email: [null, Validators.required],
      phone: [null,Validators.required],
      
      password: [null, [Validators.required, Validators.minLength(8)]],
      confirmPassword: [null, [Validators.required]],
    },
    { validators: this.passwordValidator }
  );

  /*
   * ------- function to check password and confirm password field are same---------
   */

  passwordValidator(control: AbstractControl): { [key: string]: any } | null {
    const pass = control.get('password')?.value;
    const cnfm = control.get('confirmPassword')?.value;
    
    if (pass && cnfm && pass.value !== cnfm.value) {
      return { mismatch: true };
    } else {
      return null;
    }
  }

  
  /* ---------function to register-------- */

  submit(){
    this.userRegisterForm.removeControl("confirmPassword");
    this.userService.addUser(this.userRegisterForm.value).subscribe({
      next:data => {
        this.userData = data;
        localStorage.setItem("userId",this.userData.userId);
        this.router.navigate(["/userHome"]);
      },
      error:error => {
        this.router.navigate(["/error","invalid data provided or unable to connect"]);
      }
        });
  }

  ngOnInit(): void {}
}



