import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NgxPayPalModule } from 'ngx-paypal';

import { BuySubscriptionRoutingModule } from './buy-subscription-routing.module';
import { BuySubComponent } from './buy-sub/buy-sub.component';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    BuySubComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    BuySubscriptionRoutingModule,
    NgxPayPalModule,
    FormsModule
  ]
})
export class BuySubscriptionModule { }
