import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TestlistComponent } from './testlist/testlist.component';
import { AddtestComponent } from './addtest/addtest.component';
import { UpdateestComponent } from './updateest/updateest.component';
import { AddquestionComponent } from '../question/addquestion/addquestion.component';

const routes: Routes = [{
  path:'testlist',
  component:TestlistComponent

},
{ 
path:'updatetest/:id',
component:UpdateestComponent

},
{ 
path:'addtest',
  component:AddtestComponent

},

];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class TestEngineRoutingModule { }
