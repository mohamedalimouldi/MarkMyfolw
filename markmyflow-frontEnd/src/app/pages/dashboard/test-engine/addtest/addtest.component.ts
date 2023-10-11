import { Component, EventEmitter, OnInit } from '@angular/core';
import { testvalidation } from '../../../../core/models/testvalidation';
import { TestService } from '../../../../core/services/test.service';
import { ActivatedRoute ,Params, Router} from '@angular/router';
import { question } from 'src/app/core/models/question';

@Component({
  selector: 'app-addtest',
  templateUrl: './addtest.component.html',
  styleUrls: ['./addtest.component.scss'],
  providers: [TestService]
})
export class AddtestComponent implements OnInit{
  test: testvalidation=new testvalidation();
  id: number=0;
  quest: question[]=[];
  imageUrl: any;
  a: String;
  idv = 1;
  testt: {
    titre: '',
    duree: '',
    pts: '',
    image: ''
  };
  
  
    
 

  constructor(private x:TestService, private activate:ActivatedRoute,private router: Router
    )
  {
    //this.a="ajout avec succes"
  }




  addquiz()
  {
    
    this.x.savequiz(this.test).subscribe(data => {
      console.log(data)
      this.test=data;
      this.router.navigate(["/dashboard/question/addquestion/",this.test.idv])
    })
    
  }
  ngOnInit() : void  {
    this.test.duree= null;
    this.test.pts=null;
  
    

}
addquestt()
  {
    
  }
  }
  


  

