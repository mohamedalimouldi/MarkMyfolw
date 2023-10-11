import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { passage } from 'src/app/core/models/passage';
import { TestService } from 'src/app/core/services/test.service';

@Component({
  selector: 'app-passagelist',
  templateUrl: './passagelist.component.html',
  styleUrls: ['./passagelist.component.scss'],
  providers: [TestService]

})
export class PassagelistComponent {

  p: passage[];
  selectedNumber: number;
  selectedEndNumber: number;
  numbers: number[] = Array.from({length: 11}, (_, i) => i);


  constructor(private x:TestService, private activate:ActivatedRoute)
  {
    this.p=[]  
  }

  ngOnInit() : void  {
  


    console.log('welcome aziz')
    this.getpass();
    ;} 
  
  public getpass(): void {

    this.x.getpassage().subscribe((Response: passage[] ) =>  {console.log(Response)
      this.p=Response;
  
   })
}

 public gettopliste(){

  this.x.gettoppassage().subscribe((Response: passage[]) => {console.log(Response)
    this.p=Response;
    
  })
 }
 getpassagebyscore() {
  this.x.getPassagesByScore(this.selectedNumber, this.selectedEndNumber )
    .subscribe(data => {
      this.p = data;
    });
}


}
