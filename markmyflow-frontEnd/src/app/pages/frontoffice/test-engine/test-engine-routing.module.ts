import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { GenerateTestComponent } from './generate-test/generate-test.component';
import { QuizComponent } from './quiz/quiz.component';
import { TestviewComponent } from './testview/testview.component';


const routes: Routes = [{
  path:"generate",
  component:GenerateTestComponent
},
{
  path:"vu/:id",
  component:TestviewComponent
},

{
  path:"quiz/:id",
  component:QuizComponent
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TestEngineRoutingModule { }
