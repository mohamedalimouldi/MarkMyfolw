import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { question } from 'src/app/core/models/question';
import { TestService } from 'src/app/core/services/test.service';

@Component({
  selector: 'app-questionlist',
  templateUrl: './questionlist.component.html',
  styleUrls: ['./questionlist.component.scss']
})
export class QuestionlistComponent {
  qu: question[];
  constructor(private x:TestService, private activate:ActivatedRoute)
  {
    this.qu=[]  
  }

  ngOnInit() : void  {
  


    console.log('welcome aziz')
    this.getquestion();
    
  
  
    } 
  
  public getquestion(): void {

    this.x.getquest().subscribe((Response: question[] ) =>  {console.log(Response)
      this.qu=Response;
  
   })
}
delete(id,index){
  console.log(index);
  this.x.deletequest(id).subscribe((res:any)=>{
    this.qu.splice(index,1);
  })
}

}
