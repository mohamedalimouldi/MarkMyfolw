import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FlowchartComponent } from './flowchart/flowchart.component';



@NgModule({
  declarations: [
    FlowchartComponent
  ],
  imports: [
    CommonModule
  ],
  exports:[
    FlowchartComponent
  ]
})
export class FlowchartModule { }
