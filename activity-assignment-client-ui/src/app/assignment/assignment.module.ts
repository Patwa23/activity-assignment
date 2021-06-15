// Angular components imports
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { AssignmentRoutingModule } from './assignment-routing.module';
import { MaterialModule } from '../material.module';
import { CommonModule } from '@angular/common';
import { AssignmentComponent } from './assignment.component';

import { StoreModule } from '@ngrx/store';
import { RecordsService } from './records/records.service';
import { RecordsComponent } from './records/records.component';

@NgModule({
    declarations: [
        AssignmentComponent,
        RecordsComponent
    ],
    imports: [
        ReactiveFormsModule,
        FormsModule,
        CommonModule,
        AngularFontAwesomeModule,
        
        AssignmentRoutingModule,
        MaterialModule
    ],
    providers: [
        RecordsService
    ],
})
export class AssignmentModule { }
