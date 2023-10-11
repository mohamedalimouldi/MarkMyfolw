import { Component, OnInit } from '@angular/core';
import { catchError, tap, throwError } from 'rxjs';
import { Projectworkflow } from 'src/app/core/models/projectworkflow';
import { Task } from 'src/app/core/models/task';
import { TaskService } from 'src/app/core/services/task.service';
import { WorkflowService } from 'src/app/core/services/workflow.service';

@Component({
  selector: 'app-task-add',
  templateUrl: './task-add.component.html',
  styleUrls: ['./task-add.component.scss']
})
export class TaskAddComponent implements OnInit {

  projects : Projectworkflow[] = [];
  task:Task ={id:0,userId:Number(sessionStorage.getItem('id')),debute:new Date(),deadline:new Date(),description:'',name:'',status:'DRAFT',payed:false,price:0};
  divVisible: boolean = false;
  isNameValid=true;
  isDebuteDateValid = true;
  isDeadlineValid = true;
  isPriceValid= true;
  isDescriptionValid = true;
  validForm=false;

  constructor(private taskService: TaskService, private projectService : WorkflowService) { }

  ngOnInit(): void {

  }
  onSubmit() {
    this.taskService.addTask(this.task).pipe(
      tap(task => console.log('task added:', task)),
      catchError(error => {
        console.error('Error adding task:', error);
        return throwError(error);
      })
    ).subscribe(() => {
    this.task ={id:0,userId:Number(sessionStorage.getItem('id')),debute:new Date(),deadline:new Date(),description:'',name:'',status:'DRAFT',payed:false,price:0};
    });
    console.log(this.task)
    this.divVisible = true;


  }
  validateForm() {
    let value = this.task;
    this.isNameValid=!!value.name;
    this.isDebuteDateValid = !!value.debute; // Check if the value is not empty
    this.isDeadlineValid = !!value.deadline; // Check if the value is not empty

    this.validForm=(value.debute<=value.deadline)&&(this.isDeadlineValid&&this.isDebuteDateValid)&&this.isNameValid&&this.isPriceValid&&this.isDescriptionValid;

  }
}
