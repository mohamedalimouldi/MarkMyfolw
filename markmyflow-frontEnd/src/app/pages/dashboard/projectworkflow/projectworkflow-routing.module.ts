import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ProjectworkflowListComponent } from './projectworkflow-list/projectworkflow-list.component';
import { ProjectworkflowDetailsComponent } from './projectworkflow-details/projectworkflow-details.component';
import { ProjectworkflowAddComponent } from './projectworkflow-add/projectworkflow-add.component';
import { ProjectworkflowUpdateComponent } from './projectworkflow-update/projectworkflow-update.component';
import { KanbanComponent } from 'src/app/features/kanban/kanban/kanban.component';

const routes: Routes = [
  {
    path:'list',
    component:ProjectworkflowListComponent

  },
  {
    path:'details/:id',
    component:ProjectworkflowDetailsComponent

  },
  {
    path:'kanban/:id',
    component:KanbanComponent

  },
  {
    path:'add',
    component:ProjectworkflowAddComponent

  },
  {
    path:'update/:id',
    component:ProjectworkflowUpdateComponent

  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ProjectworkflowRoutingModule { }
