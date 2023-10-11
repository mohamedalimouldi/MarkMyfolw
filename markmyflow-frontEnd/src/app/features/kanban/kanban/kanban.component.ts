import {
  CdkDragDrop,
  moveItemInArray,
  transferArrayItem,
} from '@angular/cdk/drag-drop';
import { Component, Input, OnInit } from '@angular/core';
import { TaskService } from 'src/app/core/services/task.service';
import { WorkflowService } from 'src/app/core/services/workflow.service';

@Component({
  selector: 'app-kanban',
  templateUrl: './kanban.component.html',
  styleUrls: ['./kanban.component.scss'],
})
export class KanbanComponent implements OnInit {
  @Input() data: any;
  constructor(private taskService: TaskService,private projectService:WorkflowService) {}

  ngOnInit(): void {
    for (let index = 0; index < this.data.tasks.length; index++) {
      const element = this.data.tasks[index];
      if (element.status == 'DRAFT') {
        this.todo.push(element);
      } else if (element.status == 'ASSIGNED') {
        this.done.push(element);
      } else if (element.status == 'CLOSED') {
        this.closed.push(element);
      }
    }
  }
  todo = [];
  done = [];
  closed = [];

  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      this.done.push();
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    } else {
      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
  }
  updateStatus() {
    for (let index = 0; index < this.data.tasks.length; index++) {
      const element = this.data.tasks[index];
      if (this.todo.includes(element)) {


        const newTask: any = {
          id: element.id,
          name: element.name,
          debute: element.debute,
          deadline: element.deadline,
          description: element.description,
          status: 'DRAFT',
          price: element.price,
          payed: element.payed,
          userId: element.userId,
          project_id: element.project_id
        };
        this.taskService.editTask(newTask,newTask.id).subscribe(
          (data)=>{
            console.log("******** updatee*****");
            console.log(data)
          },(err)=>{console.log(err)}
        )

      }else if (this.done.includes(element)){
        const newTask: any = {
          id: element.id,
          name: element.name,
          debute: element.debute,
          deadline: element.deadline,
          description: element.description,
          status: 'ASSIGNED',
          price: element.price,
          payed: element.payed,
          userId: element.userId,
          project_id: element.project_id
        };
        this.taskService.editTask(newTask,newTask.id).subscribe(
          (data)=>{
            console.log("******** updatee*****");
            console.log(data)
          },(err)=>{console.log(err)}
        )

      }else
      {
        const newTask: any = {
          id: element.id,
          name: element.name,
          debute: element.debute,
          deadline: element.deadline,
          description: element.description,
          status: 'CLOSED',
          price: element.price,
          payed: element.payed,
          userId: element.userId,
          project_id: element.project_id
        };
        this.taskService.editTask(newTask,newTask.id).subscribe(
          (data)=>{
            console.log("******** updatee*****");
            console.log(data)
          },(err)=>{console.log(err)}
        )
      }

      const newProject: any = {
        id: this.data.id,
        name: this.data.name,
        debute: this.data.debute,
        deadline: this.data.deadline,
        description: this.data.description,
        status: this.data.status,
        tasks: this.data.tasks
      };
       this.projectService.editProject(newProject,newProject.id).subscribe(
        (data)=>{
          console.log("******** updatee*****");
          console.log(data)
        },(err)=>{console.log(err)}
      )

      }
    }
  }

