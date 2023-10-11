import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MissionRoutingModule } from './mission-routing.module';
import { MissionListComponent } from './mission-list/mission-list.component';
import { AddMissionComponent } from './add-mission/add-mission.component';
import { FormsModule } from '@angular/forms';
import { ParticipatedUsersListComponent } from './participated-users-list/participated-users-list.component';


@NgModule({
  declarations: [
    MissionListComponent,
    AddMissionComponent,
    ParticipatedUsersListComponent
  ],
  imports: [
    CommonModule,
    MissionRoutingModule,
    FormsModule
  ]
})
export class MissionModule { }
