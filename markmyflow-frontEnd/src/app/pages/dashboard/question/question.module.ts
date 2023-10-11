import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { QuestionRoutingModule } from './question-routing.module';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { QuestionlistComponent } from './questionlist/questionlist.component';
import { AddquestionComponent } from './addquestion/addquestion.component';
import { UpdatequestionComponent } from './updatequestion/updatequestion.component';


@NgModule({
  declarations: [
    QuestionlistComponent,
    AddquestionComponent,
    UpdatequestionComponent,
    

  ],
  
  imports: [
    CommonModule,
    FormsModule,
    RouterModule,
    QuestionRoutingModule
  ]
})
export class QuestionModule { }
