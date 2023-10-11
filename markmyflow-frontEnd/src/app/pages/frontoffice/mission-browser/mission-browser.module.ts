import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MissionBrowserRoutingModule } from './mission-browser-routing.module';
import { MissionListComponent } from './mission-list/mission-list.component';
import { MissionDetailsComponent } from './mission-details/mission-details.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    MissionListComponent,
    MissionDetailsComponent,
    

  ],
  imports: [
    CommonModule,
    MissionBrowserRoutingModule,
    FormsModule,
  ],
  providers:[
  ]
})
export class MissionBrowserModule { }
