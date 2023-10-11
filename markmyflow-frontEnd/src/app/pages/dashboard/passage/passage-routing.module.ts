import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { PassagelistComponent } from './passagelist/passagelist.component';

const routes: Routes = [
  {
    path:'passagelist',
    component:PassagelistComponent
  
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class PassageRoutingModule { }
