import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CompanyDetailsComponent } from './company-details/company-details.component';
import { ManageCompanyComponent } from './manage-company/manage-company.component';

const routes: Routes = [
  {
    path:"mlist",
    component:ManageCompanyComponent
  },
  {
    path:"details/:id",
    component:CompanyDetailsComponent
    
  
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompanyManageRoutingModule { }
