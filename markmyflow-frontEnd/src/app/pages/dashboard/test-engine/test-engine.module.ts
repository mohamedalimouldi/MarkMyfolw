import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TestEngineRoutingModule } from './test-engine-routing.module';
import { TestlistComponent } from './testlist/testlist.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AddtestComponent } from './addtest/addtest.component';
import { UpdateestComponent } from './updateest/updateest.component';
import { AddquestionComponent } from '../question/addquestion/addquestion.component';
import { QRCodeModule } from 'angularx-qrcode';


@NgModule({
  declarations: [
    TestlistComponent,
    AddtestComponent,
    UpdateestComponent,
    

  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    TestEngineRoutingModule,
    
  ]
})
export class TestEngineModule { }
