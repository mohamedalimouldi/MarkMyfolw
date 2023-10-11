import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { FrontofficeComponent } from './frontoffice.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { VerifyComponent } from './verify/verify.component';
import { ResetComponent } from './reset/reset.component';
import { AdminGuardService } from 'src/app/core/services/admin-guard.service';

const routes: Routes = [{
  path:'',
  component:FrontofficeComponent,
  children:[
    { 
      path:'',
      component:HomeComponent
    },
    {
      path:'missions',
      loadChildren:()=>import("./mission-browser/mission-browser.module").then(x=>x.MissionBrowserModule)
    },
    {
      path:'company',
      loadChildren:()=>import("./company-browser/company-browser.module").then(x=>x.CompanyBrowserModule)
    },
    {
      path:'acheted',
      loadChildren:()=>import("./buy-subscription/buy-subscription.module").then(x=>x.BuySubscriptionModule)
    },
    {
      path:'test',
      loadChildren:()=>import("./test-engine/test-engine.module").then(x=>x.TestEngineModule)
    },
    {
      path:'authenticate',
      component:LoginComponent
    },
    {
      path:'register',
      component:RegisterComponent
    },
    {
      path:'verify/:verificationtoken',
      component:VerifyComponent
    },
    {
      path:'reset',
      component:ResetComponent
    },
  ]

}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FrontofficeRoutingModule { }
