import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserBackOfficeComponent } from './user-back-office/user-back-office.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { DashboardComponent } from './dashboard.component';
import { MainDashboardComponent } from './main-dashboard/main-dashboard.component';
import { LayoutModule } from '../../layout/layout.module';
import { ProjectworkflowModule } from './projectworkflow/projectworkflow.module';
import { TasksModule } from './tasks/tasks.module';
import { CoreModule } from 'src/app/core/core.module';
import { TestEngineModule } from './test-engine/test-engine.module';
import { SharedModule } from 'src/app/shared/shared.module';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    DashboardComponent,
    MainDashboardComponent,
    //TestListComponent,
    UserBackOfficeComponent
    
  ],
  imports: [
    CommonModule,
    DashboardRoutingModule,
    LayoutModule,
    CoreModule,
    TestEngineModule,
    SharedModule,
    ReactiveFormsModule,
  ]
})
export class DashboardModule { }
