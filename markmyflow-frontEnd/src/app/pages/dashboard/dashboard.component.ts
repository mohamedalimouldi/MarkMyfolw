import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss']
})
export class DashboardComponent implements OnInit {
  lineChart: any;

  ngOnInit() {
    const labels = this.getMonthLabels(7);
    const data = {
      labels: labels,
      datasets: [{
        label: 'My First Dataset',
        data: [65, 59, 80, 81, 56, 55, 40],
        fill: false,
        borderColor: 'rgb(75, 192, 192)',
        tension: 0.1
      }]
    };

    this.lineChart = new Chart('lineChart', {
      type: 'line',
      data: data
    });
  }

  getMonthLabels(count: number) {
    const months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
    const currentDate = new Date();
    const labels = [];

    for (let i = count - 1; i >= 0; i--) {
      const monthIndex = currentDate.getMonth() - i;
      const wrappedIndex = (monthIndex < 0) ? (12 + monthIndex) : monthIndex;
      labels.push(months[wrappedIndex]);
    }

    return labels;
  }
}
