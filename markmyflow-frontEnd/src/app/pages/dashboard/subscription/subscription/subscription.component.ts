import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Company } from 'src/app/core/models/company';
import { Subscription } from 'src/app/core/models/subscription';
import { SubscriptionService } from 'src/app/core/services/subscription.service';

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.scss']
})
export class SubscriptionComponent implements OnInit {
  @ViewChild("closeModal")
  closeButton:ElementRef
  @ViewChild("OpenModal")
  openmodal:ElementRef
  edit=-1;
  subscription: Subscription= new Subscription();
  subscriptions: Subscription[]
  id : number;
  company: Company=new Company();
  DocForm = new FormGroup({
    prixAbon: new FormControl(0.0,Validators.required),
    dateDebut: new FormControl(new Date(),Validators.required),
    dateFin: new FormControl(new Date(), [Validators.required]),
    typeSubscription: new FormControl(null, [Validators.required]),
   
  });
  constructor(private subscriptionService: SubscriptionService){}
  ngOnInit(): void {
    this.getSubscriptions();
    
  }
  
  public getSubscriptions(){
    this.subscriptionService.getSubscription().subscribe(data=>{
      this.subscriptions= data;
      console.log(this.subscriptions)
      
    });
  }
  deleteSubscription(id: number){
    this.subscriptionService.deleteSubscription(id).subscribe( data => {
        console.log(data);
      this.getSubscriptions();
    })
  }
 
  public addSubscription(): void{
    this.subscriptionService.addSubscription(this.DocForm.value).subscribe(
      (response: Subscription)=>{
        console.log(response);
        this.getSubscriptions()
        this.closeButton.nativeElement.click();
      }
    )
  }
  openEdit(obj,id){
    console.log(obj)
      this.DocForm.setValue({
        
        prixAbon:obj.prixAbon,
        dateDebut:obj.dateDebut,
        dateFin:obj.dateFin,
        typeSubscription:obj.typeSubscription,
       
       
      })
      this.edit=id;
      this.openmodal.nativeElement.click();
}
async onSubmit(){
  if (this.edit<0){
    console.log(this.subscription);
    this.addSubscription();
  }
  else{
    let body={
      prixAbon:this.DocForm.value.prixAbon,
      id:this.subscriptions[this.edit].id,
      dateDebut:this.DocForm.value.dateDebut,
      dateFin:this.DocForm.value.dateFin,
      typeSubscription:this.DocForm.value.typeSubscription

    }
    let updated=await this.onUpdateSub(body);
    
     //this.companys[this.edit]=updated
  }
  this.closeButton.nativeElement.click();
}

onUpdateSub(s: Subscription): void {
  this.subscriptionService.updateSubscription(s).subscribe(
    (response: Subscription) => {
      this.getSubscriptions();
    }
  );




}

}
