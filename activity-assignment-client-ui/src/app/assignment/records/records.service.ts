import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpEventType } from '@angular/common/http';

import { throwError } from "rxjs";
import { take, catchError, map } from "rxjs/operators";
import { properties } from '../../shared/app.properties';

@Injectable()
export class RecordsService {

  constructor(private httpClient: HttpClient) {
  }

  public upload(formData) {
    // let input = new FormData();
    // input.append('file', formData);
    const headers = this.createHeaders();
    return this.httpClient.post<any[]>(properties.uploadActivity, formData, {
      headers: headers,
      reportProgress: true,
      observe: 'events'
    });
  }

  private createHeaders(): HttpHeaders {
    return new HttpHeaders({
      'Content-Type': 'multipart/form-data'
    });
  }

  public upload1(data) {
    // Add your values in here
    //   let input = new FormData();
    // input.append('id', data.id);
    // input.append('file', data.invoiceFile);
    //let uploadURL = `${this.SERVER_URL}/auth/${userId}/avatar`;

    return this.httpClient.post<any>(properties.uploadActivity, data, {
      reportProgress: true,
      observe: 'events'
    }).pipe(map((event) => {

      switch (event.type) {

        case HttpEventType.UploadProgress:
          const progress = Math.round(100 * event.loaded / event.total);
          return { status: 'progress', message: progress };

        case HttpEventType.Response:
          return event.body;
        default:
          return `Unhandled event: ${event.type}`;
      }
    })
    );
  }

  public getRecords() {
    return this.httpClient.get<any[]>(properties.getActivities, { responseType: 'json' });
  }

}