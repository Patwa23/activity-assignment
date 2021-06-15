import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ErrorDialogComponent } from './errordialog.component';

@Injectable()
export class ErrorDialogService {
   data:any;
   
    constructor(public dialog: MatDialog) { }
    public openDialog(data): void {
        const dialogRef = this.dialog.open(ErrorDialogComponent, {
            width: '500px',
            data:data
        });

        dialogRef.afterClosed().subscribe(result => {
            let animal;
            animal = result;
        });
    }
}