import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, CanActivateChild, Router } from "@angular/router";
import { Store } from "@ngrx/store";
import { Injectable } from "@angular/core";
import { take, map } from 'rxjs/operators';

import * as fromApp from '../store/app.reducer';
import * as fromAuth from './store/auth.reducer';
import { Observable } from "rxjs";
import { AuthService } from "./auth.service";


@Injectable()
export class AuthGuard implements CanActivate, CanActivateChild {
    constructor(private store: Store<fromApp.AppState>,
        private authService: AuthService,
        private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        return this.checkLogin();

    }

    checkLogin(): boolean {
        if (this.authService.isAuthenticated()) { return true; }

        // Store the attempted URL for redirecting
        this.router.navigate(['/login']);
        return false;
    }

    canActivateChild(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | Promise<boolean> | boolean {
        return this.canActivate(route, state);
    }
}