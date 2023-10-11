import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BuySubComponent } from './buy-sub/buy-sub.component';

const routes: Routes = [
  {
    path:"subscription/:id",
  component:BuySubComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BuySubscriptionRoutingModule { }
