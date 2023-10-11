import { Component } from '@angular/core';
import { testvalidation } from '../../../../core/models/testvalidation';
import { TestService } from '../../../../core/services/test.service';
import { ActivatedRoute ,Params} from '@angular/router';

@Component({
  selector: 'app-testlist',
  templateUrl: './testlist.component.html',
  styleUrls: ['./testlist.component.scss'],
  providers: [TestService]
})
export class TestlistComponent {
  quiz: testvalidation[];
  test: testvalidation=new testvalidation();
  id: number=0;
  imageUrl: string;
  qrCodeUrl: string;
  idd: string;
  file:File;
  a: String ;

  constructor(private x:TestService, private activate:ActivatedRoute)
  {
    this.quiz=[]
    this.a=")"  
  }
  public gettest(): void {

    this.x.gettestt().subscribe((Response: testvalidation[] ) =>  {console.log(Response)
      this.quiz=Response;
  
   })



}
ngOnInit() : void  {
  //this.activate.params.subscribe((params: Params) => {
    //this.id = params['id'];
  //});
     
    //this.x.updatequiz(this.id).subscribe(data=> this.test=data)
    //const isidpresent=this.activate.snapshot.paramMap.has('id');
    //if(isidpresent){
   //const id = parseInt(this.activate.snapshot.paramMap.get('id') || '0');
   this.activate.params.subscribe((params: Params) => {
    this.idd = params['id'];
  });


  console.log('welcome aziz')
  this.gettest();
  this.generateQrCode(this.idd);
  console.log('qrcode0')
  
  }
  generateQrCode(idd: string): void {
    this.x.generateqrcode(idd).subscribe((blob: Blob) => {
      this.qrCodeUrl = URL.createObjectURL(blob);
    });
    

  //href="single-job-page.html"

  }
  delete(id,index){
    console.log(index);
    this.x.delete(id).subscribe((res:any)=>{
      this.quiz.splice(index,1);
    })
  }
  
  getImage(): void {
    this.x.generateqrcode('id').subscribe((image: Blob) => {
      this.imageUrl = URL.createObjectURL(image);
    });
  }
  
  
  }

 

  
  
 

  




