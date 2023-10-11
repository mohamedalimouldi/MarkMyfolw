import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { SubscriptionService } from 'src/app/core/services/subscription.service';

import { Chart,LinearScale } from 'chart.js/auto';
import { CompanyService } from 'src/app/core/services/company.service';
import { Company } from 'src/app/core/models/company';
@Component({
  selector: 'app-static',
  templateUrl: './static.component.html',
  styleUrls: ['./static.component.scss']
})
export class StaticComponent implements OnInit {
  list: any[];
  dateDebut: Date 
  dateFin: Date;
  abonPrixTotal: number;
  listPrix: any[]
  year : string="2023"
  @ViewChild('myChart') myChartCanvas: ElementRef;
  @ViewChild("mychart2") mychartCan2: ElementRef;
  @ViewChild("barChart") barChartRef: ElementRef;
  list2 : any[]
  projets : any[]
  companies: Company[]
  
  selectedCompanyId: number = 31;
  constructor(private subService: SubscriptionService, private companyServe: CompanyService) {}
  ngOnInit(): void {
    Chart.register(LinearScale);
    this.getRevenues()
    this.getProject()
    this.companyServe.getAllCompany().subscribe(data =>{
      this.companies=data

    })
  }
  onCompanyChange(event: any): void {
    const selectedCompanyId = event.target.value;
    this.selectedCompanyId = Number(selectedCompanyId);
    this.getProject();
  }

  public getProject() {
    if (!this.selectedCompanyId) {
      return;
    }
    this.companyServe.getProject(this.selectedCompanyId).subscribe((data: any) => {
      this.projets = data.projectWorkflow;
      console.log(this.projets);
      const ctx = (this.barChartRef.nativeElement as HTMLCanvasElement).getContext('2d');
      const chartData = {
        labels: this.projets.map(item => `${item.name} (${item.debute} - ${item.deadline})`),
        datasets: [{
          axis: 'y',
          label: 'Days: ',
          data: this.projets.map(item =>
            this.calculateWorkingDays(item.debute, item.deadline)
          ),
          backgroundColor: this.projets.map(() => this.getRandomColor()), // Generate random colors
          borderWidth: 1,
          inflateAmount: 1
        }]
      };
      const chartOptions = {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true
          }
        }
      };
      const myChart = Chart.getChart(ctx);
      if (myChart) {
        myChart.destroy();
      }
  
      const newChart = new Chart(ctx, {
        type: 'bar',
        data: chartData,
        options: {
          indexAxis: 'y',
        }
      });
    });
  }
  
  // Generate random color
   getRandomColor() {
    const letters = '0123456789ABCDEF';
    let color = '#';
    for (let i = 0; i < 6; i++) {
      color += letters[Math.floor(Math.random() * 16)];
    }
    return color;
  }
  
  
  public getRevenues() {
    this.subService.getrevenuedata(this.year).subscribe(data => {
      this.list2 = data;
      console.log(this.list2);
      const ctx = (this.mychartCan2.nativeElement as HTMLCanvasElement).getContext('2d');
      const labels = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
      
      const monthPriceMap = {};
  
      labels.forEach(month => {
        monthPriceMap[month] = 0;
      });
  
    
      this.list2.forEach(item => {
        const month = item.month;
        const price = item.price;
        if (monthPriceMap.hasOwnProperty(month)) {
          monthPriceMap[month] = price;
        }
      });
  
      const chartData = {
        labels: labels,
        datasets: [{
          label: 'revenue',
          data: labels.map(month => ({
            x: month,
            y: monthPriceMap[month]
          })),
          backgroundColor: 'rgba(255, 206, 86, 0.2)',
          borderColor: 'rgb(75, 192, 192)',
          borderWidth: 3,
          tension: 0.1
        }]
      };
  
      const chartOptions = {
        responsive: true,
        scales: {
          y: {
            beginAtZero: true
          }
        },
        plugins: {
          tooltip: {
            callbacks: {
              label: function (context) {
                var label = context.dataset.label || '';
                if (label) {
                  label += ': ';
                }
                label += context.parsed.y || '';
                return label;
              }
            }
          }
        }
      };
  
      const myChart = Chart.getChart(ctx);
      if (myChart) {
        myChart.destroy();
      }
  
      const newChart = new Chart(ctx, {
        type: 'line',
        data: chartData,
        options: chartOptions
      });
    });
  }
  
  public getdataStatic(d1: Date, d2: Date) {
    this.subService.getstaticData(d1, d2).subscribe(data => {
      this.list = data;
      console.log(this.list);
      this.abonPrixTotal = data[data.length-1].abonPrixTotal;
      const ctx = (this.myChartCanvas.nativeElement as HTMLCanvasElement).getContext('2d');
      const chartData = {    
        labels: this.list.map(item => item.name),
        datasets: [ {
          label: 'Subscription Price and Number of Subscriptions',
          data: this.list.map(item => ({
            x: item.name,
            y: item.abonPrix,
            label: item.numberSub
          })),
          backgroundColor: 'rgba(255, 206, 86, 0.2)',
          borderColor: 'rgba(255, 206, 86, 1)',
          borderWidth: 1
        }]
      };
      const chartOptions = {
        responsive: true,
       scales: {
    y: {
      beginAtZero: true
    }
  },
  plugins: {
    tooltip: {
      callbacks: {
        label: function(context) {
          var label = context.dataset.label || '';
          if (label) {
            label += ': ';
          }
          label += context.formattedValue;
          const data = context.chart.data.datasets[0].data[context.dataIndex];
          label += ` (${data.label})`;
          return label;
        }
      }
    }
  }
      };
      const myChart = Chart.getChart(ctx);
      if (myChart) {
        myChart.destroy();
      }
      
      const newChart = new Chart(ctx, {
        type: 'bar',
        data: chartData,
        options: chartOptions
      });
    });
  }
  calculateWorkingDays(startDate: any, endDate: any): number {
      const start = new Date(startDate);
      const end = new Date(endDate);
  
      var diff = Math.abs(start.getTime() - end.getTime());
      var diffDays = Math.ceil(diff / (1000 * 3600 * 24)); 
      console.log("Diff in Days: " + diffDays);
      return diffDays
  
  }
  onSubmit(){
    this.getdataStatic(this.dateDebut, this.dateFin);
    this.resetForm();
  }
  resetForm() {
    const form = document.querySelector('form');
    form.reset();
    
  }
  submited(){
    this.getRevenues();
    this.resetForm();
    
  }
}
