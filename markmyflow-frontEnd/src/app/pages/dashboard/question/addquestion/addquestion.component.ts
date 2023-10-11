import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Params } from '@angular/router';
import { question } from 'src/app/core/models/question';
import { testvalidation } from 'src/app/core/models/testvalidation';
import { TestService } from 'src/app/core/services/test.service';

@Component({
  selector: 'app-addquestion',
  templateUrl: './addquestion.component.html',
  styleUrls: ['./addquestion.component.scss'],
  providers: [TestService]
})
export class AddquestionComponent implements OnInit{
  quest: question=new question();
  questionText: string;
  id: number;
  a: String;

  constructor(private x:TestService, private activate:ActivatedRoute)
  {
    
  }
  

  addquest()
  {
    this.x.savequestion(this.quest).subscribe(data => {
      console.log('response')
      this.quest=data;
    })
  }
  affect(quest: question,id:number)
  {
    this.x.savequesttotest(this.quest,this.id).subscribe(data=>{

      this.quest=data;
  })
  this.a="ajoutreussi"
  }
  
  ngOnInit(): void {
    this.activate.params.subscribe((params: Params) => {
      this.id = params['id'];
    });
    this.x.gettestbyid(this.id).subscribe((response: any) => {
      console.log(response);
     
    });
    
  }
 

  

}
