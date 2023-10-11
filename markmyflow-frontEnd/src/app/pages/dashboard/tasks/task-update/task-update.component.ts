import { Component } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { TaskService } from 'src/app/core/services/task.service';
import { WorkflowService } from 'src/app/core/services/workflow.service';

@Component({
  selector: 'app-task-update',
  templateUrl: './task-update.component.html',
  styleUrls: ['./task-update.component.scss']
})
export class TaskUpdateComponent {
  taskRetrieved: any={} ;
  paid:any = false;
  project:any;
  id:number=0;
  divVisible: boolean = false;

  constructor(
    private taskService: TaskService,
    private projectService : WorkflowService,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {

    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params['id'];
    });

    this.taskService.getTaskById(this.id).subscribe((result) => {
        this.taskRetrieved = result;
      console.log("******** on init task retrived");
      console.log(this.taskRetrieved);

    });

  }

  onUpdate() {

    const newTask: any = {
      id: this.taskRetrieved.id,
      name: this.taskRetrieved.name,
      debute: this.taskRetrieved.debute,
      deadline: this.taskRetrieved.deadline,
      description: this.taskRetrieved.description,
      status: this.taskRetrieved.status,
      price: this.taskRetrieved.price,
      payed: this.paid,
      userId: this.taskRetrieved.userId,
      project_id: this.taskRetrieved.project_id
    };
     this.taskService.editTask(newTask,this.id).subscribe(
      (data)=>{
        console.log("******** updatee*****");
        console.log(data)
      },(err)=>{console.log(err)}
    )
    this.divVisible = true;

    console.log(newTask)
  }

}
