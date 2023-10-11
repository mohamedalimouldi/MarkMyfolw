import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MainDashboardComponent } from './main-dashboard/main-dashboard.component';
import { DashboardComponent } from './dashboard.component';
import { UserBackOfficeComponent } from './user-back-office/user-back-office.component';
import { AdminGuardService } from 'src/app/core/services/admin-guard.service';


const routes: Routes = [{
  path:'',
  component:DashboardComponent,
 // canActivate:[AdminGuardService],
  children:[
  {
    path:'',
    component:MainDashboardComponent

  },
  {
    path:'projectworkflow',
    loadChildren:()=>import("./projectworkflow/projectworkflow.module").then(x=>x.ProjectworkflowModule)
  },
  {
    path:'test',
    loadChildren:()=>import("./test-engine/test-engine.module").then(x=>x.TestEngineModule)
  },
  {
    path:'question',
    loadChildren:()=>import("./question/question.module").then(x=>x.QuestionModule)
  },
  {
    path:'passage',
    loadChildren:()=>import("./passage/passage.module").then(x=>x.PassageModule)
  },
  
 {
    path:'document',
    loadChildren:()=>import("./document-management/document-management.module").then(x=>x.DocumentManagementModule)
  },
  {
    path:'managecompany',
    loadChildren:()=>import("./company-manage/company-manage.module").then(x=>x.CompanyManageModule)
  },

   

  {
    path:'tasks',
    loadChildren:()=>import("./tasks/tasks.module").then(x=>x.TasksModule)
  },


  {
    path:'mission',
    loadChildren:()=>import("./mission/mission.module").then(x=>x.MissionModule)
  },

  {
    path:'userBackOff',
    component:UserBackOfficeComponent
  },
 
  {
    path:'intent',
    loadChildren:()=>import("./intents-management/intents-management.module").then(x=>x.IntentsManagementModule)
  },
  {
    path:'subscription',
    loadChildren:()=>import("./subscription/subscription.module").then(x=>x.SubscriptionModule)
  },
  {
    path:'statique',
    loadChildren:()=>import("./statisique/statisique.module").then(x=>x.StatisiqueModule)
  },
  {
    path:'userBackOff',
    component:UserBackOfficeComponent

  },
 
 

]
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
