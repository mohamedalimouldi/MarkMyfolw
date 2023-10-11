import { Component, OnInit } from '@angular/core';
import { testvalidation } from '../../../../core/models/testvalidation';
import { TestService } from '../../../../core/services/test.service';
@Component({
  selector: 'app-generate-test',
  templateUrl: './generate-test.component.html',
  styleUrls: ['./generate-test.component.scss'],
  providers: [TestService]
})
export class GenerateTestComponent implements OnInit {

  title = 'testt';
  tests: testvalidation[];
  test: testvalidation=new testvalidation;
 // testv: testvalidation;
 MyAngularxQrCode: any;
 aziz: any;

 constructor(private x:TestService)
 {
   this.tests=[];
   this.MyAngularxQrCode="http://localhost:4200/test/vu/" 
   this.aziz=2;

   

   
  
 }
 

 public gettest(): void {

  this.x.gettestt().subscribe((Response: testvalidation[] ) =>  {console.log(Response)
    this.tests=Response;

 }
   ) 
 }

 


 ngOnInit()  {
    
   console.log('welcome aziz')
   this.gettest();
   

   //href="single-job-page.html"

   }
   
}
