import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminGuardService } from './core/services/admin-guard.service';

const routes: Routes = [
  
{
 path:"",
 loadChildren:() => import('./pages/frontoffice/frontoffice.module').then(m => m.FrontofficeModule)
},
{
  path:"dashboard",
  loadChildren:() => import('./pages/dashboard/dashboard.module').then(m => m.DashboardModule)
  
 }
 
]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
