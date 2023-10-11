import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CompanyManageRoutingModule } from './company-manage-routing.module';
import { ManageCompanyComponent } from './manage-company/manage-company.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CompanyDetailsComponent } from './company-details/company-details.component';


@NgModule({
  declarations: [
    ManageCompanyComponent,
    CompanyDetailsComponent,
    
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    CompanyManageRoutingModule,
    FormsModule

    
  ],
  providers:[

  ]
})
export class CompanyManageModule { }
