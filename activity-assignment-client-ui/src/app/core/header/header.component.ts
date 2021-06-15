import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';

import * as fromApp from '../../store/app.reducer';
import * as fromAuth from '../../auth/store/auth.reducer';
import { Observable } from 'rxjs';
import { AuthService } from '../../auth/auth.service';

declare var $: any;

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.css']
})

export class HeaderComponent implements OnInit {
    authState: Observable<fromAuth.State>;


    constructor(public router: Router,
        public store: Store<fromApp.AppState>,
        public authService: AuthService) {
    }

    ngOnInit() {
        this.authState = this.store.select('auth');
    }

    public onLogout() {
        this.authService.logout();
    }

}
