import { Injectable } from '@angular/core';
import { HttpClientModule, HttpClient, HttpHeaders } from '@angular/common/http';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import * as fromApp from '../store/app.reducer';
import { map } from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';
import { Observable, of as observableOf } from 'rxjs';


@Injectable({
  providedIn: 'root'
})
export class AuthService {
  loggedIn = false;

  constructor(private http: HttpClient,
    private router: Router,
    private store: Store<fromApp.AppState>,
    private toastr: ToastrService) { }

  public loginUser(username: string, password: string) {
    return this.http.get('data/auth.json', )
      .subscribe(data => {
        if (data['username'] === username && data['password'] === password) {
          this.loggedIn = true;
          this.router.navigate(['/']);
          this.toastr.info("Authentication", "Successfully Authentication");
        } else {
          this.loggedIn = false;
          this.toastr.error("Authentication", "Invalid Username & Password");
        }
      },
        error => {
          this.toastr.error("Authentication", "Error during authentication");
        }

      )
  }

  isAuthenticated() {
    return this.loggedIn;
  }

  logout() {
    this.loggedIn = false;
    this.toastr.info("Authentication", "Logout Successfully");
  }



}
