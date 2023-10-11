import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SafeurlPipe } from './pipes/safeurl.pipe';



@NgModule({
  declarations: [
    SafeurlPipe
  ],
  imports: [
    CommonModule
  ],
  exports:[
    SafeurlPipe,
  ]
})
export class SharedModule { }
