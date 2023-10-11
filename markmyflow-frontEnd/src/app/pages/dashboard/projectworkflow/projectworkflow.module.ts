import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ProjectworkflowRoutingModule } from './projectworkflow-routing.module';
import { ProjectworkflowListComponent } from './projectworkflow-list/projectworkflow-list.component';
import { LayoutModule } from 'src/app/layout/layout.module';
import { DashboardComponent } from '../dashboard.component';
import { ProjectworkflowDetailsComponent } from './projectworkflow-details/projectworkflow-details.component';
import { ProjectworkflowAddComponent } from './projectworkflow-add/projectworkflow-add.component';

import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProjectworkflowUpdateComponent } from './projectworkflow-update/projectworkflow-update.component';
import { AvatarModule } from 'src/app/features/avatar/avatar.module';
import { FlowchartModule } from 'src/app/features/flowchart/flowchart.module';
import { KanbanModule } from 'src/app/features/kanban/kanban.module';
import { TasksModule } from '../tasks/tasks.module';
import { TasksListComponent } from '../tasks/tasks-list/tasks-list.component';


@NgModule({
  declarations: [
    ProjectworkflowListComponent,
    ProjectworkflowDetailsComponent,
    ProjectworkflowAddComponent,
    ProjectworkflowUpdateComponent,



  ],
  imports: [
    CommonModule,
    ProjectworkflowRoutingModule,
    LayoutModule,
    FormsModule,
    AvatarModule,
    FlowchartModule,
    KanbanModule,
    TasksModule




  ]
})
export class ProjectworkflowModule { }
