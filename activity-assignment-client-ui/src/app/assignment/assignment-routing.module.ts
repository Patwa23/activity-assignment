import { Routes, RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";

import { AssignmentComponent } from "./assignment.component";

import { AuthGuard } from "../auth/auth-guard.service";
import { RecordsComponent } from "./records/records.component";


const assignmentRoutes: Routes = [
    { path: '', component: AssignmentComponent },
    { path: 'records', component: RecordsComponent },

];


@NgModule({
    imports: [RouterModule.forChild(assignmentRoutes)],
    exports: [RouterModule]
})

export class AssignmentRoutingModule {
}