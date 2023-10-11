import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Company } from 'src/app/core/models/company';
import { ProjectWorkflow } from 'src/app/core/models/project-workflow';
import { CompanyService } from 'src/app/core/services/company.service';

@Component({
  selector: 'app-company-details',
  templateUrl: './company-details.component.html',
  styleUrls: ['./company-details.component.scss']
})
export class CompanyDetailsComponent {
  id: number=0;
  public company: Company=new Company();
  public project: any[];
  constructor(private activetedRoute: ActivatedRoute, private companyService: CompanyService){
    this.project= [];
  }
  ngOnInit(){
    this.activetedRoute.params.subscribe((params: Params)=>{
      this.id = params['id'];
    });
    this.companyService.getByid(this.id).subscribe((res:any) =>{
      this.company=res;
    });
    this.companyService.getProject(this.id).subscribe((response: any )=> {
      this.project = response.projectWorkflow;
      console.log(this.project);
      
    });

  }

}
