import { Component, OnInit } from '@angular/core';
import { Chart } from 'angular-highcharts';
import { DashboardService } from './dashboard.service';

import { throwError } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  user = 'XYZ';
  totalRequest: number;
  totalOkResponse: number;
  total4xxResponse: number;
  total5xxResponse: number;
  AvgResponseTime = [];
  MinResponseTime = [];
  MaxResponseTime = [];
  chart1: Chart;
  chart2: Chart;

  constructor(private dashboardService: DashboardService) { }

  ngOnInit() {
    this.chart1 = new Chart({
      chart: {
        type: 'line',
        backgroundColor: null
      },
      title: {
        text: ''
      },
      credits: {
        enabled: false
      },
      colors: ['#2f7ed8', '#0d233a', '#136ea0'],
      series: [{
        name: 'Average Response Time',
        data: this.AvgResponseTime
      },
      {
        name: 'Minimum Response Time',
        data: this.MinResponseTime
      },
      {
        name: 'Maximum Response Time',
        data: this.MaxResponseTime
      }]
    });

    this.chart2 = new Chart({
      chart: {
        type: 'column',
        backgroundColor: null
      },
      title: {
        text: '',
        verticalAlign: "bottom",
        align: "left"
      },
      credits: {
        enabled: false
      },
      series: [{
        data: [],
        name: 'Request/Response'

      }],
      xAxis: {
        categories: [
          'Total Request',
          'OK Response ',
          '4xx Response',
          '5xx Response',
        ],
        crosshair: true
      },
      yAxis: {
        min: 0,
        title: {
          text: 'Total Count'
        }
      },
    });

    // this.dashboardService.getTraffic()
    //   .subscribe(
    //     data => {
    //       this.totalRequest = data["totalRequest"];
    //       this.totalOkResponse = data["totalOkResponse"];
    //       this.total4xxResponse = data["total4xxResponse"];
    //       this.total5xxResponse = data["total5xxResponse"];
    //       this.chart2.addPoint(data["totalRequest"]);
    //       this.chart2.addPoint(data["totalOkResponse"]);
    //       this.chart2.addPoint(data["total4xxResponse"]);
    //       this.chart2.addPoint(data["total5xxResponse"]);
    //       this.chart1.addPoint(data["avgResponseTime"], 1);
    //       this.chart1.addPoint(data["minResponseTime"], 0);
    //       this.chart1.addPoint(data["maxResponseTime"], 2);

    //     }, error => {
    //       return throwError(error);
    //     });
  }
}
