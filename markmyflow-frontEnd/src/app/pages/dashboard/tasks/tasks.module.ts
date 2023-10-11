import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TasksRoutingModule } from './tasks-routing.module';
import { TasksListComponent } from './tasks-list/tasks-list.component';
import { TasksDetailsComponent } from './tasks-details/tasks-details.component';
import { LayoutModule } from 'src/app/layout/layout.module';
import { TaskAddComponent } from './task-add/task-add.component';
import { FormsModule } from '@angular/forms';
import { TaskUpdateComponent } from './task-update/task-update.component';
import { AvatarModule } from 'src/app/features/avatar/avatar.module';
import { TagInputModule } from 'ngx-chips';
import { FlowchartModule } from 'src/app/features/flowchart/flowchart.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TaskService } from 'src/app/core/services/task.service';


@NgModule({
  declarations: [

    TasksListComponent,
    TasksDetailsComponent,
    TaskAddComponent,
    TaskUpdateComponent,

  ],
  imports: [
    CommonModule,
    TasksRoutingModule,
    LayoutModule,
    FormsModule,
    AvatarModule,

  ]
})
export class TasksModule { }
