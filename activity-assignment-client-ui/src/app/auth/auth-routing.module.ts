import { Routes, RouterModule, PreloadAllModules } from "@angular/router";
import { NgModule } from "@angular/core";

import { ProfileComponent } from "./profile/profile.component";
import { LoginComponent } from "./login/login.component";
import { AuthGuard } from "./auth-guard.service";


const authRoutes: Routes = [
    { path: 'login', component: LoginComponent, data: { preload: true } },
    { path: 'profile', component: ProfileComponent, canActivate: [AuthGuard] },
];


@NgModule({
    imports: [RouterModule.forChild(authRoutes)],
    exports: [RouterModule]
})

export class AuthRoutingModule {
}