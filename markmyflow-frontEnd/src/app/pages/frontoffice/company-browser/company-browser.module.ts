import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompanyBrowserRoutingModule } from './company-browser-routing.module';

import { CompanyDetailsComponent } from './company-details/company-details.component';
import { CompanyListsComponent } from './company-lists/company-lists.component';


@NgModule({
  declarations: [
   
    CompanyDetailsComponent,
    CompanyListsComponent
  ],
  imports: [
    CommonModule,
    CompanyBrowserRoutingModule

  ],
  providers:[
    
  ]
})
export class CompanyBrowserModule { }
