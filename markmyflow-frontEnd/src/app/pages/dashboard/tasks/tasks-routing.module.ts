import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TasksListComponent } from './tasks-list/tasks-list.component';
import { TasksDetailsComponent } from './tasks-details/tasks-details.component';
import { TaskAddComponent } from './task-add/task-add.component';
import { TaskUpdateComponent } from './task-update/task-update.component';

const routes: Routes = [
  {
    path: 'list',
    component: TasksListComponent,
  },
  {
    path: 'details/:id',
    component: TasksDetailsComponent,
  },
  {
    path: 'add',
    component: TaskAddComponent,
  },
  {
    path: 'update/:id',
    component: TaskUpdateComponent,
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class TasksRoutingModule {}
