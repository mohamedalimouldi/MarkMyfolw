import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PassageRoutingModule } from './passage-routing.module';
import { PassagelistComponent } from './passagelist/passagelist.component';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    PassagelistComponent
  ],
  imports: [
    CommonModule,
    PassageRoutingModule,
    RouterModule,
    FormsModule
  ]
})
export class PassageModule { }
