import { Injectable } from "@angular/core";
import { Store } from '@ngrx/store';
import { switchMap,take } from "rxjs/operators";
import { HttpInterceptor, HttpHandler, HttpEvent,HttpRequest, HttpErrorResponse } from "@angular/common/http";
import { Observable } from "rxjs";

import * as fromApp from '../store/app.reducer';
import * as fromAuth from '../auth/store/auth.reducer';

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
    constructor(private store:Store<fromApp.AppState>){}

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>>{
        
        return this.store.select('auth')
            .pipe(take(1),
            switchMap((authState:fromAuth.State) => {
                const copiedReq =req.clone({params: req.params.set('auth',authState.token)});
                return next.handle(copiedReq); 
            }));
        
    }

}