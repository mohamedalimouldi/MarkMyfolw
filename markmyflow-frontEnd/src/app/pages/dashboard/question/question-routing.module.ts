import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QuestionlistComponent } from './questionlist/questionlist.component';
import { AddquestionComponent } from './addquestion/addquestion.component';
import { UpdatequestionComponent } from './updatequestion/updatequestion.component';

const routes: Routes = [
  {
  path:'questionlist',
  component:QuestionlistComponent

},
{ 
  path:'updatequestion/:id',
  component:UpdatequestionComponent
  
  },
{ 
  path:'addquestion/:id',
  component:AddquestionComponent
  
  },
];


@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QuestionRoutingModule { }
