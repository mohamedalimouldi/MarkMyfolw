import { Component, OnInit } from '@angular/core';
import { Company } from '../../../../core/models/company';
import { CompanyService } from '../../../../core/services/company.service';

@Component({
  selector: 'app-company-lists',
  templateUrl: './company-lists.component.html',
  styleUrls: ['./company-lists.component.scss']
})
export class CompanyListsComponent{

  public company: Company[];

  constructor(private companyService: CompanyService) {
    this.company = [];
  }

  ngOnInit(): void {
    this.companyService.getAllCompany().subscribe(data => {
      this.company = data; 
    });
    
  }

}
