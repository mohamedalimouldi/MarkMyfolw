import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router, Params } from '@angular/router';
import { IPayPalConfig, ICreateOrderRequest } from 'ngx-paypal';
import { Company } from 'src/app/core/models/company';
import { packet } from 'src/app/core/models/packet';
import { Subscription } from 'src/app/core/models/subscription';
import { CompanyService } from 'src/app/core/services/company.service';
import { SubscriptionService } from 'src/app/core/services/subscription.service';
import { UserService } from 'src/app/core/services/user.service';
@Component({
  selector: 'app-buy-sub',
  templateUrl: './buy-sub.component.html',
  styleUrls: ['./buy-sub.component.scss']
})
export class BuySubComponent implements OnInit{
  cartTotal!: any;
  company: Company=new Company();
  c1: Company=new Company();
  sub : Subscription
  id: number
  packet: any
  subscri : Subscription
  public payPalConfig?: IPayPalConfig;
  showSuccess! :any
  public name : string =""
  public cmp: Company= {
    id : 0,
    name:"",
    email:"",
    codefiscal :"",
    address:"",
    number:"",
    nameuser:"",
    lastnuser:"",
    emailUser:"",
    password:"",

  }
  
  constructor(private cs: CompanyService,private _userService:UserService ,private sb: SubscriptionService, private router: Router, private activetedRoute: ActivatedRoute){}
  ngOnInit(): void {
    this.activetedRoute.params.subscribe((params: Params)=>{
      this.id = +params['id'];
      console.log(this.id)
      this.packet= this.sb.getPacketById(this.id)
      console.log(this.packet)
      console.log(this.sb.getPacketById(this.id))
    });
    this.initConfig();
  }
  resetForm() {
    const form = document.querySelector('form');
    form.reset();
    
  }
    public addcompany(): void{
      this.cs.addEntreprise(this.cmp).subscribe(
        (response: Company)=>{
          console.log(response);
          this.company=response
          //
        }
      )
    }
     sleep(ms: number) {
      return new Promise(resolve => setTimeout(resolve, ms));
    }
    
    async add(){ 
      await this.addcompany()
      await this.sleep(2000);
      //ajout entripise
      //id=identripise
      console.log(this.company)
      let body={
        prixAbon:this.packet.prixAbon,
        typeSubscription: this.packet.type,
        entreprise: this.company
        
      }
  
      let bodyUser={
        firstname:this.cmp.nameuser,
        lastname:this.cmp.lastnuser,
        username:this.cmp.emailUser,
        password:this.cmp.password,
        email:this.cmp.emailUser,
        phone:this.cmp.number,
        company_id:this.company.id
      }
      console.log(bodyUser)
      //ajout bel body hedha
      await this.sb.addSubscription(body).subscribe( Response =>{
        console.log(Response),
        this.sub=Response
        this._userService.registerAdmin(bodyUser).subscribe((res:any)=> {
          this.resetForm();
        })
        }
      )
    }
    private initConfig(): void {
      this.payPalConfig = {
      currency: 'EUR',
      clientId: 'AczY0Mvrjxta6tfUblBSAwrDxf7AlTOWUlaluulO_HOzCAGFTIqx09071g7ZGGTCljEBGKsmWobm184z',
      createOrderOnClient: (data) => <ICreateOrderRequest>{
        intent: 'CAPTURE',
        purchase_units: [
          {
            amount: {
              currency_code: 'EUR',
              value: this.sub.prixAbon.toString(),
              breakdown: {
                item_total: {
                  currency_code: 'EUR',
                  value: this.sub.prixAbon.toString()
                }
              }
            },
            items: [
              {
                name: 'Enterprise Subscription',
                quantity: '1',
                category: 'DIGITAL_GOODS',
                unit_amount: {
                  currency_code: 'EUR',
                  value: this.sub.prixAbon.toString(),
                },
              }
            ]
          }
        ]
      },
      advanced: {
        commit: 'true'
      },
      style: {
        label: 'paypal',
        layout: 'vertical'
      },
      onApprove: (data, actions) => {
        console.log('onApprove - transaction was approved, but not authorized', data, actions);
        actions.order.get().then(details => {
          if(details.status == 'COMPLETED')
          console.log('onApprove - you can get full order details inside onApprove: ', details);
          this.router.navigate['/dashboard']
        });
       
      },
      onClientAuthorization: (data) => {
        console.log('onClientAuthorization - you should probably inform your server about completed transaction at this point', data);
        this.showSuccess = true;
      },
      onCancel: (data, actions) => {
        console.log('OnCancel', data, actions);
      },
      onError: err => {
        console.log('OnError', err);
      },
      onClick: (data, actions) => {
        console.log('onClick', data, actions);
      },
    };
    }
  }
