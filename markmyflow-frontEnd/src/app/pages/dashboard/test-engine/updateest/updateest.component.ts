import { Component, OnInit } from '@angular/core';
import { testvalidation } from '../../../../core/models/testvalidation';
import { TestService } from '../../../../core/services/test.service';
import { ActivatedRoute ,Params} from '@angular/router';

@Component({
  selector: 'app-updateest',
  templateUrl: './updateest.component.html',
  styleUrls: ['./updateest.component.scss']
})
export class UpdateestComponent implements OnInit{
  test: testvalidation=new testvalidation();
  id: number=0;

  constructor(private x:TestService, private activate:ActivatedRoute)
  {
    
  }
  ngOnInit() : void  {
    this.activate.params.subscribe((params: Params) => {
      this.id = params['id'];
    });
    this.x.gettestbyid(this.id).subscribe((response: any) => {
      console.log(response);
      this.test = response; 
    });
    this.test.duree=null;
    this.test.pts=null;
       }
       updatequ()
{ 
this.x.updatequiz(this.test).subscribe(data=> this.test=data)
}



}
