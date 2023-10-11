import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { Company } from 'src/app/core/models/company';
import { CompanyService } from 'src/app/core/services/company.service';

@Component({
  selector: 'app-company-details',
  templateUrl: './company-details.component.html',
  styleUrls: ['./company-details.component.scss']
})
export class CompanyDetailsComponent implements OnInit {
  id: number=0;
  des=[]
  public company: Company=new Company();
  constructor(private activetedRoute: ActivatedRoute, private companyService: CompanyService){
  
  }
  ngOnInit(): void {
    this.activetedRoute.params.subscribe((params: Params)=>{
      this.id = params['id'];
    
    this.companyService.getByid(this.id).subscribe((res:any) =>{
      this.company=res;
      console.log(this.company)
    this.companyService.getProject(this.id).subscribe((res:any) =>{
      this.des=res.projectWorkflow;
      console.log(this.des)
    })
  });
});

  }

}
