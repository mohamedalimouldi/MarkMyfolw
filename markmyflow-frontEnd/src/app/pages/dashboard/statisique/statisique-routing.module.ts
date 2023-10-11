import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { StaticComponent } from './static/static.component';

const routes: Routes = [
  {
    path:'static',
    component: StaticComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StatisiqueRoutingModule { }
