import { DatePipe, formatDate } from '@angular/common';
import { Component } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { catchError, tap, throwError } from 'rxjs';
import { Projectworkflow } from 'src/app/core/models/projectworkflow';
import { WorkflowService } from 'src/app/core/services/workflow.service';

@Component({
  selector: 'app-projectworkflow-add',
  templateUrl: './projectworkflow-add.component.html',
  styleUrls: ['./projectworkflow-add.component.scss']
})
export class ProjectworkflowAddComponent {
  project:Projectworkflow ={id:0,company_id:0,debute:new Date(),deadline:new Date(),description:'',name:'',status:'ON_HOLD',tasks:[]};
  divVisible = false;
  isNameValid=true;
  isDebuteDateValid = true;
  isDeadlineValid = true;
  validForm=false;

  constructor(private projectService: WorkflowService) { }



  onSubmit() {
    this.projectService.addProject(this.project).pipe(
      tap(project => console.log('Project added:', project)),
      catchError(error => {
        console.error('Error adding project:', error);
        return throwError(error);
      })
    ).subscribe(() => {
    this.project ={id:0,company_id:0,debute:new Date(),deadline:new Date(),description:'',name:'',status:'ON_HOLD',tasks:[]};
    });
    console.log(this.project)
    this.divVisible = true;

  }
  validateForm() {
    let value = this.project;
    this.isNameValid=!!value.name;
    this.isDebuteDateValid = !!value.debute; // Check if the value is not empty
    this.isDeadlineValid = !!value.deadline; // Check if the value is not empty

    this.validForm=(value.debute<=value.deadline)&&(this.isDeadlineValid&&this.isDebuteDateValid)&&this.isNameValid;

  }

  }




