import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { TaskService } from 'src/app/core/services/task.service';
import { WorkflowService } from 'src/app/core/services/workflow.service';
import { ProjectworkflowUpdateComponent } from '../projectworkflow-update/projectworkflow-update.component';

@Component({
  selector: 'app-projectworkflow-details',
  templateUrl: './projectworkflow-details.component.html',
  styleUrls: ['./projectworkflow-details.component.scss'],
})
export class ProjectworkflowDetailsComponent implements OnInit {
  project: any;
  tasks: any[] = [];
  constructor(private router:Router,private activatedRoute: ActivatedRoute,private projectService: WorkflowService,private taskService : TaskService) { }
  id : number = 0;



  ngOnInit() {
    this.getProjectById(this.id);

  }

  exportReport(id:number){
    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params['id'];
    });

    this.projectService.exportToPDF(this.id).subscribe((blob: Blob) => {
      // Create a URL for the blob data
      const url = URL.createObjectURL(blob);

      // Create a link element to download the PDF
      const link = document.createElement('a');
      link.href = url;
      link.download = 'report.pdf';
      link.click();

      // Release the object URL
      URL.revokeObjectURL(url);
    });
  }

  getProjectById(id:number) {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params['id'];
    });

    this.projectService.getProjectById(this.id)
    .subscribe(
      result => {
        // Handle resu  lt
        console.log(result);
        this.project = result;
      }
    );
  }

  deleteProject(id:number) {
    this.activatedRoute.params.subscribe((params: Params) => {
      this.id = params['id'];
    });
    this.projectService.deleteProject(this.id)
    .subscribe(
      result => {
        // Handle resu  lt
        console.log(result);
        this.project = result;
      }
    );

  }


}
