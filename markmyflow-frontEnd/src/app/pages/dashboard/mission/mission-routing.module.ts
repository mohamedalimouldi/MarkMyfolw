import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MissionListComponent } from './mission-list/mission-list.component';
import { AddMissionComponent } from './add-mission/add-mission.component';
import { ParticipatedUsersListComponent } from './participated-users-list/participated-users-list.component';

const routes: Routes = [
  {
    path:'missionlist/:id',
    component:MissionListComponent
  },
  {
    path:'addmission/:id',
    component:AddMissionComponent
  },
  {
    path:'applications/:id',
    component:ParticipatedUsersListComponent
  },

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MissionRoutingModule { }
