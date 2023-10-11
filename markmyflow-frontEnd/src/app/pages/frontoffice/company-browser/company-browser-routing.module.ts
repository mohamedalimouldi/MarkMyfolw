import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { CompanyDetailsComponent } from './company-details/company-details.component';
import { CompanyListsComponent } from './company-lists/company-lists.component';

const routes: Routes = [
  {
  path:"list",
  component:CompanyListsComponent

},
{
  path:"details/:id",
  component:CompanyDetailsComponent

},
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CompanyBrowserRoutingModule { }
