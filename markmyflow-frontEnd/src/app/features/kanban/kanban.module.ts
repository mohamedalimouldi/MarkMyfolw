import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { KanbanComponent } from './kanban/kanban.component';
import { DragDropModule } from '@angular/cdk/drag-drop';
import { AvatarModule } from '../avatar/avatar.module';
import { TasksRoutingModule } from 'src/app/pages/dashboard/tasks/tasks-routing.module';



@NgModule({
  declarations: [
    KanbanComponent
  ],
  imports: [
    CommonModule,
    DragDropModule,
    AvatarModule,
    TasksRoutingModule

  ],
  exports:[
    KanbanComponent
  ]
})
export class KanbanModule { }
