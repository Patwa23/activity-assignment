import { Component, OnInit } from '@angular/core';
import { Title } from '@angular/platform-browser';

import { NgxPermissionsService } from 'ngx-permissions';
import { Router } from '@angular/router';
import { authConfig } from './auth/auth.config';
import { delay } from 'rxjs/operators';


@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
    public constructor(private titleService: Title,
        private permissionsService: NgxPermissionsService,
        public router: Router) {
    }

    ngOnInit(): void {
        const perm = ["ADMIN", "GUEST"];
        this.permissionsService.loadPermissions(perm);
    }

    public setTitle(newTitle: string) {
        this.titleService.setTitle(newTitle);
    }

}
