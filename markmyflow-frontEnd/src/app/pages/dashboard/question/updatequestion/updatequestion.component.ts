import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { question } from 'src/app/core/models/question';
import { TestService } from 'src/app/core/services/test.service';

@Component({
  selector: 'app-updatequestion',
  templateUrl: './updatequestion.component.html',
  styleUrls: ['./updatequestion.component.scss'],
  providers: [TestService]
})
export class UpdatequestionComponent implements OnInit{
  quest: question=new question();
  id: number=0;

  constructor(private x:TestService, private activate:ActivatedRoute)
  {
    
  }
  ngOnInit() : void  {
    this.activate.params.subscribe((params: Params) => {
      this.id = params['id'];
    });
    this.x.getquestbyid(this.id).subscribe((response: any) => {
      console.log(response);
      this.quest= response; 
    });
       }
       updatequ()
{ 
this.x.updatequest(this.quest).subscribe(data=> this.quest=data)
}


}
