import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { TestEngineRoutingModule } from './test-engine-routing.module';
import { GenerateTestComponent } from './generate-test/generate-test.component';
import { QuizComponent } from './quiz/quiz.component';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { TestService } from 'src/app/core/services/test.service';
import { TestviewComponent } from './testview/testview.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { QRCodeModule } from 'angularx-qrcode';



@NgModule({
  declarations: [
    GenerateTestComponent,
    TestviewComponent,
    
  ],
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    NgbModule,
    TestEngineRoutingModule,
    QRCodeModule
  ],
  providers:[TestService
  ]
})
export class TestEngineModule { }
