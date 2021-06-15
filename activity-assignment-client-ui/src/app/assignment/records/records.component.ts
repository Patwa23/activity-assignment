import { Component, OnInit, ViewChild, ElementRef } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { RecordsService } from './records.service';
import { InvalidRecords } from './records.model';

import { startWith, map, catchError } from 'rxjs/operators';
import { Observable, throwError, of } from 'rxjs';
import { FormControl, Validators, FormGroup, FormBuilder } from '@angular/forms';
import { HttpEventType, HttpErrorResponse, HttpClient } from '@angular/common/http';
import { Activity } from './activity.model';


@Component({
  selector: 'app-records',
  templateUrl: './records.component.html',
  styleUrls: ['./records.component.css']
})
export class RecordsComponent implements OnInit {

  displayedColumns = [];
  invalidRecords: any[];
  dataSource: MatTableDataSource<any>;
  isHide = true;
  isPanelExpanded = true;
  code = '';
  hideValidation = false;
  activities: Activity[];
  sourceRecordCtrl = new FormControl('', Validators.required);
  filteredSourceRecord: Observable<InvalidRecords[]>;
  @ViewChild(MatPaginator, { static: false }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: false }) sort: MatSort;

  @ViewChild("fileUpload", { static: false }) fileUpload: ElementRef; files = [];

  // form: FormGroup;
  // file: File;
  // uploadResponse = { status: '', message: '', filePath: '' };

  constructor(private recordsService: RecordsService, private formBuilder: FormBuilder, private httpClient: HttpClient) { }

  ngOnInit() {
    //----Code
    this.activities = [] //TABLE DATASOURCE
    // this.createForm();
  }

  // Instantiate an AbstractControl from a user specified configuration
  // createForm() {
  //   this.form = this.formBuilder.group({
  //     avatar: ['']
  //   });
  // }

  // onFileChange(event) {
  //   if (event.target.files.length > 0) {
  //     const file = event.target.files[0];
  //     this.form.get('avatar').setValue(file);
  //   }
  // }

  // onSubmit() {
  //   const formData = new FormData();
  //   formData.append('file', this.form.get('avatar').value);

  //   this.recordsService.upload1(formData).subscribe(
  //     (res) => console.log(res),
  //     (err) => console.log(err)
  //   );
  // }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim().toLowerCase(); // Remove whitespace
    this.dataSource.filter = filterValue;

    this.dataSource.filterPredicate =
      (data: any, filterValue: string) => {
        const matchFilter = [];
        const filterArray = filterValue.split(',');
        const columns = [data.code];
        //  const columns = (<any>Object).values(data);

        //Main loop
        filterArray.forEach(filterValue => {
          const customFilter = [];
          columns.forEach(column => customFilter.push(column.toString().toLowerCase().includes(filterValue)));
          matchFilter.push(customFilter.some(Boolean)); // OR
        });
        return matchFilter.every(Boolean); // AND
      }
  }



  onClick() {
    const fileUpload = this.fileUpload.nativeElement; fileUpload.onchange = () => {
      for (let index = 0; index < fileUpload.files.length; index++) {
        const file = fileUpload.files[index];
        this.files.push({ data: file, inProgress: false, progress: 0 });
      }
      this.uploadFiles();
    };
    fileUpload.click();
  }

  private uploadFiles() {
    this.fileUpload.nativeElement.value = '';
    this.files.forEach(file => {
      this.uploadFile(file);
    });
  }

  uploadFile(file) {
    const formData = new FormData();
    formData.append('file', file.data);
    file.inProgress = true;
    this.recordsService.upload(formData).pipe(
      map(event => {
        switch (event.type) {
          case HttpEventType.UploadProgress:
            file.progress = Math.round(event.loaded * 100 / event.total);
            break;
          case HttpEventType.Response:
            return event;
        }
      }),
      catchError((error: HttpErrorResponse) => {
        file.inProgress = false;
        return of(`${file.data.name} upload failed.`);
      })).subscribe((event: any) => {
        if (typeof (event) === 'object') {
          console.log(event.body);
        }
      });
  }

  //File upload function
  changeListener(files: FileList) {
    console.log(files);
    if (files && files.length > 0) {
      let file: File = files.item(0);
      console.log(file.name);
      console.log(file.size);
      console.log(file.type);

      this.recordsService.upload(file).pipe(
        map(event => {
          switch (event.type) {
            case HttpEventType.UploadProgress:
              // file. = Math.round(event.loaded * 100 / event.total);
              break;
            case HttpEventType.Response:
              return event;
          }
        }),
        catchError((error: HttpErrorResponse) => {
          //  file.inProgress = false;
          return of(`${file.name} upload failed.`);
        })).subscribe((event: any) => {
          if (typeof (event) === 'object') {
            console.log(event.body);
          }
        });
    }
  }

}
