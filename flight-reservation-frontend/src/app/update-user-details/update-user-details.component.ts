import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-update-user-details',
  templateUrl: './update-user-details.component.html',
  styleUrls: ['./update-user-details.component.css']
})
export class UpdateUserDetailsComponent implements OnInit {

  constructor(
    private formBuilder: FormBuilder,
    private router: Router,
    private userService: UserService
  ) {}

  userId:any;


  /* ---- update form------ */

  updateForm = this.formBuilder.group(
    {
      userName: [null, Validators.required],
      email: [null, Validators.required],
      phone: [null, Validators.required],
      password: [null, [Validators.required, Validators.minLength(8)]],
      confirmPassword: [null, [Validators.required]],
    },
    { validators: this.passwordValidator }
  );




  ngOnInit(): void {
    this.userId = localStorage.getItem("userId");
    if (this.userId == null){
      this.router.navigate(["/error","login to continue"]);
    } else {
      this.userId = parseInt(this.userId);
    }
  }


  /* ------password validator------- */  

  passwordValidator(control: AbstractControl): { [key: string]: any } | null {
    const pass = control.get("password")?.value;
    const cnfm = control.get("confirmPassword")?.value;
    if (pass && cnfm && pass.value !== cnfm.value) {
      return { mismatch: true };
    } else {
      return null;
    }
  }

  submit() {
    this.updateForm.removeControl("confirmPassword");
    let data = this.updateForm.value;
    data.userId = this.userId;
    this.userService.updateUser(data).subscribe(
      data => {
        this.router.navigate(["/userHome"]);
      },
      error => {
        this.router.navigate(["/error","unable to update"]);
      }
    );
  }

  logout(){
    localStorage.removeItem("userId");
    this.router.navigate(["/userLogin"]);
  }
}
