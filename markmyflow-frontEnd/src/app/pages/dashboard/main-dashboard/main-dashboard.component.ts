import { Component, OnInit } from '@angular/core';
import{ Chart } from'chart.js'
@Component({
  selector: 'app-main-dashboard',
  templateUrl: './main-dashboard.component.html',
  styleUrls: ['./main-dashboard.component.scss']
})
export class MainDashboardComponent {
 
  date1 = new Date("2023, 1, 1");
  
  date2 = new Date(2023, 1, 1);

  ngOnInit(): void {
    Chart.register
   
  }

  
}
