import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { properties } from '../../shared/app.properties';
import { throwError } from 'rxjs';


@Injectable()
export class DashboardService {
  constructor(private httpClient: HttpClient) { }

  // public getTraffic() {
  //   let url = properties.getTraffic;
  //   try {
  //     return this.httpClient.get(url);
  //   } catch (error) {
  //     error => {
  //       return throwError(error);
  //     }
  //   }
  // }
}