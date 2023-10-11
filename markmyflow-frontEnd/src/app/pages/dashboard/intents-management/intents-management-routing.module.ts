import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { IntentListComponent } from './intent-list/intent-list.component';
import { IntentEditorComponent } from './intent-editor/intent-editor.component';

const routes: Routes = [
  {
  component:IntentListComponent,
  path:"list"
},
{
  component:IntentEditorComponent,
  path:"add"

},
{
  component:IntentEditorComponent,
  path:"edit/:id"
}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class IntentsManagementRoutingModule { }
