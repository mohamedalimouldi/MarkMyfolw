import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SubscriptionRoutingModule } from './subscription-routing.module';
import { SubscriptionComponent } from './subscription/subscription.component';
import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    SubscriptionComponent
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    SubscriptionRoutingModule
  ]
})
export class SubscriptionModule { }
