import { Component } from '@angular/core';
import { ActivatedRoute, Params, Router } from '@angular/router';
import { passage } from 'src/app/core/models/passage';
import { TestService } from 'src/app/core/services/test.service';

@Component({
  selector: 'app-quiz',
  templateUrl: './quiz.component.html',
  styleUrls: ['./quiz.component.scss'],
  providers: [TestService]

  
})
export class QuizComponent {
  p: passage[];
  pa: passage=new passage();
  id: number=0;


  constructor(private x:TestService, private activate:ActivatedRoute )
  {
    this.p=[]  
  }

  ngOnInit() : void  {
   this.activate.params.subscribe((params: Params) => {
        this.id = params['id'];
      });
      this.x.getpassagebyid(this.id).subscribe((response: any) => {
        console.log(response);
        this.pa=response;
       
      });
      
    
  


   // console.log('welcome aziz')
    //this.getpass();
  //  ;
  } 
  
  public getpass(): void {

    this.x.getpassage().subscribe((Response: passage[] ) =>  {console.log(Response)
      this.p=Response;

  
   })
}

 


}
