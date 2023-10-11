import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { catchError, tap, throwError } from 'rxjs';
import { Projectworkflow } from 'src/app/core/models/projectworkflow';
import { WorkflowService } from 'src/app/core/services/workflow.service';
@Component({
  selector: 'app-projectworkflow-update',
  templateUrl: './projectworkflow-update.component.html',
  styleUrls: ['./projectworkflow-update.component.scss'],
})
export class ProjectworkflowUpdateComponent implements OnInit {
  divVisible = false;

  projectRetrieved: any={} ;
  id:number=0;
  constructor(
    private projectService: WorkflowService,
    private activatedRoute: ActivatedRoute
  ) {}
  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params['id'];

    });

    this.projectService.getProjectById(this.id).subscribe((result) => {

      this.projectRetrieved = result;
      console.log("******** on init project retrived");
      console.log(this.projectRetrieved);
    });


  }

  onUpdate() {
    const newProject: any = {
      id: this.projectRetrieved.id,
      name: this.projectRetrieved.name,
      debute: this.projectRetrieved.debute,
      deadline: this.projectRetrieved.deadline,
      description: this.projectRetrieved.description,
      status: this.projectRetrieved.status,
      tasks: this.projectRetrieved.tasks
    };
     this.projectService.editProject(newProject,this.id).subscribe(
      (data)=>{
        console.log("******** updatee*****");
        console.log(data)
      },(err)=>{console.log(err)}
    )
    this.divVisible = true;

    console.log(newProject)
  }


}
