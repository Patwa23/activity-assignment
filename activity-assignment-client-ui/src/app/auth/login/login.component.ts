import { Component, OnInit } from '@angular/core';
import { NgForm, FormControl, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { map } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  passwordPattern = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
  usernamePattern = "^[a-zA-Z0-9]+$";
  hide = false;

  constructor(private authService: AuthService,
    private toastr: ToastrService) { }

  public onLogin(form: NgForm) {

    const username = form.value.username;
    const password = form.value.password;
    if (!form.valid) {
      this.toastr.warning("Login", "Invalid Input");
    } else {
      this.authService.loginUser(username, password);
    }

  }

}
