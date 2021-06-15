// Angular components imports
import { NgModule } from '@angular/core';

import { MaincontainerComponent } from './maincontainer/maincontainer.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';
import { AppRoutingModule } from '../app-routing.module';
import { CommonModule } from '@angular/common';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ChartModule } from 'angular-highcharts';
import { ToastrService } from 'ngx-toastr';
import { AuthGuard } from '../auth/auth-guard.service';
import { ErrorDialogService } from '../error-dialogue/errordialog.service';
import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { HttpConfigInterceptor } from '../interceptor/httpconfig.interceptor';
import { ErrorDialogComponent } from '../error-dialogue/errordialog.component';
import { DashboardService } from './dashboard/dashboard.service';
import { AuthModule } from '../auth/auth.module';

@NgModule({
    declarations: [
        MaincontainerComponent,
        HeaderComponent,
        FooterComponent,
        DashboardComponent,
        ErrorDialogComponent
    ],
    imports: [
        AuthModule,
        AppRoutingModule,
        CommonModule,
        ChartModule,
    ],
    exports: [
        AuthModule,
        AppRoutingModule,
        HeaderComponent,
        FooterComponent,
        MaincontainerComponent,
        DashboardComponent,
    ],
    providers: [
        ErrorDialogService,
        DashboardService,
        AuthGuard,
        { provide: HTTP_INTERCEPTORS, useClass: HttpConfigInterceptor, multi: true }
    ], entryComponents: [ErrorDialogComponent],

})
export class CoreModule { }
