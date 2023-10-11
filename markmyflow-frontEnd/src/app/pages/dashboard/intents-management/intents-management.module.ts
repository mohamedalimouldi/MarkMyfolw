import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { IntentsManagementRoutingModule } from './intents-management-routing.module';
import { IntentListComponent } from './intent-list/intent-list.component';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { IntentEditorComponent } from './intent-editor/intent-editor.component';
import { TagInputModule } from 'ngx-chips';
import { ReactiveFormsModule } from '@angular/forms';

@NgModule({
  declarations: [
    IntentListComponent,
    IntentEditorComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    Ng2SmartTableModule,
    TagInputModule,
    IntentsManagementRoutingModule,
  ]
})
export class IntentsManagementModule { }
