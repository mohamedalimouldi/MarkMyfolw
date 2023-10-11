import { Component, OnInit } from '@angular/core';
import { TaskService } from 'src/app/core/services/task.service';

@Component({
  selector: 'app-tasks-list',
  templateUrl: './tasks-list.component.html',
  styleUrls: ['./tasks-list.component.scss']
})
export class TasksListComponent implements OnInit {
  tasks: any[] = [];
  constructor(private taskService: TaskService) { }
  dateSent : any;
  ngOnInit() {

    this.getTasks();
    this.calculateDiff(this.dateSent);
  }

  getTasks() {
    this.tasks;
    this.taskService.getTaskList()
    .subscribe(
      result => {
        // Handle resu  lt
        console.log(result);
        this.tasks = result;
      }
    );
  }
  calculateDiff(dateSent:Date){
    let currentDate = new Date();
    dateSent = new Date(dateSent);

    return Math.floor(Date.UTC(dateSent.getFullYear(), dateSent.getMonth(), dateSent.getDate()) - Date.UTC(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate()) ) /(1000 * 60 * 60 * 24);
}
deleteTask(id:number) {
  this.taskService.deleteTask(id)
  .subscribe(
    result => {
      // Handle resu  lt
      console.log(result);
      this.getTasks();
    }
  );
}
}
