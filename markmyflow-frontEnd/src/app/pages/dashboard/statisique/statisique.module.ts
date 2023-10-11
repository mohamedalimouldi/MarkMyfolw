import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { StatisiqueRoutingModule } from './statisique-routing.module';
import { StaticComponent } from './static/static.component';
import { Chart } from 'chart.js';
import { FormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    StaticComponent
  ],
  imports: [
    CommonModule,
    StatisiqueRoutingModule,
    FormsModule

  ]
})
export class StatisiqueModule { }
