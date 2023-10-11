import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { DocumentManagementRoutingModule } from './document-management-routing.module';
import { DocumentListComponent } from './document-list/document-list.component';
import { SharedModule } from 'src/app/shared/shared.module';
import { TagInputModule } from 'ngx-chips';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';


@NgModule({
  declarations: [
    DocumentListComponent,

  ],
  imports: [
    ReactiveFormsModule,
    CommonModule,
    FormsModule,
    TagInputModule,
    Ng2SmartTableModule,
    DocumentManagementRoutingModule,
    SharedModule
  ]
})
export class DocumentManagementModule { }
