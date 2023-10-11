import { Component, OnInit } from '@angular/core';

import { SubscriptionService } from 'src/app/core/services/subscription.service';
import { Subscription } from 'src/app/core/models/subscription';
import { CompanyService } from 'src/app/core/services/company.service';
import { NgForm } from '@angular/forms';
import { Company } from 'src/app/core/models/company';
import { HttpErrorResponse } from '@angular/common/http';
import { packet } from 'src/app/core/models/packet';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent {

  public subscription: Subscription [];
  public company: Company = new Company();
  public Packets : packet[]
  constructor(private subscriptionservice: SubscriptionService, private companyService: CompanyService){
    this.subscription= [];
  }
  ngOnInit(): void {
   
    this.subscriptionservice.getSubscription().subscribe(data => {
      this.subscription = data;
    });
    this.Packets = this.subscriptionservice.getPacket();
    
  }
  public addcompany(): void{
    this.companyService.addEntreprise(this.company).subscribe(
      (response: Company)=>{
        console.log(response);
      }
    )
  }
  onSubmit(){
    console.log(this.company);
    this.addcompany();
  }


}
