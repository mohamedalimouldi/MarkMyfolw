import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Task } from 'src/app/core/models/task';
import { TaskService } from 'src/app/core/services/task.service';
import { WorkflowService } from 'src/app/core/services/workflow.service';

@Component({
  selector: 'app-tasks-details',
  templateUrl: './tasks-details.component.html',
  styleUrls: ['./tasks-details.component.scss'],
})
export class TasksDetailsComponent implements OnInit {
  task: any;
  project: any;
  project_id:any;
  constructor(
    private activatedRoute: ActivatedRoute,
    private taskService: TaskService,
    private projectService: WorkflowService
  ) {}
  id: number = 0;
  projects: any[] = [];

  ngOnInit() {
    this.getTaskById(this.id);
    this.projectService.getProjectList().subscribe((result) => {
      // Handle resu  lt
      console.log(result);
      this.projects = result;
    });
  }

  getTaskById(id: number) {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params['id'];
    });

    this.taskService.getTaskById(this.id).subscribe((result) => {
      // Handle resu  lt
      console.log(result);
      this.task = result;
    });
  }
  deleteTask(id: number) {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params['id'];
    });

    this.taskService.deleteTask(this.id).subscribe((result) => {
      // Handle resu  lt
      console.log(result);
      this.task = result;
    });
  }
  AssignOnClick(project_id: number) {
    this.task = {
      id: this.task.id,
      name: this.task.name,
      debute: this.task.debute,
      deadline: this.task.deadline,
      description: this.task.description,
      status: this.task.status,
      price: this.task.price,
      payed: this.task.payed,
      userId: this.task.userId,
      project_id: project_id,
    };
    this.projectService.assignTaskProject(this.task, project_id).subscribe(
      (data) => {
        console.log('******** assign*****');
        console.log(data);
      },
      (err) => {
        console.log(err);
      }
    );

    console.log(this.task);
  }
}
