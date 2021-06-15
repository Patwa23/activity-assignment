import { Routes, RouterModule, PreloadAllModules } from "@angular/router";
import { NgModule } from "@angular/core";
import { PageNotFoundComponent } from "./page-not-found/page-not-found.component";
import { DashboardComponent } from "./core/dashboard/dashboard.component";
import { AuthGuard } from "./auth/auth-guard.service";

const appRoutes: Routes = [
    { path: 'dashboard', component: DashboardComponent },
    { path: 'assignment', loadChildren: () => import('./assignment/assignment.module').then(m => m.AssignmentModule) },
    { path: 'login', loadChildren: () => import('./auth/auth.module').then(m => m.AuthModule), canActivate: [AuthGuard] },
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'not-found', component: PageNotFoundComponent },
    { path: '**', redirectTo: 'not-found' }
];


@NgModule({
    imports: [RouterModule.forRoot(appRoutes, {
        enableTracing: false,
        preloadingStrategy: PreloadAllModules
    })],
    exports: [RouterModule]
})

export class AppRoutingModule {
}