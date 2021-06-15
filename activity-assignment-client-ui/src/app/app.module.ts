// Angular components imports
import { Title, BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
// Maincontainer component imports
import { AppComponent } from './app.component';
// Technical components import
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { AgGridModule } from 'ag-grid-angular';
import { NgxPermissionsModule } from 'ngx-permissions';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { Store, StoreModule } from '@ngrx/store'
import { CoreModule } from './core/core.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { reducers } from './store/app.reducer';
import { OAuthModule } from 'angular-oauth2-oidc';
import { BASE_URL } from './auth/auth.token';

@NgModule({
    declarations: [
        AppComponent,
        PageNotFoundComponent,
    ],
    imports: [
        CoreModule,
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        AngularFontAwesomeModule,
        AgGridModule.withComponents([]),
        NgxPermissionsModule.forRoot(),
        NgbModule.forRoot(),
        StoreModule.forRoot(reducers),
        BrowserAnimationsModule,
        ToastrModule.forRoot({
            timeOut: 5000,
            positionClass: 'toast-bottom-left',
            preventDuplicates: false
        }),
    ],
    providers: [
        Title,
    ],
    bootstrap: [
        AppComponent,
    ]
})
export class AppModule { }
