import { Component, OnInit } from '@angular/core';
import { Observable, filter } from 'rxjs';
import { Projectworkflow } from 'src/app/core/models/projectworkflow';
import { WorkflowService } from 'src/app/core/services/workflow.service';
import { trigger, transition, style, animate } from '@angular/animations';


@Component({
  selector: 'app-projectworkflow-list',
  templateUrl: './projectworkflow-list.component.html',
  styleUrls: ['./projectworkflow-list.component.scss']
})
export class ProjectworkflowListComponent implements OnInit {
  projects: any[] = [];
  filteredData:any[]=[];
  searchTerm:string="";
  dateSent : any;

  constructor(private projectService: WorkflowService) { }


  ngOnInit() {
    this.getProjects();
    this.calculateDiff(this.dateSent);

  }

  getProjects() {
    this.projectService.getProjectList()
    .subscribe(
      result => {
        // Handle resu  lt
        console.log(result);
        this.projects = result;
        this.filteredData=result;
      }
    );
  }
  filterData(searchTerm: string) {
    if (searchTerm) {
      this.filteredData = this.projects.filter(item =>
        item.name.toLowerCase().includes(searchTerm.toLowerCase())
      );
    } else {
      this.filteredData = this.projects;
    }
  }
  onSearch() {
    this.filterData(this.searchTerm);
  }
  calculateDiff(dateSent:Date){
    let currentDate = new Date();
    dateSent = new Date(dateSent);

    return Math.floor(Date.UTC(dateSent.getFullYear(), dateSent.getMonth(), dateSent.getDate()) - Date.UTC(currentDate.getFullYear(), currentDate.getMonth(), currentDate.getDate()) ) /(1000 * 60 * 60 * 24);
}




}
