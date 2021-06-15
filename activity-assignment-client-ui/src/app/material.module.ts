import { NgModule } from '@angular/core';
import { MatRippleModule, MatInputModule, MatFormFieldModule, MatSortModule, MatPaginatorModule, MatCheckboxModule, MatTableModule, MatCardModule, MatIconModule, MatToolbarModule, MatMenuModule, MatButtonModule, MatSelectModule, MatSidenavModule, MatSidenav, MatListModule, MatGridListModule, MatExpansionModule, MatDatepickerModule, MatNativeDateModule, MatProgressBarModule, MatProgressSpinnerModule, MatStepperModule, MatDialogModule, MatAutocompleteModule, MatSliderModule, MatSlideToggleModule } from '@angular/material';


const modules = [
  MatButtonModule,
  MatMenuModule,
  MatToolbarModule,
  MatIconModule,
  MatCardModule,
  MatTableModule,
  MatCheckboxModule,
  MatPaginatorModule,
  MatSortModule,
  MatFormFieldModule,
  MatInputModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatListModule,
  MatGridListModule,
  MatExpansionModule,
  MatNativeDateModule,
  MatDatepickerModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatStepperModule,
  MatDialogModule,
  MatAutocompleteModule,
  MatSliderModule,
  MatSlideToggleModule

];
@NgModule({
  imports: [...modules],
  exports: [...modules]
  ,
})
export class MaterialModule { };