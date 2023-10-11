import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MissionListComponent } from './mission-list/mission-list.component';
import { MissionDetailsComponent } from './mission-details/mission-details.component';

const routes: Routes = [
 {
  path:"list",
  component:MissionListComponent
 },
 {
  path:"details/:id",
  component:MissionDetailsComponent
 },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class MissionBrowserRoutingModule { }
